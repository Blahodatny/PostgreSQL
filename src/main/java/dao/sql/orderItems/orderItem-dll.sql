CREATE TABLE IF NOT EXISTS ORDER_ITEMS
(
  Id         SERIAL      NOT NULL,
  Quantity   INT         NOT NULL,
  Order_Id   INT         NOT NULL,
  Product_Id VARCHAR(20) NOT NULL,
  PRIMARY KEY (Id),
  CONSTRAINT fk FOREIGN KEY (Order_Id) REFERENCES ORDERS (Id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk1 FOREIGN KEY (Product_Id) REFERENCES PRODUCTS (Id)
    ON DELETE NO ACTION ON UPDATE CASCADE
)