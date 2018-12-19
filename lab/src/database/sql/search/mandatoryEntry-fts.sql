SELECT ts_headline(Phone, q, 'StartSel=\u001B[34m, StopSel=\u001B[0m'), FirstName, LastName
FROM customers,
     to_tsquery(?) AS q
WHERE make_tsvector_cus(Phone, FirstName, LastName, Street, City) @@ q
ORDER BY ts_rank(make_tsvector_cus(Phone, FirstName, LastName, Street, City), q)