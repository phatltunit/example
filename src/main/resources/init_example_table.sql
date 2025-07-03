CREATE SEQUENCE example_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE example (
    id BIGINT PRIMARY KEY DEFAULT nextval('example_id_seq'),
    name VARCHAR(255),
    description VARCHAR(500),
    created_date TIMESTAMP,
    created_by VARCHAR(255),
    updated_date TIMESTAMP,
    updated_by VARCHAR(255),
    deleted_flag INT
);
