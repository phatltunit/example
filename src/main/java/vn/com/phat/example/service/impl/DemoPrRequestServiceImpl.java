/*******************************************************************************
 * Class        ：DemoPrRequestServiceImpl
 * Created date ：2025/06/13
 * Lasted date  ：2025/06/13
 * Author       ：PhatLT
 * Change log   ：2025/06/13：1.0 PhatLT Initial creation
 ******************************************************************************/
package vn.com.phat.example.service.impl;

import jp.sf.amateras.mirage.SqlManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.phat.example.common.PageWrapper;
import vn.com.phat.example.dto.DemoPrRequestDto;
import vn.com.phat.example.dto.PRRequestBiDataDto;
import vn.com.phat.example.dto.PRRequestDto;
import vn.com.phat.example.entity.DemoPrRequest;
import vn.com.phat.example.enumdef.PRRequestStatus;
import vn.com.phat.example.mapper.DemoPrRequestMapper;
import vn.com.phat.example.repository.DemoPrRequestRepository;
import vn.com.phat.example.service.DemoBiDataService;
import vn.com.phat.example.service.DemoPrRequestService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DemoPrRequestServiceImpl implements DemoPrRequestService {

    private final DemoPrRequestRepository demoPrRequestRepository;
    private final DemoPrRequestMapper demoPrRequestMapper;
    private final DemoBiDataService demoBiDataService;
    private final SqlManager sqlManager;

    @Override
    public PageWrapper<DemoPrRequestDto> search(int page, DemoPrRequestDto dto, int pageSize) {
        PageWrapper<DemoPrRequestDto> wrapper = new PageWrapper<>(page, pageSize, new ArrayList<>());
        List<DemoPrRequestDto> list = new ArrayList<>();
        int count = demoPrRequestRepository.countByCondition(dto);
        if (count > 0) {
            int currentPage = wrapper.getCurrentPage();
            int startIndex = (currentPage - 1) * pageSize;
            list = demoPrRequestRepository.searchByCondition(startIndex, pageSize, dto);
        }

        wrapper.setDataAndCount(list, count);
        return wrapper;
    }

    @Override
    public DemoPrRequestDto save(DemoPrRequestDto dto) {
        DemoPrRequest entity;
        if (dto.getId() != null) {
            Optional<DemoPrRequest> existingEntity = Optional.ofNullable(demoPrRequestRepository.findOne(dto.getId()));
            if (existingEntity.isPresent()) {
                entity = existingEntity.get();
                demoPrRequestMapper.toEntity(dto); // Assuming MapStruct can update existing entity
            } else {
                entity = demoPrRequestMapper.toEntity(dto);
            }
        } else {
            entity = demoPrRequestMapper.toEntity(dto);
        }
        return demoPrRequestMapper.toDto(demoPrRequestRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        DemoPrRequest entity = demoPrRequestRepository.findOne(id);
        if (entity != null) {
            demoPrRequestRepository.delete(entity);
        }
    }

    List<DemoPrRequest> processBiData(List<PRRequestBiDataDto> biDataList, String prNo) {
        biDataList.sort(Comparator.comparing(PRRequestBiDataDto::getBiNo)); // We've prevented deadlock just by one line of code
        return biDataList.stream().map(item -> {
            PRRequestStatus status = PRRequestStatus.PENDING;
            int count = demoBiDataService.updatePendingAmount(item.getBiNo(), item.getAmount());
            if (count <= 0)
                status = PRRequestStatus.REJECTED;
            DemoPrRequest en = demoPrRequestMapper.fromRequestData(item, prNo, status.name());
            demoPrRequestRepository.save(en);
            return en;
        }).collect(Collectors.toList());
    }

    @Override
    public void processRequest(PRRequestDto requestData) {
        requestData.setPrNo(generatePrNo());
        final String prNo = requestData.getPrNo();
        Optional.ofNullable(requestData.getBiData()).ifPresent(lst -> {
            List<DemoPrRequest> prRequests = processBiData(lst, prNo);
            // if we found any rejected PR, we need to release all pending PRs
            boolean needRelease = prRequests.stream().anyMatch(pr -> pr.getStatus().equals(PRRequestStatus.REJECTED.name()));
            if (needRelease) {
                prRequests.stream().filter(pr -> pr.getStatus().equals(PRRequestStatus.PENDING.name())).forEach(pr -> {
                    // we need to release pending amount
                    demoBiDataService.updatePendingAmount(pr.getBiNo(), BigDecimal.ZERO.subtract(pr.getAmount()));
                    demoPrRequestRepository.updateStatus(pr.getPrNo(), pr.getBiNo(), PRRequestStatus.REJECTED.name());

                });
            }
        });
    }


    private String generatePrNo() {
        return "PR-" + UUID.randomUUID() + "-" + System.getenv("APP_NAME");
    }

    @Override
    public void processRequestBatch(PRRequestDto requestData) {
        requestData.setPrNo(generatePrNo());
        final String prNo = requestData.getPrNo();
        List<DemoPrRequest> prRequests = Optional.ofNullable(requestData.getBiData()).orElseGet(ArrayList::new).stream()
                .filter(Objects::nonNull).map(item -> demoPrRequestMapper.fromRequestData(item, prNo, PRRequestStatus.PENDING.name()))
                .collect(Collectors.toList());
        // If an Unchecked Exception occurs, the transaction will be rolled back.
        demoBiDataService.updatePendingAmountBatch(prNo, sqlManager.insertBatch(prRequests));

    }
}
