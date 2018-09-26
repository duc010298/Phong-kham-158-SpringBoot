-- create database
drop database clinic;
create database clinic CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
use clinic;

-- user manager
create table app_user (
  user_id           BIGINT not null primary key AUTO_INCREMENT,
  user_name         VARCHAR(36) not null unique,
  encryted_password VARCHAR(128) not null
);
create table app_role (
  role_id   BIGINT not null primary key,
  role_name VARCHAR(30) not null unique
);
create table user_role (
  id      BIGINT not null primary key AUTO_INCREMENT,
  user_id BIGINT not null,
  role_id BIGINT not null
);
alter table user_role add constraint user_role_uk unique (user_id, role_id);
alter table user_role add constraint user_role_fk1 foreign key (user_id) references app_user (user_id);
alter table user_role add constraint user_role_fk2 foreign key (role_id) references app_role (role_id);

--
insert into app_user (user_id, user_name, encryted_password)
values (null, 'duc010298', '$2a$10$hjBz774Yg4Fff44DYseK4.w4p27w2enR0W.QxSxlIXA.TcxS2bYV.');
--
insert into app_role (role_id, role_name)
values (1, 'ROLE_ADMIN');

insert into app_role (role_id, role_name)
values (2, 'ROLE_MASTER');

insert into app_role (role_id, role_name)
values (3, 'ROLE_MEMBER');
--
insert into user_role (id, user_id, role_id)
values (null, 1, 1);

insert into user_role (id, user_id, role_id)
values (null, 1, 2);

insert into user_role (id, user_id, role_id)
values (null, 1, 3);
--

-- manager clinic
CREATE TABLE Customer (
	Id INT PRIMARY KEY AUTO_INCREMENT,
	Name VARCHAR(250),
	NameS VARCHAR(250),
	YOB INT,
	AddressCus VARCHAR(250),
	AddressCusS VARCHAR(250),
	DayVisit DATE,
	ExpectedDOB DATE,
	Result VARCHAR(250),
	Note VARCHAR(250),
	Report MEDIUMTEXT
);
CREATE TABLE Customer_Hidden (
  Id INT PRIMARY KEY AUTO_INCREMENT,
  Name VARCHAR(250),
	YOB INT,
	AddressCus VARCHAR(250),
	DayVisit DATE,
	Result VARCHAR(250),
	Report MEDIUMTEXT
);
CREATE TABLE UltraSoundResult
(
	ID INT PRIMARY KEY AUTO_INCREMENT,
	Name VARCHAR(100) NOT NULL,
	OrderNumber INT NOT NULL,
	Content MEDIUMTEXT
);