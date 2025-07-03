/*******************************************************************************
 * Class        ：ExampleMapper
 * Created date ：2025/06/09
 * Lasted date  ：2025/06/09
 * Author       ：PhatLT
 * Change log   ：2025/06/09：1.0 PhatLT Initial creation
 ******************************************************************************/
package vn.com.phat.example.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.com.phat.example.dto.ExampleDto;
import vn.com.phat.example.entity.Example;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExampleMapper {

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    Example toEntity(ExampleDto dto);

    ExampleDto toDto(Example entity);

    // Add method to map a list of entities to a list of DTOs
    List<ExampleDto> toDtoList(List<Example> entities);
}
