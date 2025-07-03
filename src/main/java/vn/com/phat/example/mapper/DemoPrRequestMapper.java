/*******************************************************************************
 * Class        ：DemoPrRequestMapper
 * Created date ：2025/06/13
 * Lasted date  ：2025/06/13
 * Author       ：PhatLT
 * Change log   ：2025/06/13：1.0 PhatLT Initial creation
 ******************************************************************************/
package vn.com.phat.example.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import vn.com.phat.example.dto.DemoPrRequestDto;
import vn.com.phat.example.dto.PRRequestBiDataDto;
import vn.com.phat.example.entity.DemoPrRequest;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DemoPrRequestMapper {
    DemoPrRequest toEntity(DemoPrRequestDto dto);
    DemoPrRequestDto toDto(DemoPrRequest entity);
    DemoPrRequest fromRequestData(PRRequestBiDataDto requestData, String prNo, String status);
}
