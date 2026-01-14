select product_id, product_name, sum(price*amount) as total_sales
from food_product left join food_order using (product_id)
where produce_date like '2022-05%'
group by product_id
order by total_sales desc, product_id






# -- 코드를 입력하세요
# SELECT PRODUCT_ID, PRODUCT_NAME, 
#     PRICE*SALES TOTAL_SALES
# FROM FOOD_PRODUCT
# JOIN (
#     SELECT PRODUCT_ID, SUM(AMOUNT) SALES
#     FROM FOOD_ORDER
#     WHERE PRODUCE_DATE LIKE '2022-05%'
#     GROUP BY 1
# ) O USING(PRODUCT_ID)
# GROUP BY 1
# ORDER BY TOTAL_SALES DESC, PRODUCT_ID