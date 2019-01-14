delimiter $$ 
drop trigger if exists normalUsersProperValuesInsert$$
create trigger normalUsersProperValuesInsert
	before insert on normal_users
	for each row
	begin 
	
		if(new.userWeight<=0) then
			signal sqlstate '40000'
            set message_text = 'Weight must be greater than 0';
		end if;
        
        if(new.userHeight<=0) then
			signal sqlstate '40000'
            set message_text = 'Height must be greater than 0';
		end if;
        
        if((new.userBirthDate>curdate()) or (new.userBirthDate<"1880-01-01")) then
			signal sqlstate '40000'
            set message_text = 'Bad value of birth date';
		end if;
        
        if(new.userMail not like concat('%', '@', '%', '.', '%')) then
			signal sqlstate '40000'
            set message_text = 'Bad format of mail';
		end if;

	end;$$
delimiter ;



delimiter $$ 
drop trigger if exists normalUsersProperValuesUpdate$$
create trigger normalUsersProperValuesUpdate
	before update on normal_users
	for each row
	begin 
	
		if(new.userWeight<=0) then
			signal sqlstate '40000'
            set message_text = 'Weight must be greater than 0';
		end if;
        
        if(new.userHeight<=0) then
			signal sqlstate '40000'
            set message_text = 'Height must be greater than 0';
		end if;
        
        if((new.userBirthDate>curdate()) or (new.userBirthDate<"1880-01-01")) then
			signal sqlstate '40000'
            set message_text = 'Bad value of birth date';
		end if;
        
        if(new.userMail not like concat('%', '@', '%', '.', '%')) then
			signal sqlstate '40000'
            set message_text = 'Bad format of mail';
		end if;

	end;$$
delimiter ;



/** above triggers for normalUsers, below for dieticians**/




delimiter $$ 
drop trigger if exists dieticiansProperValuesInsert$$
create trigger dieticiansProperValuesInsert
	before insert on dieticians
	for each row
	begin 	
		
        if((new.dieticianBirthDate>curdate()) or (new.dieticianBirthDate<"1880-01-01")) then
			signal sqlstate '40000'
            set message_text = 'Bad value of birth date';
		end if;
        
        if(new.dieticianMail not like concat('%', '@', '%', '.', '%')) then
			signal sqlstate '40000'
            set message_text = 'Bad format of mail';
		end if;

	end;$$
delimiter ;





delimiter $$ 
drop trigger if exists dieticiansProperValuesUpdate$$
create trigger dieticiansProperValuesUpdate
	before update on dieticians
	for each row
	begin 	
		
        if((new.dieticianBirthDate>curdate()) or (new.dieticianBirthDate<"1880-01-01")) then
			signal sqlstate '40000'
            set message_text = 'Bad value of birth date';
		end if;
        
        if(new.dieticianMail not like concat('%', '@', '%', '.', '%')) then
			signal sqlstate '40000'
            set message_text = 'Bad format of mail';
		end if;

	end;$$
delimiter ;






/* next trigger package */



delimiter $$ 
drop trigger if exists removingUser$$
create trigger removingUser
	before delete on normal_users
	for each row
	begin 	
		delete from logData where loginValue=old.userLogin;
        delete from users_and_dieticians where UserLogin=old.userLogin;
        delete from diet_and_user where UserLogin=old.userLogin;
	end;$$
delimiter ;

delimiter $$ 
drop trigger if exists removingDietician$$
create trigger removingDietician
	before delete on dieticians
	for each row
	begin 	
		delete from logData where loginValue=old.dieticianLogin;
        delete from users_and_dieticians where DieticianLogin=old.dieticianLogin;
	end;$$
delimiter ;

delimiter $$ 
drop trigger if exists removingAdmin$$
create trigger removingAdmin
	before delete on admin
	for each row
	begin 	
		delete from logData where loginValue=old.adminLogin;
	end;$$
delimiter ;

delimiter $$ 
drop trigger if exists removingLogData$$
create trigger removingLogData
	before delete on logData
	for each row
	begin 	
		if(old.role='USER') then
			delete from normal_users where userLogin=old.loginValue;
		end if;
        if(old.role='DIETICIAN') then
			delete from dieticians where dieticianLogin=old.loginValue;
		end if;
        if(old.role='ADMIN') then
			delete from admin where adminLogin=old.loginValue;
		end if;	
	end;$$
delimiter ;

delimiter $$ 
drop trigger if exists editingUser$$
create trigger editingUser
	after update on normal_users
	for each row
	begin 	
		SET FOREIGN_KEY_CHECKS=0;
		
        update logData
        set loginValue=new.userLogin
        where loginValue=old.userLogin;
        
        update users_and_dieticians
        set UserLogin=new.userLogin
        where UserLogin=old.userLogin;
        
        update diet_and_user
		set UserLogin=new.userLogin
        where UserLogin=old.userLogin;
        
		SET FOREIGN_KEY_CHECKS=1;      	
	end;$$
delimiter ;


delimiter $$ 
drop trigger if exists editingDietician$$
create trigger editingDietician
	after update on dieticians
	for each row
	begin 	
		SET FOREIGN_KEY_CHECKS=0;
        
		update logData
        set loginValue=new.dieticianLogin
        where loginValue=old.dieticianLogin;
        
        update users_and_dieticians
        set UserLogin=new.dieticianLogin
        where UserLogin=old.dieticianLogin;
        
        SET FOREIGN_KEY_CHECKS=1;
	end;$$
delimiter ;

delimiter $$ 
drop trigger if exists editingAdmin$$
create trigger editingAdmin
	after update on admin
	for each row
	begin 	
		update logData
        set loginValue=new.adminLogin
        where loginValue=old.adminLogin;
	end;$$
delimiter ;

delimiter $$ 
drop trigger if exists editingLogData$$
create trigger editingLogData
	after update on logData
	for each row
	begin 	
		if(old.role='USER') then
			update normal_users
            set userLogin=new.loginValue
            where userLogin=old.loginValue;
		end if;
        if(old.role='DIETICIAN') then
			update dieticians
            set dieticianLogin=new.loginValue
            where dieticianLogin=old.loginValue;
		end if;
        if(old.role='ADMIN') then
			update admin
            set adminLogin=new.loginValue
            where adminLogin=old.loginValue;
		end if;	
	end;$$
delimiter ;











SET FOREIGN_KEY_CHECKS=1;

select * from normal_users;
select * from logData;
select * from users_and_dieticians;
select * from dieticians;
select * from admin;

delete from logData where loginValue='dietlogin';
delete from normal_users where userLogin='loginuser2';
delete from dieticians where dieticianLogin='logindiet2';


insert into dieticians (dieticianLogin, dieticianName, dieticianSurname, dieticianBirthDate, dieticianMail) values ('login123', 'imie', 'nazw', "2000-09-09", 'emial@op.pl');
insert into logData values ('login', 'haslo', 'DIETICIAN');
insert into users_and_dieticians values ('UserLogin', 'login');
insert into normal_users(userLogin, userName, userSurname, userWeight, userHeight, userBirthDate, userMail) values ('UserLogin123', 'Name', 'Surname', 100, 205, "2000-05-08", 'mail@op.pl');


update dieticians
set dieticianLogin='dietlogin'
where dieticianLogin='zmienionylogin';

update admin
set adminLogin='zmienionyloginadmina'
where adminLogin='loginadmin';