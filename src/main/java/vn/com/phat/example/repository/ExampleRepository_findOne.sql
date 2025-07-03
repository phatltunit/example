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
WHERE id = /*id*/0
AND deleted_flag = 0
