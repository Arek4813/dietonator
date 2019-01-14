delimiter $$
drop procedure if exists writeUsersForDietician$$
create procedure writeUsersForDietician (in chosenDietician varchar(40))
begin
	select UserLogin, DieticianLogin from users_and_dieticians where DieticianLogin=chosenDietician;
    end;$$
delimiter ;

/**select userName, userSurname from normal_users inner join users_and_dieticians on normal_users.userLogin=users_and_dieticians.UserLogin where users_and_dieticians.DieticianLogin=chosenDietician;*//

delimiter $$
drop procedure if exists writeDieticiansForUser$$
create procedure writeDieticiansForUser (in chosenUser varchar(40))
begin 
	select UserLogin, DieticianLogin from users_and_dieticians where UserLogin=chosenUser;
end;$$
delimiter ;


delimiter $$
drop procedure if exists writeDieticiansForChosenUser$$
create procedure writeDieticiansForChosenUser (in chosenUser varchar(40))
begin 
	select dieticians.dieticianLogin, dieticians.dieticianName, dieticians.dieticianSurname, dieticians.dieticianBirthDate, dieticians.dieticianMail from dieticians inner join users_and_dieticians on dieticians.dieticianLogin=users_and_dieticians.DieticianLogin where users_and_dieticians.UserLogin=chosenUser;
end;$$
delimiter ;




/**select dieticianName, dieticianSurname from dieticians inner join users_and_dieticians on dieticians.dieticianLogin=users_and_dieticians.DieticianLogin where users_and_dieticians.UserLogin=chosenUser;*//

delimiter $$
drop procedure if exists writeDietsForUser$$
create procedure writeDietsForUser (in chosenUser varchar(40))
begin
	select DietName from diet_and_user where UserLogin=chosenUser;
end;$$
delimiter ;


delimiter $$
drop procedure if exists addNormalUser$$
create procedure addNormalUser (in userLogin varchar(40), in userPassword varchar(40), in userName varchar(30), in userSurname varchar(30), in userWeight float, in userHeight float, in userBirthDate date, in userMail varchar(50))
begin 
	insert into normal_users (userLogin, userName, userSurname, userWeight, userHeight, userBirthDate, userMail) values (userLogin, userName, userSurname, userWeight, userHeight, userBirthDate, userMail);
    insert into logData (loginValue, passwordValue, role) values (userLogin, userPassword, 'USER');
end;$$
delimiter ;


delimiter $$
drop procedure if exists addDietician$$
create procedure addDietician (in dieticianLogin varchar(40), in dieticianPassword varchar(40),in dieticianName varchar(30), in dieticianSurname varchar(30), in dieticianBirthDate date, in dieticianMail varchar(50))
begin 
	insert into dieticians (dieticianLogin, dieticianName, dieticianSurname, dieticianBirthDate, dieticianMail) values (dieticianLogin, dieticianName, dieticianSurname, dieticianBirthDate, dieticianMail);
    insert into logData (loginValue, passwordValue, role) values (dieticianLogin, dieticianPassword, 'DIETICIAN');
end;$$
delimiter ;



delimiter $$
drop procedure if exists addAdmin$$
create procedure addAdmin (in adminLogin varchar(40), in adminPassword varchar(40))
begin 
	insert into admin (adminLogin) values (adminLogin);
    insert into logData(loginValue, passwordValue, role) values(adminLogin, adminPassword, 'ADMIN');
end;$$
delimiter ;




delimiter $$
drop procedure if exists dropNormalUser$$
create procedure dropNormalUser(in userLoginInput varchar(40))
begin

	SET FOREIGN_KEY_CHECKS=0;
	delete from normal_users where userLogin=userLoginInput;
    SET FOREIGN_KEY_CHECKS=1;

end;$$
delimiter ;


delimiter $$
drop procedure if exists dropDietician$$
create procedure dropDietician(in dieticianLoginInput varchar(40))
begin

	SET FOREIGN_KEY_CHECKS=0;
	delete from dieticians where dieticianLogin=dieticianLoginInput;
    SET FOREIGN_KEY_CHECKS=1;

end;$$
delimiter ;


delimiter $$
drop procedure if exists dropAdmin$$
create procedure dropAdmin(in adminLoginInput varchar(40))
begin
	delete from admin where adminLogin=adminLoginInput;
end;$$
delimiter ;



delimiter $$
drop procedure if exists editNormalUser$$
create procedure editNormalUser(in userLoginIn varchar(40), in userPasswordIn varchar(40), in userNameIn varchar(30), in userSurnameIn varchar(30), in userWeightIn float, in userHeightIn float, in userBirthDateIn date, in userMailIn varchar(50))
begin

	update normal_users
    set userName=userNameIn,
    userSurname=userSurnameIn,
    userWeight=userWeightIn,
    userHeight=userHeightIn,
    userBirthDate=userBirthDateIn,
    userMail=userMailIn
    where userLogin=userLoginIn;
    
    update logData
    set passwordValue=userPasswordIn
    where loginValue=userLoginIn;
    
end;$$
delimiter ;


delimiter $$
drop procedure if exists editDietician$$
create procedure editDietician(in dieticianLoginIn varchar(40), in dieticianPasswordIn varchar(40), in dieticianNameIn varchar(30), in dieticianSurnameIn varchar(30), in dieticianBirthDateIn date, in dieticianMailIn varchar(50))
begin

	update dieticians
    set dieticianName=dieticianNameIn,
    dieticianSurname=dieticianSurnameIn,
    dieticianBirthDate=dieticianBirthDateIn,
    dieticianMail=dieticianMailIn
    where dieticianLogin=dieticianLoginIn;
    
    update logData
    set passwordValue=dieticianPasswordIn
    where loginValue=dieticianLoginIn;
    
end;$$
delimiter ;


delimiter $$
drop procedure if exists editAdmin$$
create procedure editAdmin(in adminLoginIn varchar(40), in adminPasswordIn varchar(40))
begin
    
    update logData
    set passwordValue=adminPasswordIn
    where loginValue=adminLoginIn;
    
end;$$
delimiter ;


delimiter $$
drop procedure if exists editLogData$$
create procedure editLogData(in _loginValue varchar(40), in _passwordvalue varchar(40))
begin

	update logData
    set passwordValue=_passwordvalue
    where loginValue=_loginValue;
    
end;$$
delimiter ;


delimiter $$
drop procedure if exists removeLogData$$
create procedure removeLogData(in _loginValue varchar(40), in _passwordvalue varchar(40), in _role enum('USER', 'DIETICIAN', 'ADMIN'))
begin
	delete from logData where loginValue=_loginValue and passwordValue=_passwordValue;
end;$$
delimiter ;

delimiter $$
drop procedure if exists removingAccount$$
create procedure removingAccount(IN `p_Name` VARCHAR(45))
begin 
SET `p_Name` := CONCAT('\'', REPLACE(TRIM(`p_Name`), CHAR(39), CONCAT(CHAR(92), CHAR(39))), '\'');
	SET @`sql` := CONCAT('DROP USER ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
end;$$
delimiter ;


delimiter $$
drop procedure if exists editingAccount$$
create procedure editingAccount(IN `p_Name` VARCHAR(45), IN `p_Passw` VARCHAR(200))
begin 
	SET `p_Name` := CONCAT('\'', REPLACE(TRIM(`p_Name`), CHAR(39), CONCAT(CHAR(92), CHAR(39))), '\''), 
	`p_Passw` := CONCAT('\'', REPLACE(`p_Passw`, CHAR(39), CONCAT(CHAR(92), CHAR(39))), '\'');
	SET @`sql` := CONCAT('SET PASSWORD FOR ', `p_Name`, '=', `p_Passw`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
end;$$
delimiter ;


delimiter $$
drop procedure if exists addingUserAndDieticianConnection$$
create procedure addingUserAndDieticianConnection (in _userLogin varchar(40), in _dieticianLogin varchar(40))
begin 
	insert into users_and_dieticians (UserLogin, DieticianLogin) values (_userLogin, _dieticianLogin);
end;$$
delimiter ;

delimiter $$
drop procedure if exists removingUserAndDieticianConnection$$
create procedure removingUserAndDieticianConnection (in _userLogin varchar(40), in _dieticianLogin varchar(40))
begin 
	delete from users_and_dieticians where UserLogin=_userLogin and DieticianLogin=_dieticianLogin;
end;$$
delimiter ;

delimiter $$
drop procedure if exists select_nutritional_plan_for_user$$
create procedure select_nutritional_plan_for_user(in _login varchar(40))
begin
	select nutritional_plan.name, nutritional_plan.energy, nutritional_plan.fat, nutritional_plan.of_which_saturates, nutritional_plan.carbohydrates, nutritional_plan.of_which_sugars, nutritional_plan.protein, nutritional_plan.salt from nutritional_plan inner join diet_and_user on nutritional_plan.name=diet_and_user.DietName where diet_and_user.UserLogin=_login;
end;$$
delimiter ;







