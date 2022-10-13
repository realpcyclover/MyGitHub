drop database test_db
CREATE DATABASE test_db
go
use test_db
go
CREATE TABLE Categories (
  categoryID varchar(10) primary key ,
  categoryName varchar(100)  ,
  [description] varchar(200)  
) 
select * from Categories

INSERT INTO Categories (categoryID, categoryName, description) VALUES
('C0101', 'Beverages', 'Soft drinks, coffees, teas, beers, and ales'),
('C0102', 'Condiments', 'Sweet and savory sauces, relishes, spreads, and seasonings'),
('C0103', 'Confections', 'Desserts, candies, and sweet breads'),
('C0104', 'Dairy Products', 'Cheeses'),
('C0105', 'Grains_Cereals', 'Breads, crackers, pasta, and cereal'),
('C0106', 'Meat_Poultry', 'Prepared meats'),
('C0107', 'Produce', 'Dried fruit and bean curd'),
('C0108', 'Seafood', 'Seaweed and fish');


CREATE TABLE Products (
  productID varchar(10)  primary key,
  categoryID varchar(10)  NULL,
  productName varchar(50)  NULL,
	price int  NULL,
  description varchar(200)  NULL
) 


INSERT INTO Products (productID, categoryID, productName, price, description) VALUES
('P0101', 'C0101', 'Chai', 18, 'Good product'),
('P01010', 'C0104', 'Ikura', 31, 'Good'),
('P01011', 'C0105', 'Queso Cabrales', 21, 'Good'),
('P01012', 'C0105', 'Queso Manchego La Pastora', 38, 'Good'),
('P0102', 'C0101', 'Chang', 19, 'best sell product'),
('P0103', 'C0101', 'Aniseed Syrup', 10, 'new produced');

CREATE TABLE Students (
  idstu varchar(100)  primary key,
  name varchar(max)  ,
  age int
) 
insert into Students values('STU1','Bach',1)


/* 
DECLARE @kill varchar(8000) = '';  
SELECT @kill = @kill + 'kill ' + CONVERT(varchar(5), session_id) + ';'  
FROM sys.dm_exec_sessions
WHERE database_id  = db_id('test_db')

EXEC(@kill);
*/

--MY SQL SCRIPT----------------------------------------------------------------------
create database MyInsuranceCard;
use MyInsuranceCard;
Create table Account(
gmailAccount nvarchar(100) PRIMARY KEY,
typeaccount nvarchar(50),
passwordaccount nvarchar(50)
);
ALTER TABLE account ADD active varchar(100);
insert into Account values('bsd03rd@gmail.com','admin','Songduc&*78');
create table Accountinfo(
gmailAccount nvarchar(100) PRIMARY KEY,
Fname nvarchar(50),
Lname nvarchar(50),
gender nvarchar(50),
balance  decimal(15,2),
PhoneNumber nvarchar(50),
img blob,          
createdateaccount nvarchar(50),
FOREIGN KEY (gmailAccount) REFERENCES Account(gmailAccount)
);
insert into Accountinfo(gmailAccount,Fname,Lname,gender,balance,PhoneNumber,createdateaccount) values('bsd03rd@gmail.com','Bach','Duc','male',100,'0966793672','10/9/2022');
ALTER TABLE accountinfo ADD verify varchar(100);
create table CityAddress(
IDCityAddress  nvarchar(100) PRIMARY KEY,
NameCityAddress  nvarchar(100)
);
create table DistrictAddress(
districkey int NOT NULL AUTO_INCREMENT PRIMARY KEY,
IDCityAddress nvarchar(100), 
IDDistrictAddress  nvarchar(100),
NameDistrictAddress  nvarchar(100),
FOREIGN KEY (IDCityAddress) REFERENCES CityAddress (IDCityAddress)
);
drop table DistrictAddress;
create table AddressAccount(
idAddressAccount int NOT NULL AUTO_INCREMENT PRIMARY KEY,
gmailAccount nvarchar(100), 
IDCityAddress nvarchar(100), 
IDDistrictAddress  nvarchar(100), 
StreetAddress nvarchar(100),
FOREIGN KEY (gmailAccount) REFERENCES Account (gmailAccount),
FOREIGN KEY (IDCityAddress) REFERENCES CityAddress (IDCityAddress)
);
create table RequestTable(
IDResquest nvarchar(100), 
gmailAccount nvarchar(100), 
RoleAccount nvarchar(100),
LevelRequest int,
TypeRequest nvarchar(100), 
Title nvarchar(100), 
ContentRequest nvarchar(100),
AttachmentsRequest blob,
StatusRequest nvarchar(100),
createdaterequest nvarchar(50)
);
create table InsuranceCard(
IdCard nvarchar(100), 
NameCard nvarchar(100), 
DescriptionCard nvarchar(100),
TypeCard nvarchar(100),
img blob, 
PriceByYear decimal(15,2)
);
ALTER TABLE InsuranceCard ADD active varchar(100);




insert into CityAddress(IDCityAddress,NameCityAddress) values(N'HN',N'Hà Nội');
insert into CityAddress(IDCityAddress,NameCityAddress) values(N'DN',N'Đà Nẵng');
insert into CityAddress(IDCityAddress,NameCityAddress) values(N'HCM',N'Hồ Chí Minh');
insert into DistrictAddress(IDCityAddress,IDDistrictAddress,NameDistrictAddress) values(N'HN',N'TX',N'Thanh Xuân');
insert into DistrictAddress(IDCityAddress,IDDistrictAddress,NameDistrictAddress) values(N'HN',N'TX',N'Đống Đa');
insert into DistrictAddress(IDCityAddress,IDDistrictAddress,NameDistrictAddress) values(N'HCM',N'Q1',N'Quận 1');
insert into DistrictAddress(IDCityAddress,IDDistrictAddress,NameDistrictAddress) values(N'HCM',N'Q2',N'Quận 2');
insert into DistrictAddress(IDCityAddress,IDDistrictAddress,NameDistrictAddress) values(N'DN',N'CL',N'Cẩm Lệ');
insert into DistrictAddress(IDCityAddress,IDDistrictAddress,NameDistrictAddress) values(N'DN',N'QT',N'Quốc Toản');
insert into AddressAccount(gmailAccount,IDCityAddress,IDDistrictAddress,StreetAddress) values('bsd03rd@gmail.com','HN','TX','50 khuong dinh thanh');
drop database InsuranceCard