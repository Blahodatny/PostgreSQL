CREATE TABLE IF NOT EXISTS ORDERS
(
  Id        SERIAL      NOT NULL,
  Phone     VARCHAR(20) NOT NULL,
  To_Street TEXT        NOT NULL,
  To_City   TEXT        NOT NULL,
  Ship_Date TIMESTAMP   NOT NULL,
  PRIMARY KEY (Id),
  CONSTRAINT fk FOREIGN KEY (Phone) REFERENCES CUSTOMERS (Phone)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE OR REPLACE FUNCTION make_tsvector_ord(To_Street TEXT, To_City TEXT)
  RETURNS TSVECTOR AS
$$
BEGIN
  RETURN (setweight(to_tsvector('english', To_Street), 'D')) ||
         setweight(to_tsvector('english', To_City), 'D');
END
$$ LANGUAGE 'plpgsql'
   IMMUTABLE;

CREATE INDEX IF NOT EXISTS idx_ftx_orders ON ORDERS
  USING gin (make_tsvector_ord(To_Street, To_City));

CREATE EXTENSION IF NOT EXISTS pg_trgm;

CREATE INDEX IF NOT EXISTS orders_trgm_idx ON ORDERS
  USING gin (make_tsvector_ord(To_Street, To_City))