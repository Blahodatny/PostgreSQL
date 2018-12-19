SELECT C.Phone,
       C.FirstName,
       C.LastName,
       C.Street,
       C.City,
       O.Order_Number,
       O.ToStreet,
       O.ToCity,
       OI.Quantity,
       P.Product_ID,
       P.ProductType
FROM customers AS C
       INNER JOIN orders AS O ON C.Phone = O.Phone
       INNER JOIN order_items AS OI ON O.Order_Number = OI.Order_Number
       INNER JOIN products AS P ON OI.Product_ID = P.Product_ID
WHERE C.FirstName % ?
   OR C.LastName % ?
   OR C.Street % ?
   OR C.City % ?
   OR O.ToStreet % ?
   OR O.ToCity % ?
   OR P.Product_ID % ?
   OR P.ProductType % ?