/*******************************************************************************
 * Class        ：ExampleDto
 * Created date ：2025/06/09
 * Lasted date  ：2025/06/09
 * Author       ：PhatLT
 * Change log   ：2025/06/09：1.0 PhatLT Initial creation
 ******************************************************************************/
package vn.com.phat.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExampleDto {

    private Long id;

    @Size(max = 255, message = "Name cannot exceed 255 characters")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    private Integer deletedFlag;
}
