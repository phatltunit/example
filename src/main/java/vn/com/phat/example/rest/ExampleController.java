/*******************************************************************************
 * Class        ：ExampleController
 * Created date ：2025/06/09
 * Lasted date  ：2025/06/09
 * Author       ：PhatLT
 * Change log   ：2025/06/09：1.0 PhatLT Initial creation
 ******************************************************************************/
package vn.com.phat.example.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.phat.example.common.PageWrapper;
import vn.com.phat.example.dto.ExampleDto;
import vn.com.phat.example.service.ExampleService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/example")
@RequiredArgsConstructor
public class ExampleController {

    private final ExampleService exampleService;

    @GetMapping("/search")
    public ResponseEntity<PageWrapper<ExampleDto>> search(
            @ModelAttribute ExampleDto dto,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageWrapper<ExampleDto> result = exampleService.search(dto, page, pageSize);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/save")
    public ResponseEntity<Void> save(@Valid @RequestBody ExampleDto dto) {
        exampleService.save(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        exampleService.delete(id);
        return ResponseEntity.ok().build();
    }
}
