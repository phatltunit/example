SELECT
    id,
    name,
    description,
    created_date,
    updated_date,
    deleted_flag,
    created_by,
    updated_by
FROM example
WHERE 1=1
/*IF dto.name != null*/
    AND name LIKE CONCAT('%', /*dto.name*/'', '%')
/*END*/
/*IF dto.description != null*/
    AND description LIKE CONCAT('%', /*dto.description*/'', '%')
/*END*/
AND deleted_flag = 0
ORDER BY id
LIMIT /*size*/0 OFFSET /*offset*/0
