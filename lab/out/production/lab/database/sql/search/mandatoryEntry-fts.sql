SELECT ts_headline(Phone, q, 'StartSel=<em>, StopSel=</em>'), FirstName, LastName
FROM customers,
     phraseto_tsquery('simple', ?) AS q
WHERE make_tsvector_cus(Phone, FirstName, LastName, Street, City) @@ q
ORDER BY ts_rank(make_tsvector_cus(Phone, FirstName, LastName, Street, City), q)