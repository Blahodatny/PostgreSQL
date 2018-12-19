CREATE TABLE IF NOT EXISTS products
(
  Product_ID  VARCHAR(20) NOT NULL,
  ProductType TEXT        NOT NULL,
  isNew       BOOLEAN     NOT NULL,
  PRIMARY KEY (Product_ID)
);

CREATE UNIQUE INDEX IF NOT EXISTS products_product_id_uindex ON products (Product_ID);

CREATE OR REPLACE FUNCTION make_tsvector_pr(Product_ID text, ProductType text)
  RETURNS TSVECTOR AS
$$
BEGIN
  RETURN (setweight(to_tsvector('english', Product_ID), 'D')) ||
         setweight(to_tsvector('english', ProductType), 'D');
END
$$ LANGUAGE 'plpgsql'
   IMMUTABLE;

CREATE INDEX IF NOT EXISTS idx_fts_products ON products
  USING gin (make_tsvector_pr(Product_ID, ProductType));

CREATE EXTENSION IF NOT EXISTS pg_trgm;

CREATE INDEX IF NOT EXISTS products_trgm_idx ON products
  USING gin (make_tsvector_pr(Product_ID, ProductType))