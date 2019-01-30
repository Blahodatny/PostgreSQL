SELECT Product_ID
FROM (
       SELECT Product_ID, row_number() OVER (ORDER BY Product_ID) FROM products
     ) X
WHERE row_number = ?