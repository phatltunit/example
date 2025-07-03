UPDATE demo_bi_data
SET pending_amount = COALESCE(pending_amount, 0) + COALESCE(ordered_data.amount, 0)
FROM (
    SELECT bi.bi_no, pr.amount
    FROM demo_bi_data bi
    JOIN demo_pr_request pr ON bi.bi_no = pr.bi_no
    WHERE pr.pr_no = /*prNo*/''
      AND bi.balance - COALESCE(pr.amount, 0) >= 0
    -- Two lines below are used to make sure the deadlock does not happen
    ORDER BY bi.bi_no ASC
    FOR UPDATE
) AS ordered_data
WHERE demo_bi_data.bi_no = ordered_data.bi_no;