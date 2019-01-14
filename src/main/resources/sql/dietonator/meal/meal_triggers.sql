DELIMITER $$
CREATE TRIGGER meal_delete_trigger AFTER DELETE ON meal
FOR EACH ROW
BEGIN 
	DELETE FROM product_meal WHERE meal=OLD.name;
END $$

CREATE TRIGGER meal_delete_before_trigger BEFORE DELETE ON meal
FOR EACH ROW
BEGIN 
    DELETE FROM plan_meal WHERE meal=OLD.name;
END $$

CREATE TRIGGER meal_update_trigger AFTER UPDATE ON meal
FOR EACH ROW
BEGIN
	UPDATE nutritional_plan np INNER JOIN plan_meal pm ON pm.plan = np.name
    SET
    np.energy = np.energy - OLD.energy + NEW.energy,
    np.fat = np.fat - OLD.fat + NEW.fat,
    np.of_which_saturates = np.of_which_saturates - OLD.of_which_saturates + NEW.of_which_saturates,
    np.carbohydrates = np.carbohydrates - OLD.carbohydrates + NEW.carbohydrates,
    np.of_which_sugars = np.of_which_sugars - OLD.of_which_sugars + NEW.of_which_sugars,
    np.protein = np.protein - OLD.protein + NEW.protein,
    np.salt = np.salt - OLD.salt + NEW.salt
    WHERE pm.meal = OLD.name;
END $$


DELIMITER ;

DROP TRIGGER meal_update_trigger;
DROP TRIGGER meal_delete_trigger;

