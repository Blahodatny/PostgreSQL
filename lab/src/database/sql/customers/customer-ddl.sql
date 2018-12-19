CREATE TABLE IF NOT EXISTS customers
(
  Phone     VARCHAR(20) NOT NULL,
  FirstName VARCHAR(20) NOT NULL,
  LastName  VARCHAR(20) NOT NULL,
  Street    TEXT,
  City      TEXT        NOT NULL,
  PRIMARY KEY (Phone)
);

CREATE UNIQUE INDEX IF NOT EXISTS customers_phone_uindex ON customers (Phone);

CREATE OR REPLACE FUNCTION make_tsvector_cus(Phone text, FirstName text, LastName text, Street text, City text)
  RETURNS TSVECTOR AS
$$
BEGIN
  RETURN (setweight(to_tsvector('english', Phone), 'A')) ||
         setweight(to_tsvector('english', FirstName), 'B') ||
         setweight(to_tsvector('english', LastName), 'B') ||
         setweight(to_tsvector('english', Street), 'C') ||
         setweight(to_tsvector('english', City), 'C');
END
$$ LANGUAGE 'plpgsql'
   IMMUTABLE;

CREATE INDEX IF NOT EXISTS idx_fts_customers ON customers
  USING gin (make_tsvector_cus(Phone, FirstName, LastName, Street, City));

CREATE EXTENSION IF NOT EXISTS pg_trgm;

CREATE INDEX IF NOT EXISTS customers_trgm_idx ON customers
  USING gin (make_tsvector_cus(Phone, FirstName, LastName, Street, City))