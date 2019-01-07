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


select * from normal_users;
select * from dieticians;

insert into normal_users(userName, userSurname, userWeight, userHeight, userBirthDate, userMail) values ('Name', 'Surname', 100, 205, "2000-05-08", 'mail@op.pl');
insert into dieticians(dieticianName, dieticianSurname, dieticianBirthDate, dieticianMail) values ('Name', 'Surname', "1980-02-03", 'tre@l.ol');


