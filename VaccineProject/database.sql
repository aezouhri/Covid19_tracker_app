DROP TABLE BusinessT;

CREATE TABLE BusinessT
(
    CompanyName VARCHAR (15) NOT NULL,
    Website VARCHAR (30) NOT NULL,
    PhoneNumber VARCHAR (15) NOT NULL
);

INSERT INTO BusinessT (CompanyName,Website,PhoneNumber)
VALUES ('Walmart', 'https://walmart.com','555-5555'),
       ('Target','https://target.com','555-1234');