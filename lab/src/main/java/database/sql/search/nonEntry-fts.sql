SELECT Phone, FirstName, LastName
FROM customers,
     to_tsquery(?) AS q
WHERE make_tsvector_cus(Phone, FirstName, LastName, Street, City) @@ !!q
ORDER BY ts_rank(make_tsvector_cus(Phone, FirstName, LastName, Street, City), !!q)