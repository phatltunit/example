SELECT count(*) FROM example
WHERE 1=1
/*IF dto.name != null*/
    AND name LIKE CONCAT('%', /*dto.name*/'', '%')
/*END*/
/*IF dto.description != null*/
    AND description LIKE CONCAT('%', /*dto.description*/'', '%')
/*END*/
AND deleted_flag = 0
