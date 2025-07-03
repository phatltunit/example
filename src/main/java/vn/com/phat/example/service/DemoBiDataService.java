/*******************************************************************************
 * Class        ：DemoBiDataService
 * Created date ：2025/06/13
 * Lasted date  ：2025/06/13
 * Author       ：PhatLT
 * Change log   ：2025/06/13：1.0 PhatLT Initial creation
 ******************************************************************************/
package vn.com.phat.example.service;

import vn.com.phat.example.common.PageWrapper;
import vn.com.phat.example.dto.DemoBiDataDto;
import vn.com.phat.example.exception.DatabaseException;

import java.math.BigDecimal;

public interface DemoBiDataService {
    PageWrapper<DemoBiDataDto> search(int page, DemoBiDataDto dto, int pageSize);
    DemoBiDataDto save(DemoBiDataDto dto);
    void delete(Long id);

    int updatePendingAmount(String biNo, BigDecimal pendingAmount) throws DatabaseException;
    int updatePendingAmountBatch(String prNo, int expected);
}
