select m.member_name MEMBER_NAME, r.review_text REVIEW_TEXT, r.review_date REVIEW_DATE
from member_profile m right join rest_review r using (member_id)
where m.member_id = (select member_id from (
    select member_id, count(*)
    from rest_review
    group by (member_id)
    order by 2 desc
    limit 1
) s )
order by r.review_date, r.review_text;