/*******************************************************************************
 * Class        ：DemoBiDataController
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
import vn.com.phat.example.dto.DemoBiDataDto;
import vn.com.phat.example.service.DemoBiDataService;

@RestController
@RequestMapping("/api/demo-bi-data")
@RequiredArgsConstructor
public class DemoBiDataController {

    private final DemoBiDataService demoBiDataService;

    @PostMapping("/search")
    public ResponseEntity<PageWrapper<DemoBiDataDto>> search(@RequestBody DemoBiDataDto dto,
                                                             @RequestParam(defaultValue = "1") int page,
                                                             @RequestParam(defaultValue = "10") int pageSize) {
        PageWrapper<DemoBiDataDto> result = demoBiDataService.search(page, dto, pageSize);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/save")
    public ResponseEntity<DemoBiDataDto> save(@RequestBody DemoBiDataDto dto) {
        DemoBiDataDto result = demoBiDataService.save(dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        demoBiDataService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
