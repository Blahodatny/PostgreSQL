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
FROM CUSTOMERS AS C
       INNER JOIN ORDERS AS O ON C.Phone = O.Phone
       INNER JOIN ORDER_ITEMS AS OI ON O.Order_Number = OI.Order_Number
       INNER JOIN PRODUCTS AS P ON OI.Product_ID = P.Product_ID
WHERE C.Phone || C.FirstName || C.LastName || C.Street || C.City ||
      O.ToStreet || O.ToCity || P.Product_ID || P.ProductType LIKE ?

