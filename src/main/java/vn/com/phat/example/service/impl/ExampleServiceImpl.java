/*******************************************************************************
 * Class        ：ExampleServiceImpl
 * Created date ：2025/06/09
 * Lasted date  ：2025/06/12
 * Author       ：PhatLT
 * Change log   ：2025/06/09：1.0 PhatLT Initial creation
 *              ：2025/06/12：1.1 PhatLT Added setSearchParm method and call, updated search method to map entities to DTOs.
 ******************************************************************************/
package vn.com.phat.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.phat.example.common.PageWrapper;
import vn.com.phat.example.dto.ExampleDto;
import vn.com.phat.example.entity.Example;
import vn.com.phat.example.mapper.ExampleMapper;
import vn.com.phat.example.repository.ExampleRepository;
import vn.com.phat.example.service.ExampleService;
import vn.com.phat.example.utils.BeanUtils;
import vn.com.phat.example.utils.UserProfileUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ExampleServiceImpl implements ExampleService {

    private final ExampleRepository exampleRepository;
    private final ExampleMapper exampleMapper;

    @Override
    public PageWrapper<ExampleDto> search(ExampleDto dto, int page, int pageSize) {
        setSearchParm(dto); // Added call as per template
        PageWrapper<ExampleDto> wrapper = new PageWrapper<>(page, pageSize, new ArrayList<>());
        int count = exampleRepository.countByCondition(dto);
        if (count > 0) {
            int currentPage = wrapper.getCurrentPage();
            int startIndex = (currentPage - 1) * pageSize;
            List<Example> entities = exampleRepository.searchByCondition(startIndex, pageSize, dto); // Changed return type
            List<ExampleDto> list = exampleMapper.toDtoList(entities); // Mapped entities to DTOs
            wrapper.setDataAndCount(list, count);
        }
        return wrapper;
    }

    @Override
    public void save(ExampleDto dto) {
        Example example = Optional.ofNullable(dto.getId())
                .map(exampleRepository::findOne)
                .orElse(new Example());

        if (example.getId() == null) { // New entity
            example = exampleMapper.toEntity(dto);
            example.setCreatedDate(new Date());
            example.setCreatedBy(UserProfileUtils.getUsernameLogin());
            example.setDeletedFlag(0); // Ensure deleted_flag is 0 for new entities
        } else { // Existing entity
            Example updatedExample = exampleMapper.toEntity(dto);
            BeanUtils.copyNonNullProperties(updatedExample, example);
            example.setUpdatedDate(new Date());
            example.setUpdatedBy(UserProfileUtils.getUsernameLogin());
        }
        exampleRepository.save(example);
    }

    @Override
    public void delete(Long id) {
        Example example = exampleRepository.findOne(id);
        if (example != null) {
            // Soft delete
            example.setDeletedFlag(1);
            example.setUpdatedDate(new Date());
            example.setUpdatedBy(UserProfileUtils.getUsernameLogin());
            exampleRepository.save(example);
        }
    }

    /**
     * Sets search parameters for the DTO.
     * This method can be used to trim strings, set default values, or perform other
     * preparations on the DTO before it's used in a search query.
     *
     * @param dto The ExampleDto to prepare.
     */
    private void setSearchParm(ExampleDto dto) {
        // Example: Trim string fields if necessary
        if (dto.getName() != null) {
            dto.setName(dto.getName().trim());
        }
        if (dto.getDescription() != null) {
            dto.setDescription(dto.getDescription().trim());
        }
        // Add any other DTO preparation logic here
    }
}
