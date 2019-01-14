DELIMITER $$
CREATE TRIGGER delete_nutritional_plan_trigger AFTER DELETE ON nutritional_plan
FOR EACH ROW 
BEGIN 
	DELETE FROM plan_meal WHERE plan = OLD.name;
END $$

DELIMITER ;

DROP TRIGGER delete_nutritional_plan_trigger;