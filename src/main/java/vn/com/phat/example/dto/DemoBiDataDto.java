/*******************************************************************************
 * Class        ：DemoBiDataDto
 * Created date ：2025/06/13
 * Lasted date  ：2025/06/13
 * Author       ：PhatLT
 * Change log   ：2025/06/13：1.0 PhatLT Initial creation
 ******************************************************************************/
package vn.com.phat.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DemoBiDataDto {
    private Long id;
    private String biNo;
    private BigDecimal amount;
    private BigDecimal pendingAmount;
    private BigDecimal balance;
}
