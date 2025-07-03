/*******************************************************************************
 * Class        ：DemoBiDataRepository
 * Created date ：2025/06/13
 * Lasted date  ：2025/06/13
 * Author       ：PhatLT
 * Change log   ：2025/06/13：1.0 PhatLT Initial creation
 ******************************************************************************/
package vn.com.phat.example.repository;

import org.springframework.data.mirage.repository.MirageRepository;
import org.springframework.data.mirage.repository.query.Modifying;
import org.springframework.data.repository.query.Param;
import vn.com.phat.example.dto.DemoBiDataDto;
import vn.com.phat.example.entity.DemoBiData;
import vn.com.phat.example.exception.DatabaseException;

import java.math.BigDecimal;
import java.util.List;

public interface DemoBiDataRepository extends MirageRepository<DemoBiData, Long> {

    int countByCondition(@Param("dto") DemoBiDataDto dto);

    List<DemoBiDataDto> searchByCondition(@Param("offset") int offset, @Param("size") int size, @Param("dto") DemoBiDataDto dto);

    DemoBiData findOne(@Param("id") Long id);


    @Modifying
    int updatePendingAmount(@Param("biNo") String biNo, @Param("pendingAmount") BigDecimal pendingAmount);

    @Modifying
    int updatePendingAmountBatch(@Param("prNo") String prNo) throws DatabaseException;
}
