SELECT C.Phone,
       C.First_Name,
       C.Last_Name,
       C.Street,
       C.City,
       O.Id,
       O.To_Street,
       O.To_City,
       OI.Quantity,
       P.Id,
       P.Type
FROM CUSTOMERS AS C
         INNER JOIN ORDERS AS O ON C.Phone = O.Phone
         INNER JOIN ORDER_ITEMS AS OI ON O.Id = OI.Order_Id
         INNER JOIN PRODUCTS AS P ON OI.Product_Id = P.Id
WHERE C.First_Name % ?
   OR C.Last_Name % ?
   OR C.Street % ?
   OR C.City % ?
   OR O.To_Street % ?
   OR O.To_City % ?
   OR P.Id % ?
   OR P.Type % ?