/*******************************************************************************
 * Class        ：ExampleRepository
 * Created date ：2025/06/09
 * Lasted date  ：2025/06/12
 * Author       ：PhatLT
 * Change log   ：2025/06/09：1.0 PhatLT Initial creation
 *              ：2025/06/12：1.1 PhatLT Changed searchByCondition return type to List<Example> for better separation of concerns.
 ******************************************************************************/
package vn.com.phat.example.repository;

import org.springframework.data.mirage.repository.MirageRepository;
import org.springframework.data.repository.query.Param;
import vn.com.phat.example.dto.ExampleDto;
import vn.com.phat.example.entity.Example;

import java.util.List;

public interface ExampleRepository extends MirageRepository<Example, Long> {

    Example findOne(Long id);

    int countByCondition(@Param("dto") ExampleDto dto);

    List<Example> searchByCondition(@Param("offset") int offset, @Param("size") int size, @Param("dto") ExampleDto dto);
}
