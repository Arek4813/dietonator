DELIMITER $$
CREATE TRIGGER delete_plan_meal_trigger BEFORE DELETE ON plan_meal
FOR EACH ROW
BEGIN
	UPDATE meal m INNER JOIN plan_meal pm ON m.name = pm.meal INNER JOIN nutritional_plan np ON np.name = pm.plan
    SET
    np.energy = np.energy - m.energy,
    np.fat = np.fat - m.fat,
    np.of_which_saturates = np.of_which_saturates - m.of_which_saturates,
    np.carbohydrates = np.carbohydrates - m.carbohydrates,
    np.of_which_sugars = np.of_which_sugars - m.of_which_sugars,
    np.protein = np.protein - m.protein,
    np.salt = np.salt - m.salt
    WHERE np.name = OLD.plan;
END $$
DELIMITER ;
