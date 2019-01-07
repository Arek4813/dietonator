delimiter $$
drop procedure if exists writeUsersForDietician$$
create procedure writeUsersForDietician (in chosenDietician int)
begin
	select UserId from users_and_dieticians where DieticianId=chosenDietician;
end;$$
delimiter ;


delimiter $$
drop procedure if exists writeDieticiansForUser$$
create procedure writeDieticiansForUser (in chosenUser int)
begin 
	select DieticianId from users_and_dieticians where UserId=chosenUser;
end;$$
delimiter ;


delimiter $$
drop procedure if exists writeDietsForUser$$
create procedure writeDietsForUser (in chosenUser int)
begin
	select DietId from diet_and_user where UserId=chosenUser;
end;$$
delimiter ;


delimiter $$
drop procedure if exists addNormalUser$$
create procedure addNormalUser (in userName varchar(30), in userSurname varchar(30), in userWeight int, in userHeight int, in userBirthDate date, in userMail varchar(50))
begin 
	insert into normal_users (userName, userSurname, userWeight, userHeight, userBirthDate, userMail) values (userName, userSurname, userWeight, userHeight, userBirthDate, userMail);
end;$$
delimiter ;


delimiter $$
drop procedure if exists addDietician$$
create procedure addDietician (in dieticianName varchar(30), in dieticianSurname varchar(30), in dieticianBirthDate date, in dieticianMail varchar(50))
begin 
	insert into dieticians (dieticianName, dieticianSurname, dieticianBirthDate, dieticianMail) values (dieticianName, dieticianSurname, dieticianBirthDate, dieticianMail);
end;$$
delimiter ;


delimiter $$
drop procedure if exists addLogData$$
create procedure addLogData (in loginValue varchar(40), in passwordValue varchar(40))
begin 
	insert into logData (loginValue, passwordValue) values (loginValue, passwordValue);
end;$$
delimiter ;



delimiter $$
drop procedure if exists addAdmin$$
create procedure addAdmin (in adminNickname varchar(50))
begin 
	insert into Admin (adminNickName) values (adminNickname);
end;$$
delimiter ;