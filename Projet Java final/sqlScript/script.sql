CREATE DATABASE gestionstock;

\c gestionstock;

CREATE TABLE client (
  idclient SERIAL PRIMARY KEY,
  nomclient VARCHAR(50),
  adresseclient VARCHAR(100),
  emailclient VARCHAR(50),
  telclient VARCHAR(15)
);

CREATE TABLE produit (
  idproduit SERIAL PRIMARY KEY,
  nomproduit VARCHAR(50),
  prixunitaire DECIMAL(10, 2),
  qtestock INT
);