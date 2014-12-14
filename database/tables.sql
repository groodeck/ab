-- nie testowane
create table Users(
	userId integer(10) not null,
	username varchar(45) not null,
  	password varchar(45) not null,
  	name varchar(60) not null,
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
);

create table contractDuration(
 	duration_idn varchar(100) not null  PRIMARY KEY,
 	duration_description varchar(100) not null,
);

create table contract(
 	contractId integer not null IDENTITY PRIMARY KEY,
 	contractIdn varchar(100) not null,
	contractStatus varchar(100) not null,
	contractSignDate date,
	contractActivationDate date,
	contractEndDate date,
	contractPeriod varchar(100),
	contractPackageId integer,
	contractSubscription decimal,
	installationFee decimal,
	activationFee decimal,
	subscriberId integer,
	active bit not null default 0,
	userId integer,
);

create table subscriber(
 	subscriberId integer not null IDENTITY PRIMARY KEY,
 	subscriberIdn varchar(100) not null,
	clientType varchar(50) not null,
	name varchar(100),
	surname varchar(100),
	companyName varchar(100),
	clientIdNumber varchar(30),
	pesel varchar(15),
	regon varchar(15),
	nip varchar(15),
	balance decimal,
	comment varchar(200),
	additionalComment varchar(200),
);

create table contractPackage(
 	packageId integer not null IDENTITY PRIMARY KEY,
 	packageName varchar(100) not null,
	packageSubscription decimal,
	activationFee decimal,
	installationFee decimal,
);

create table contact(
	contactId integer not null IDENTITY PRIMARY KEY,
 	contactType varchar(10) not null,
 	contact varchar(100) not null,
 	subscriberId integer,
);

create table address(
	addressId integer not null IDENTITY PRIMARY KEY,
 	addressType varchar(15) not null,
 	city varchar(100),
 	zipCode varchar(6),
 	street varchar(100),
 	houseNo varchar(10),
 	apartmentNo varchar(10),
 	subscriberId integer,
);

create table device(
	deviceId integer not null IDENTITY PRIMARY KEY,
 	deviceType varchar(15) not null,
 	serialNumber varchar(30),
 	mac varchar(20),
 	ip varchar(20),
 	contractId integer,
);

create table service(
	serviceId integer not null IDENTITY PRIMARY KEY,
 	serviceName varchar(50) not null,
 	vat varchar(30) not null,
 	subscriptionNet decimal not null,
 	packageId integer,
);