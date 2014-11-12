-- nie testowane
create table Users(
	username varchar(45) not null,
  	password varchar(45) not null,
  	enabled tinyint(3) not null
)

-- nie testowane
create table user_roles(
  	user_role_id integer(10) not null,
  	username varchar(45) not null,
  	role varchar(45) not null
 )

create table city(
 	city_idn varchar(100) not null  PRIMARY KEY,
 	city_description varchar(100) not null,
)