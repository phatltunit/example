/*******************************************************************************
 * Class        ：DemoPrRequestService
 * Created date ：2025/06/13
 * Lasted date  ：2025/06/13
 * Author       ：PhatLT
 * Change log   ：2025/06/13：1.0 PhatLT Initial creation
 ******************************************************************************/
package vn.com.phat.example.service;

import vn.com.phat.example.common.PageWrapper;
import vn.com.phat.example.dto.DemoPrRequestDto;
import vn.com.phat.example.dto.PRRequestDto;

public interface DemoPrRequestService {
    PageWrapper<DemoPrRequestDto> search(int page, DemoPrRequestDto dto, int pageSize);
    DemoPrRequestDto save(DemoPrRequestDto dto);
    void delete(Long id);
    void processRequest(PRRequestDto requestData);
    void processRequestBatch(PRRequestDto requestData);
}
