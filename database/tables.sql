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
	activationFeeNet numeric(12,2),
	activationFeeVatRate varchar(30),
	activationFeeVat numeric(12,2),
	activationFeeGross numeric(12,2),
	installationFeeNet numeric(12,2),
	installationFeeVatRate varchar(30),
	installationFeeVat numeric(12,2),
	installationFeeGross numeric(12,2),
	disposableFeePaid bit,
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
	packageSubscription numeric(12,2),
	activationFeeNet numeric(12,2),
	activationFeeVatRate varchar(30),
	activationFeeVat numeric(12,2),
	activationFeeGross numeric(12,2),
	installationFeeNet numeric(12,2),
	installationFeeVatRate varchar(30),
	installationFeeVat numeric(12,2),
	installationFeeGross numeric(12,2),
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
 	vatRate integer,
 	vat decimal not null,
 	subscriptionNet decimal not null,
 	subscriptionGross decimal not null,
 	disposable bit not null,
 	packageId integer,
);

create table invoice(
	invoiceId integer not null IDENTITY PRIMARY KEY,
	contractId integer,
 	settlementPeriodStart date not null,
 	settlementPeriodEnd date not null,
 	invoiceNumber varchar(30) not null,
 	createDate date,
 	receiveDate date,
 	netAmount decimal,
 	vatAmount decimal,
 	grossAmount decimal,
 	grossAmountWords varchar(150),
 	paymentDate date,
 	paid bit,
);

create table invoiceRecord(
	invoiceRecordId integer not null IDENTITY PRIMARY KEY,
	serviceName varchar(100) not null,
	quantity integer,
 	netPrice decimal,
 	netAmount decimal,
 	vatRate integer,
 	vatAmount decimal,
 	grossAmount decimal,
 	invoiceId integer,
);

create table invoiceContent(
 	invoiceId integer not null IDENTITY PRIMARY KEY,
	invoiceHtml clob not null,
);
