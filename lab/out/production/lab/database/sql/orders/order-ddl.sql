CREATE TABLE IF NOT EXISTS orders
(
  Order_Number SERIAL      NOT NULL,
  Phone        VARCHAR(20) NOT NULL,
  ToStreet     TEXT        NOT NULL,
  ToCity       TEXT        NOT NULL,
  ShipDate     TIMESTAMP   NOT NULL,
  PRIMARY KEY (Order_Number),
  CONSTRAINT fk FOREIGN KEY (Phone) REFERENCES customers (Phone) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE OR REPLACE FUNCTION make_tsvector_ord(ToStreet text, ToCity text)
  RETURNS TSVECTOR AS
$$
BEGIN
  RETURN (setweight(to_tsvector('english', ToStreet), 'D')) ||
         setweight(to_tsvector('english', ToCity), 'D');
END
$$ LANGUAGE 'plpgsql'
   IMMUTABLE;

CREATE INDEX IF NOT EXISTS idx_ftx_orders ON orders
  USING gin (make_tsvector_ord(ToStreet, ToCity));

CREATE EXTENSION IF NOT EXISTS pg_trgm;

CREATE INDEX IF NOT EXISTS orders_trgm_idx ON orders
  USING gin (make_tsvector_ord(ToStreet, ToCity))