/*******************************************************************************
 * Class        ：DemoBiDataServiceImpl
 * Created date ：2025/06/13
 * Lasted date  ：2025/06/13
 * Author       ：PhatLT
 * Change log   ：2025/06/13：1.0 PhatLT Initial creation
 ******************************************************************************/
package vn.com.phat.example.service.impl;

import jp.sf.amateras.mirage.exception.SQLRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.phat.example.common.PageWrapper;
import vn.com.phat.example.dto.DemoBiDataDto;
import vn.com.phat.example.entity.DemoBiData;
import vn.com.phat.example.exception.BusinessException;
import vn.com.phat.example.exception.DatabaseException;
import vn.com.phat.example.mapper.DemoBiDataMapper;
import vn.com.phat.example.repository.DemoBiDataRepository;
import vn.com.phat.example.service.DemoBiDataService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DemoBiDataServiceImpl implements DemoBiDataService {

    private final DemoBiDataRepository demoBiDataRepository;
    private final DemoBiDataMapper demoBiDataMapper;

    @Override
    public PageWrapper<DemoBiDataDto> search(int page, DemoBiDataDto dto, int pageSize) {
        PageWrapper<DemoBiDataDto> wrapper = new PageWrapper<>(page, pageSize, new ArrayList<>());
        List<DemoBiDataDto> list = new ArrayList<>();
        int count = demoBiDataRepository.countByCondition(dto);
        if (count > 0) {
            int currentPage = wrapper.getCurrentPage();
            int startIndex = (currentPage - 1) * pageSize;
            list = demoBiDataRepository.searchByCondition(startIndex, pageSize, dto);
        }

        wrapper.setDataAndCount(list, count);
        return wrapper;
    }

    @Override
    public DemoBiDataDto save(DemoBiDataDto dto) {
        DemoBiData entity;
        if (dto.getId() != null) {
            DemoBiData existingEntity = demoBiDataRepository.findOne(dto.getId());
            if (existingEntity != null) {
                entity = existingEntity;
                demoBiDataMapper.toEntity(dto, entity); // Assuming MapStruct can update existing entity
                // entity.setUpdatedDate(new Date()); // No updated_date in schema
                // entity.setUpdatedBy(UserProfileUtils.getUsernameLogin()); // No updated_by in schema
            } else {
                entity = demoBiDataMapper.toEntity(dto);
                // entity.setCreatedDate(new Date()); // No created_date in schema
                // entity.setCreatedBy(UserProfileUtils.getUsernameLogin()); // No created_by in schema
            }
        } else {
            entity = demoBiDataMapper.toEntity(dto);
            // entity.setCreatedDate(new Date()); // No created_date in schema
            // entity.setCreatedBy(UserProfileUtils.getUsernameLogin()); // No created_by in schema
        }
        return demoBiDataMapper.toDto(demoBiDataRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        DemoBiData entity = demoBiDataRepository.findOne(id);
        if (entity != null) {
            demoBiDataRepository.delete(entity);
        }
    }

    @Override
    public int updatePendingAmount(String biNo, BigDecimal pendingAmount) throws DatabaseException {
        try {
            return demoBiDataRepository.updatePendingAmount(biNo, pendingAmount);
        } catch (SQLRuntimeException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public int updatePendingAmountBatch(String prNo, int expected) {
        try {
            int count = demoBiDataRepository.updatePendingAmountBatch(prNo);
            if(count < expected) {
                throw new BusinessException("Budget is not enough for PR. Please check your Budgets and try again.");
            }
            return count;
        }
        catch (SQLRuntimeException e) {
            throw new DatabaseException(e);
        }
    }
}
