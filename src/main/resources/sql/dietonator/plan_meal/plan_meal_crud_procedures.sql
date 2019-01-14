DELIMITER $$
CREATE PROCEDURE create_plan_meal(IN _plan VARCHAR(30), IN _meal VARCHAR(25), IN _day_of_week ENUM('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'),
IN _time_of_day ENUM('Breakfast', 'Elevenses', 'Dinner', 'Tea', 'Supper'))
BEGIN 
	INSERT INTO plan_meal VALUES (
    _plan,
    _meal,
    _day_of_week,
    _time_of_day
    );
END $$

CREATE PROCEDURE delete_plan_meal(IN _plan VARCHAR(30), IN _day_of_week ENUM('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'),
IN _time_of_day ENUM('Breakfast', 'Elevenses', 'Dinner', 'Tea', 'Supper'))
BEGIN 
	DELETE FROM plan_meal WHERE
    plan = _plan AND 
    day_of_week = _day_of_week AND 
    time_of_day = _time_of_day;
END $$

CREATE PROCEDURE select_meals_of_plan(IN _plan VARCHAR(30))
BEGIN 
	SELECT plan, meal, day_of_week, time_of_day FROM plan_meal
    WHERE plan = _plan;
END $$

DELIMITER ;

DROP PROCEDURE select_meals_of_plan;
