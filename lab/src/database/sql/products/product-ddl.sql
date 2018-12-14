CREATE TABLE IF NOT EXISTS PRODUCTS
(
  Product_ID  VARCHAR(20) NOT NULL,
  ProductType TEXT        NOT NULL,
  isNew       BOOLEAN     NOT NULL,
  PRIMARY KEY (Product_ID)
);

CREATE UNIQUE INDEX IF NOT EXISTS PRODUCTS_PRODUCT_ID_UINDEX ON PRODUCTS (Product_ID);

CREATE OR REPLACE FUNCTION MAKE_TSVECTOR_PR(Product_ID text, ProductType text)
  RETURNS TSVECTOR AS
$$
BEGIN
  RETURN (SETWEIGHT(TO_TSVECTOR('english', Product_ID), 'D')) ||
         SETWEIGHT(TO_TSVECTOR('english', ProductType), 'D');
END
$$ LANGUAGE 'plpgsql'
   IMMUTABLE;

CREATE INDEX IF NOT EXISTS IDX_FTS_CUSTOMERS ON PRODUCTS
  USING gin (MAKE_TSVECTOR_PR(Product_ID, ProductType));

CREATE EXTENSION PG_TRGM;

CREATE INDEX PRODUCTS_TRGM_IDX ON PRODUCTS
  USING gin (MAKE_TSVECTOR_PR(Product_ID, ProductType))