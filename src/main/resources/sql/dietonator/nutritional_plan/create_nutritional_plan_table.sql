CREATE TABLE nutritional_plan (
    name VARCHAR(30) NOT NULL,
    energy FLOAT NOT NULL DEFAULT 0, #kcal/100g
    fat FLOAT NOT NULL DEFAULT 0, #per 100g
    of_which_saturates FLOAT NOT NULL DEFAULT 0, #per 100g, has to be less than fat
    carbohydrates FLOAT NOT NULL DEFAULT 0, #per 100g
    of_which_sugars FLOAT NOT NULL DEFAULT 0, #per 100g, has to be less than carbohydrates
    protein FLOAT NOT NULL DEFAULT 0, #per 100g
    salt FLOAT NOT NULL DEFAULT 0, #per 100g
    PRIMARY KEY (name)
);

