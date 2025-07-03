select count(*) from demo_pr_request
where 1=1
/*IF dto.prNo != null*/
    AND pr_no LIKE CONCAT('%', /*dto.prNo*/'', '%')
/*END*/
/*IF dto.biNo != null*/
    AND bi_no LIKE CONCAT('%', /*dto.biNo*/'', '%')
/*END*/
/*IF dto.status != null*/
    AND status LIKE CONCAT('%', /*dto.status*/'', '%')
/*END*/
