CREATE SEQUENCE demo_bi_data_id_seq;
CREATE TABLE demo_bi_data (
    id BIGINT PRIMARY KEY DEFAULT nextval('demo_bi_data_id_seq'),
    bi_no TEXT NOT NULL UNIQUE,
    amount NUMERIC,
    pending_amount NUMERIC,
    balance NUMERIC GENERATED ALWAYS AS (amount - COALESCE(pending_amount, 0)) STORED
);

-- insert some sample bi data
INSERT INTO demo_bi_data (bi_no, amount, pending_amount) VALUES
('BI-001', 10000.00, NULL),
('BI-002', 15000.00, NULL),
('BI-003', 20000.00, NULL),
('BI-004', 25000.00, NULL),
('BI-005', 30000.00, NULL);


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
