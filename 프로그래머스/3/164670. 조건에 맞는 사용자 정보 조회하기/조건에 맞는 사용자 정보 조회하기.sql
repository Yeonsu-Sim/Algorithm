-- 코드를 입력하세요
SELECT USER_ID, NICKNAME,
    concat(city, " ", street_address1, " " , street_address2) as '전체주소', 
    concat(substring(tlno, 1,3), "-", substring(tlno, 4,4), "-", substring(tlno, 8,4)) as '전화번호'
FROM used_goods_user as u join used_goods_board as b on u.user_id = b.writer_id
GROUP BY user_id
HAVING count(*) >= 3
ORDER BY user_id desc;