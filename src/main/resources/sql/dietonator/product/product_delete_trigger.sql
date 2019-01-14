DELIMITER $$
CREATE TRIGGER product_delete_trigger BEFORE DELETE ON product
FOR EACH ROW 
BEGIN 
    DELETE FROM product_meal WHERE product=OLD.name;
END $$ 
DELIMITER ;

DROP TRIGGER product_delete_trigger;
