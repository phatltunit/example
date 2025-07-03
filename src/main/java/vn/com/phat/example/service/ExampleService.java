/*******************************************************************************
 * Class        ：ExampleService
 * Created date ：2025/06/09
 * Lasted date  ：2025/06/09
 * Author       ：PhatLT
 * Change log   ：2025/06/09：1.0 PhatLT Initial creation
 ******************************************************************************/
package vn.com.phat.example.service;

import vn.com.phat.example.common.PageWrapper;
import vn.com.phat.example.dto.ExampleDto;

public interface ExampleService {

    PageWrapper<ExampleDto> search(ExampleDto dto, int page, int pageSize);

    void save(ExampleDto dto);

    void delete(Long id);
}
