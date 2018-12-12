SELECT TS_HEADLINE(Phone,q)
FROM CUSTOMERS,
     TO_TSQUERY(?) AS q
WHERE MAKE_TSVECTOR_CUS(Phone, FirstName, LastName, Street, City) @@ q;