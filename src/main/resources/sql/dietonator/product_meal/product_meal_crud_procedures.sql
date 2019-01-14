DELIMITER $$
CREATE PROCEDURE create_product_meal(IN pname VARCHAR (15), IN mname VARCHAR(25), IN amount INT)
BEGIN 
	INSERT INTO product_meal VALUES (pname, mname, amount);
END $$

CREATE PROCEDURE update_product_meal(IN pname VARCHAR(15), IN mname VARCHAR(25), IN _amount INT)
BEGIN 
	UPDATE product_meal SET amount=_amount WHERE product=pname AND meal=mname;
END $$

CREATE PROCEDURE delete_product_meal(IN pname VARCHAR(15), IN mname VARCHAR(25))
BEGIN 
	DELETE FROM product_meal WHERE product=pname AND meal=mname; 
END $$

CREATE PROCEDURE select_products_of_meal(IN mname VARCHAR(25))
BEGIN 
	SELECT p.name, amount FROM product p INNER JOIN product_meal pm ON pm.product = p.name
    INNER JOIN meal m ON m.name = pm.meal WHERE m.name = mname;
END $$

DELIMITER ;
DROP PROCEDURE update_product_meal;
DROP PROCEDURE delete_product_meal;
DROP PROCEDURE select_products_of_meal;

GRANT EXECUTE ON PROCEDURE select_products_of_meal TO 'jacula'@'%';
