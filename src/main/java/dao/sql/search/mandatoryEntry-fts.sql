SELECT ts_headline(Phone, q, 'StartSel=\u001B[34m, StopSel=\u001B[0m'),
       First_Name,
       Last_Name
FROM CUSTOMERS,
     to_tsquery(?) AS q
WHERE make_tsvector_cus(Phone, First_Name, Last_Name, Street, City) @@ q
ORDER BY ts_rank(make_tsvector_cus(Phone, First_Name, Last_Name, Street, City),
                 q)