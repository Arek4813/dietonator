CREATE TABLE product (
    name varchar(25) NOT NULL,
    category VARCHAR(15) NOT NULL,
    energy INT NOT NULL, #kcal/100g
    fat INT NOT NULL, #per 100g
    of_which_saturates INT NOT NULL, #per 100g, has to be less than fat
    carbohydrates INT NOT NULL, #per 100g
    of_which_sugars INT NOT NULL, #per 100g, has to be less than carbohydrates
    protein INT NOT NULL, #per 100g
    salt INT NOT NULL, #per 100g
    PRIMARY KEY (name),
    FOREIGN KEY (category) REFERENCES category(name) ON DELETE CASCADE
);
