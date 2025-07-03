/*******************************************************************************
 * Class        ：DemoPrRequest
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
@Table(name = "demo_pr_request")
public class DemoPrRequest {

    @Id
    @PrimaryKey(generationType = GenerationType.SEQUENCE, generator = "demo_pr_request_id_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "pr_no")
    private String prNo;

    @Column(name = "bi_no")
    private String biNo;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "status")
    private String status;
}
