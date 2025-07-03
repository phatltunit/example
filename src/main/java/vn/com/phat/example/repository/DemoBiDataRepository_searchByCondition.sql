select * from demo_bi_data
where 1=1
/*IF dto.biNo != null*/
    AND bi_no LIKE CONCAT('%', /*dto.biNo*/'', '%')
/*END*/
order by id
offset /*offset*/0
limit /*size*/0
