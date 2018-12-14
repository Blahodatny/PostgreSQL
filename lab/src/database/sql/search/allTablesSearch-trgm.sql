SELECT ts_headline(C.Phone, q, 'StartSel=\u001B[34m, StopSel=\u001B[0m')
FROM CUSTOMERS AS C
       INNER JOIN ORDERS AS O ON C.Phone = O.Phone
       INNER JOIN ORDER_ITEMS AS OI ON O.Order_Number = OI.Order_Number
       INNER JOIN PRODUCTS AS P ON OI.Product_ID = P.Product_ID,
     TO_TSQUERY(?) AS q
WHERE MAKE_TSVECTOR_CUS(C.Phone, C.FirstName, C.LastName, C.Street, C.City) @@ q
   OR MAKE_TSVECTOR_ORD(O.ToStreet, O.ToCity) @@ q
   OR MAKE_TSVECTOR_PR(P.Product_ID, P.ProductType) @@ q

