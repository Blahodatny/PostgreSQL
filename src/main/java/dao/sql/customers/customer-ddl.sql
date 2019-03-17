CREATE TABLE IF NOT EXISTS CUSTOMERS
(
  Phone      VARCHAR(20) NOT NULL,
  First_Name VARCHAR(20) NOT NULL,
  Last_Name  VARCHAR(20) NOT NULL,
  Street     TEXT,
  City       TEXT        NOT NULL,
  PRIMARY KEY (Phone)
);

CREATE UNIQUE INDEX IF NOT EXISTS customers_phone_uindex ON CUSTOMERS (Phone);

CREATE OR REPLACE FUNCTION make_tsvector_cus(Phone TEXT, First_Name TEXT, Last_Name TEXT,
                                             Street TEXT, City TEXT)
  RETURNS TSVECTOR AS
$$
BEGIN
  RETURN (setweight(to_tsvector('english', Phone), 'A')) ||
         setweight(to_tsvector('english', First_Name), 'B') ||
         setweight(to_tsvector('english', Last_Name), 'B') ||
         setweight(to_tsvector('english', Street), 'C') ||
         setweight(to_tsvector('english', City), 'C');
END
$$ LANGUAGE 'plpgsql'
   IMMUTABLE;

CREATE INDEX IF NOT EXISTS idx_fts_customers ON CUSTOMERS
  USING gin (make_tsvector_cus(Phone, First_Name, Last_Name, Street, City));

CREATE EXTENSION IF NOT EXISTS pg_trgm;

CREATE INDEX IF NOT EXISTS customers_trgm_idx ON CUSTOMERS
  USING gin (make_tsvector_cus(Phone, First_Name, Last_Name, Street, City))