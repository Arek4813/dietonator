create table normal_users (
	userLogin varchar(40) not null primary key, 
    userName varchar(30) not null, 
    userSurname varchar(30) not null, 
    userWeight float not null, 
    userHeight float not null, 
    userBirthDate date not null, 
    userMail varchar(50) not null);

create table dieticians (
	dieticianLogin varchar(40) not null primary key, 
    dieticianName varchar(30) not null, 
    dieticianSurname varchar(30) not null,
    dieticianBirthDate date not null, 
    dieticianMail varchar(50) not null);

create table users_and_dieticians (
	UserLogin varchar(40) not null, 
    DieticianLogin varchar(40) not null, 
    foreign key(UserLogin) references normal_users(userLogin), 
    foreign key(DieticianLogin) references dieticians(dieticianLogin),
    primary key(UserLogin, DieticianLogin));
    
create table admin (
	adminLogin varchar(40) not null primary key);
    
create table logData (
	loginValue varchar(40) not null primary key,
    passwordValue varchar(40) not null,
    role enum('USER', 'DIETICIAN', 'ADMIN'));
    /*foreign key(loginValue) references normal_users(userLogin),
    foreign key(loginValue) references dieticians(dieticianLogin),
    foreign key(loginValue) references admin(adminLogin));*/
    
create table diet_and_user (
	UserLogin varchar(40) not null,
    DietName varchar(30) not null,
    DieticianLogin varchar(40) not null,
    foreign key(UserLogin) references normal_users(userLogin),
    foreign key(DietName) references nutritional_plan(name),
    foreign key(DieticianLogin) references dieticians(dieticianLogin),
    primary key(UserLogin, DietName));
    
    


