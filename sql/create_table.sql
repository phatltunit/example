CREATE SEQUENCE demo_bi_data_id_seq;
CREATE TABLE demo_bi_data (
    id BIGINT PRIMARY KEY DEFAULT nextval('demo_bi_data_id_seq'),
    bi_no TEXT NOT NULL UNIQUE,
    amount NUMERIC,
    pending_amount NUMERIC,
    balance NUMERIC GENERATED ALWAYS AS (amount - COALESCE(pending_amount, 0)) STORED
);
-- create table pr request
CREATE SEQUENCE demo_pr_request_id_seq;
CREATE TABLE demo_pr_request (
    id BIGINT PRIMARY KEY DEFAULT nextval('demo_pr_request_id_seq'),
    pr_no TEXT NOT NULL,
    bi_no TEXT,
    amount NUMERIC,
    status TEXT,
    CONSTRAINT unique_pr_bi_amount_status UNIQUE (pr_no, bi_no)
);
