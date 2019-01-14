DELIMITER $$
CREATE TRIGGER category_delete_trigger AFTER DELETE ON category
FOR EACH ROW
BEGIN 
	DELETE FROM product WHERE category = OLD.name;
END $$

DELIMITER ;