select count(*) from demo_bi_data
where 1=1
/*IF dto.biNo != null*/
    AND bi_no LIKE CONCAT('%', /*dto.biNo*/'', '%')
/*END*/
