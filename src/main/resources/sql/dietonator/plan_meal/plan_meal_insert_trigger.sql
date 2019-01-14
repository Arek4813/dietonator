DELIMITER $$

CREATE TRIGGER insert_plan_meal_trigger AFTER INSERT ON plan_meal
FOR EACH ROW
BEGIN 
	UPDATE nutritional_plan np INNER JOIN plan_meal pm ON pm.plan = np.name INNER JOIN meal m ON m.name = pm.meal
    SET 
    np.energy = np.energy + m.energy,
    np.fat = np.fat + m.fat,
    np.of_which_saturates = np.of_which_saturates + m.of_which_saturates,
    np.carbohydrates = np.carbohydrates + m.carbohydrates,
    np.of_which_sugars = np.of_which_sugars + m.of_which_sugars,
    np.protein = np.protein + m.protein,
    np.salt = np.salt + m.salt
    WHERE np.name = NEW.plan;
END $$

CREATE TRIGGER before_insert_plan_meal_trigger BEFORE INSERT ON plan_meal
FOR EACH ROW
BEGIN
	IF NEW.meal NOT IN (SELECT name FROM meal)
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Incorrect meal name';
    ELSEIF NEW.plan NOT IN (SELECT name FROM nutritional_plan)
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Incorrect plan name';
	ELSEIF NEW.day_of_week NOT IN ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday')
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Incorrect day';
	ELSEIF NEW.time_of_day NOT IN ('Breakfast', 'Elevenses', 'Dinner', 'Tea', 'Supper')
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Incorrect time of day';
	END IF;
END $$
DELIMITER ;

DROP TRIGGER before_insert_plan_meal_trigger;