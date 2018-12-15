SELECT FirstName, LastName, TS_HEADLINE(Phone, q, 'StartSel=\u001B[34m, StopSel=\u001B[0m')
FROM CUSTOMERS,
     TO_TSQUERY(?) AS q
WHERE MAKE_TSVECTOR_CUS(Phone, FirstName, LastName, Street, City) @@ !!q
ORDER BY TS_RANK(MAKE_TSVECTOR_CUS(Phone, FirstName, LastName, Street, City), !!q)