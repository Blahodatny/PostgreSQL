CREATE TABLE IF NOT EXISTS PRODUCTS
(
  Id          VARCHAR(20) NOT NULL,
  Type        TEXT        NOT NULL,
  Second_Hand BOOLEAN     NOT NULL,
  PRIMARY KEY (Id)
);

CREATE UNIQUE INDEX IF NOT EXISTS products_id_uindex ON PRODUCTS (Id);

CREATE OR REPLACE FUNCTION make_tsvector_pr(Id TEXT, Type TEXT)
  RETURNS TSVECTOR AS
$$
BEGIN
  RETURN (setweight(to_tsvector('english', Id), 'D')) ||
         setweight(to_tsvector('english', Type), 'D');
END
$$ LANGUAGE 'plpgsql'
   IMMUTABLE;

CREATE INDEX IF NOT EXISTS idx_fts_products ON PRODUCTS
  USING gin (make_tsvector_pr(Id, Type));

CREATE EXTENSION IF NOT EXISTS pg_trgm;

CREATE INDEX IF NOT EXISTS products_trgm_idx ON PRODUCTS
  USING gin (make_tsvector_pr(Id, Type))