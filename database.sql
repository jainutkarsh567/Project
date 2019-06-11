CREATE SCHEMA `user_database`;
create table user_database.user_info(id int NOT NULL, uname  varchar(20) NOT NULL, username varchar(20) NOT NULL,password varchar(30), age int, gender varchar(20), PRIMARY KEY(id));

create table user_database.user_credentials(id int NOT NULL, password varchar(200), FOREIGN KEY(id) references user_info(id)); 
