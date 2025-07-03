/*******************************************************************************
 * Class        ：DemoBiDataMapper
 * Created date ：2025/06/13
 * Lasted date  ：2025/06/13
 * Author       ：PhatLT
 * Change log   ：2025/06/13：1.0 PhatLT Initial creation
 ******************************************************************************/
package vn.com.phat.example.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import vn.com.phat.example.dto.DemoBiDataDto;
import vn.com.phat.example.entity.DemoBiData;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DemoBiDataMapper {
    DemoBiData toEntity(DemoBiDataDto dto);
    DemoBiDataDto toDto(DemoBiData entity);
    void toEntity(DemoBiDataDto dto, @MappingTarget DemoBiData entity);
}
