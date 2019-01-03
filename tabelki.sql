create table normal_users (
	userId int not null primary key auto_increment, 
    userName varchar(30) not null, 
    userSurname varchar(30) not null, 
    userWeight int not null, 
    userHeight int not null, 
    userBirthDate date not null, 
    userMail varchar(50) not null);

create table dieticians (
	dieticianId int not null primary key auto_increment, 
    dieticianName varchar(30) not null, 
    dieticianSurname varchar(30) not null, 
    dieticianBirthDate date not null, 
    dieticianMail varchar(50) not null);

create table users_and_dieticians (
	UserId int not null, 
    DieticianId int not null, 
    foreign key(UserId) references normal_users(userId), 
    foreign key(DieticianId) references dieticians(dieticianId),
    primary key(UserId, DieticianId));
    
create table admin (
	adminId int not null primary key auto_increment,
    adminNickName varchar(50) not null);
    
create table logData (
	loginValue varchar(40) not null primary key,
    passwordValue varchar(40) not null);
    
create table diet_and_user (
	UserId int not null,
    DietId int not null,
    DieticianId int not null,
    foreign key(UserId) references normal_users(userId),
    foreign key(DietId) references nutritional_plan(id),
    foreign key(DieticianId) references dieticians(dieticianId),
    primary key(UserId, DietId));
    
    


