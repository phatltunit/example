/*******************************************************************************
 * Class        ：DemoPrRequestController
 * Created date ：2025/06/13
 * Lasted date  ：2025/06/13
 * Author       ：PhatLT
 * Change log   ：2025/06/13：1.0 PhatLT Initial creation
 ******************************************************************************/
package vn.com.phat.example.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.phat.example.common.PageWrapper;
import vn.com.phat.example.dto.DemoPrRequestDto;
import vn.com.phat.example.dto.PRRequestDto;
import vn.com.phat.example.exception.BusinessException;
import vn.com.phat.example.service.DemoPrRequestService;

@RestController
@RequestMapping("/api/demo-pr-request")
@RequiredArgsConstructor
public class DemoPrRequestController {

    private final DemoPrRequestService demoPrRequestService;

    @PostMapping("/search")
    public ResponseEntity<PageWrapper<DemoPrRequestDto>> search(@RequestBody DemoPrRequestDto dto,
            @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize) {
        PageWrapper<DemoPrRequestDto> result = demoPrRequestService.search(page, dto, pageSize);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/save")
    public ResponseEntity<DemoPrRequestDto> save(@RequestBody DemoPrRequestDto dto) {
        DemoPrRequestDto result = demoPrRequestService.save(dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        demoPrRequestService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/request")
    public ResponseEntity<PRRequestDto> makeRequest(@RequestBody PRRequestDto requestData) {
        demoPrRequestService.processRequest(requestData);
        return ResponseEntity.ok(requestData);
    }

    @PostMapping("/request-batch")
    public ResponseEntity<Object> makeRequestBatch(@RequestBody PRRequestDto requestData) {
        try {
            demoPrRequestService.processRequestBatch(requestData);
            return ResponseEntity.ok(requestData);
        } catch (BusinessException e) {
            return ResponseEntity.status(200).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
