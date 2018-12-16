SELECT Phone, FirstName, LastName
FROM CUSTOMERS,
     TO_TSQUERY(?) AS q
WHERE MAKE_TSVECTOR_CUS(Phone, FirstName, LastName, Street, City) @@ !!q
ORDER BY TS_RANK(MAKE_TSVECTOR_CUS(Phone, FirstName, LastName, Street, City), !!q)