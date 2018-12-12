SELECT TS_HEADLINE(C.Phone || C.FirstName || C.LastName || C.Street || C.City ||
                   O.ToStreet || O.ToCity || P.Product_ID || P.ProductType,
                   q,
                   'StartSel=\u001B[34m, StopSel=\u001B[0m') -- !!!
FROM CUSTOMERS AS C,
     TO_TSQUERY('bjarne <-> stroustrup') AS q
       INNER JOIN ORDERS AS O ON