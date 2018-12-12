CREATE TABLE IF NOT EXISTS CUSTOMERS
(
  Phone     VARCHAR(20) NOT NULL,
  FirstName VARCHAR(20) NOT NULL,
  LastName  VARCHAR(20) NOT NULL,
  Street    TEXT,
  City      TEXT        NOT NULL,
  PRIMARY KEY (Phone)
);

CREATE UNIQUE INDEX IF NOT EXISTS CUSTOMERS_PHONE_UINDEX ON CUSTOMERS (Phone);

CREATE OR REPLACE FUNCTION MAKE_TSVECTOR_CUS(Phone text, FirstName text, LastName text, Street text, City text)
  RETURNS TSVECTOR AS
$$
BEGIN
  RETURN (SETWEIGHT(TO_TSVECTOR('english', Phone), 'A')) ||
         SETWEIGHT(TO_TSVECTOR('english', FirstName), 'B') ||
         SETWEIGHT(TO_TSVECTOR('english', LastName), 'B') ||
         SETWEIGHT(TO_TSVECTOR('english', Street), 'C') ||
         SETWEIGHT(TO_TSVECTOR('english', City), 'C');
END
$$ LANGUAGE 'plpgsql'
   IMMUTABLE;

CREATE INDEX IF NOT EXISTS IDX_FTS_CUSTOMERS ON CUSTOMERS
  USING gin (MAKE_TSVECTOR_CUS(Phone, FirstName, LastName, Street, City))