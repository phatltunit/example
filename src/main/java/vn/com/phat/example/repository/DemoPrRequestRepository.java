/*******************************************************************************
 * Class        ：DemoPrRequestRepository
 * Created date ：2025/06/13
 * Lasted date  ：2025/06/13
 * Author       ：PhatLT
 * Change log   ：2025/06/13：1.0 PhatLT Initial creation
 ******************************************************************************/
package vn.com.phat.example.repository;

import org.springframework.data.mirage.repository.MirageRepository;
import org.springframework.data.mirage.repository.query.Modifying;
import org.springframework.data.repository.query.Param;
import vn.com.phat.example.dto.DemoPrRequestDto;
import vn.com.phat.example.entity.DemoPrRequest;

import java.util.List;

public interface DemoPrRequestRepository extends MirageRepository<DemoPrRequest, Long> {

    int countByCondition(@Param("dto") DemoPrRequestDto dto);

    List<DemoPrRequestDto> searchByCondition(@Param("offset") int offset, @Param("size") int size, @Param("dto") DemoPrRequestDto dto);

    DemoPrRequest findOne(@Param("id") Long id);

    @Modifying
    void updateStatus(@Param("prNo") String prNo, @Param("biNo") String biNo, @Param("status") String status);
}
