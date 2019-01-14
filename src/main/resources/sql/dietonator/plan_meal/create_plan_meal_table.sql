CREATE TABLE plan_meal (
	plan VARCHAR(30) NOT NULL,
    meal VARCHAR(25) NOT NULL,
    day_of_week ENUM('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday') NOT NULL,
    time_of_day ENUM('Breakfast', 'Elevenses', 'Dinner', 'Tea', 'Supper') NOT NULL,
    FOREIGN KEY (plan) REFERENCES nutritional_plan(name) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (meal) REFERENCES meal(name) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (plan, day_of_week, time_of_day)
);