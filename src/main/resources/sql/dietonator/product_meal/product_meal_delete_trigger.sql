DELIMITER $$
CREATE TRIGGER product_meal_delete_trigger BEFORE DELETE ON product_meal
FOR EACH ROW 
BEGIN 
	UPDATE meal m INNER JOIN product_meal pm ON m.name=pm.meal INNER JOIN product p ON p.name=pm.product
    SET
    m.energy = m.energy - OLD.amount / 100 * p.energy,
    m.fat = m.fat - OLD.amount / 100 * p.fat,
    m.carbohydrates = m.carbohydrates - OLD.amount / 100 * p.carbohydrates,
    m.of_which_saturates = m.of_which_saturates - OLD.amount / 100 * p.of_which_saturates,
    m.of_which_sugars = m.of_which_sugars - OLD.amount / 100 * p.of_which_sugars,
    m.protein = m.protein - OLD.amount / 100 * p.protein,
    m.salt = m.salt - OLD.amount / 100 * p.salt
    WHERE pm.meal=OLD.meal AND pm.product=OLD.product;
END $$

DELIMITER ;

DROP TRIGGER product_meal_delete_trigger;