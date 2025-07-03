/*******************************************************************************
 * Class        ：DemoBiData
 * Created date ：2025/06/13
 * Lasted date  ：2025/06/13
 * Author       ：PhatLT
 * Change log   ：2025/06/13：1.0 PhatLT Initial creation
 ******************************************************************************/
package vn.com.phat.example.entity;

import jp.sf.amateras.mirage.annotation.Column;
import jp.sf.amateras.mirage.annotation.PrimaryKey;
import jp.sf.amateras.mirage.annotation.PrimaryKey.GenerationType;
import jp.sf.amateras.mirage.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "demo_bi_data")
public class DemoBiData {

    @Id
    @PrimaryKey(generationType = GenerationType.SEQUENCE, generator = "demo_bi_data_id_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "bi_no")
    private String biNo;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "pending_amount")
    private BigDecimal pendingAmount;

    @Column(name = "balance")
    private BigDecimal balance;
}
