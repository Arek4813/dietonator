CREATE TABLE product_meal (
	product VARCHAR(15) NOT NULL,
    meal VARCHAR(25) NOT NULL,
    amount INT NOT NULL, #unit: gram
    FOREIGN KEY (product) REFERENCES product(name) ON DELETE CASCADE,
    FOREIGN KEY (meal) REFERENCES meal(name) ON DELETE CASCADE,
    PRIMARY KEY (product, meal)
);

ALTER TABLE product_meal DROP FOREIGN KEY product_meal_ibfk_1;
ALTER TABLE product_meal DROP FOREIGN KEY product_meal_ibfk_2;

ALTER TABLE product_meal 
ADD FOREIGN KEY product_meal_ibfk_1(product)
REFERENCES product(name)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE product_meal 
ADD FOREIGN KEY product_meal_ibfk_2(meal)
REFERENCES meal(name)
ON DELETE CASCADE
ON UPDATE CASCADE;