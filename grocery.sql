-- Project Database
-- Katherine Beltran 
-- 100939080

-- SQL Grocery Database

-- Drop old versions
drop table if exists products;
drop table if exists departments;
drop table if exists employees;
drop table if exists equipments;
drop table if exists equipment_employee;

BEGIN TRANSACTION;
-- Create the Database Tables
-------------------------------------------------------------------------------------------------------------------------
create table products(
	productName text primary key not null,
	departmentCode text not null references department(departmentCode), 
	brand text not null,
	cost integer not null,
	country text not null, 
	instock text not null,
	quantity integer not null
);

create table departments(
	departmentCode text primary key not null,
	supervisorID integer not null references employees(employeeID)
);

create table employees(
	employeeID integer primary key not null,
	departmentCode text not null references department(departmentCode),
	name text not null,
	phoneNum integer not null
);

create table equipments(
	equipID integer not null,
	equipName text not null,
	primary key(equipID, equipName)
);

create table equipment_employee(
	employeeID integer not null references employees(employeeID),
	equipID integer not null references equipment(equipID),
	primary key(employeeID, equipID)
);

-- Inserting Data
-- =============================================================

-- Insert some Products
-------------------------------------------------------------------------------------------------------------------------
-- Produce items
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('bananas', 'PROD', 'Chiquita', 0.99, 'US', 'Y', 100);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('spaghetti squash', 'PROD', 'Dole', 2.25, 'US', 'Y', 75);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('romaine lettuce', 'PROD', 'Dole', 2.99, 'US', 'Y', 65);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('oranges', 'PROD','Dole', 3.99,  'US', 'Y', 80);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('green peppers', 'PROD','Azteca', 2.99,  'MEX', 'Y', 50);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('mushrooms', 'PROD','Carleton',  2.99, 'CAN', 'Y', 200);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('garlic', 'PROD', 'Cesares', 0.99, 'CAN', 'Y', 48);

-- Grocery Items
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('vegetables thins', 'GROC', 'Christie', 3.49, 'US', 'Y', 15);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('pasta sauce', 'GROC', 'Classico', 2.99, 'CAN', 'Y', 2);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('mayonnaise', 'GROC', 'Hellmann', 7.49, 'US', 'N', 0);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('ketchup', 'GROC', 'Heinz', 2.99, 'US', 'Y', 45);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('mustard', 'GROC', 'Frenchs', 2.99,'US', 'N', 0);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('caesar dressing', 'GROC', 'Renee', 2.49, 'CAN', 'N', 0);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('BBQ sauce', 'GROC', 'Diana', 2.49, 'US', 'Y', 30);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('greek salad dressing', 'GROC', 'Kraft', 2.49,'US', 'Y', 27);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('Kit Kat', 'GROC', 'Nestle', 2.49, 'UK', 'Y', 10);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('sugar', 'GROC', 'Lactic', 3.49, 'US', 'Y', 20);

-- Bakery Items
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('pita', 'BAKE', 'Hanna', 0.99, 'CAN', 'Y', 25);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('croissants', 'BAKE', 'in-house', 2.99, 'CAN', 'Y', 10);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('vanilla cake', 'BAKE', 'in-house', 5.99, 'CAN', 'Y', 2);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('tortilla', 'BAKE', 'Casa Medosa', 2.99, 'MEX', 'Y', 20);

-- Diary and Eggs
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('eggs', 'DNE', 'Burnbrae', 3.99, 'CAN', 'Y', 100);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('yogurt', 'DNE', 'Astro', 2.99,'CAN', 'N', 0);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('cheese', 'DNE','Black Diamond', 7.49, 'CAN', 'Y', 25);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('coffee creamer', 'DNE', 'International Delight', 3.99, 'US', 'N', 0);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('margarine', 'DNE', 'Lactantia', 1.99, 'US', 'Y', 30);

-- Deli
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('spinach dip', 'DELI', 'Fontaine', 4.39, 'CAN', 'Y', 15);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('smoked ham', 'DELI', 'Brandt', 3.03, 'CAN', 'N', 0);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('roast beef', 'DELI', 'Brandt', 4.09, 'CAN', 'Y', 10);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('gouda cheese', 'DELI', 'Amul', 6.49, 'CAN', 'Y', 15);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('baby bell', 'DELI', 'Bel Group', 2.99, 'FRA', 'N', 0);

-- Frozen
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('popsicles', 'FRO', 'Fruttare', 2.99, 'US', 'Y', 10);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('chocolate chip cookie dough', 'FRO', 'Breyers', 2.99, 'US', 'N', 0);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('neopolitan', 'FRO', 'Chapman', 3.99, 'CAN', 'Y', 15);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('strawberry slices', 'FRO', 'PC', 5.99, 'CAN', 'N', 0);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('baby broccolli', 'FRO', 'PC', 4.99, 'CAN', 'N', 0);
insert into products (productName, departmentCode, brand, cost, country, instock, quantity) values ('pizza', 'FRO', 'Delissio', 3.49, 'CAN', 'Y', 15);

-------------------------------------------------------------------------------------------------------------------------
-- Insert some departments
insert into departments(departmentCode, supervisorID) values ('PROD', 101);
insert into departments(departmentCode, supervisorID) values ('GROC', 201);
insert into departments(departmentCode, supervisorID) values ('BAKE', 301);
insert into departments(departmentCode, supervisorID) values ('DNE', 401);
insert into departments(departmentCode, supervisorID) values ('DELI', 501);
insert into departments(departmentCode, supervisorID) values ('FROZ', 601);

-------------------------------------------------------------------------------------------------------------------------
-- Insert some employees
insert into employees(employeeID, departmentCode, name, phoneNum) values (101, 'PROD', 'Kim Possible', 4161112222);
insert into employees(employeeID, departmentCode, name, phoneNum) values (102, 'PROD', 'Ron Stoppable', 4162223333);
insert into employees(employeeID, departmentCode, name, phoneNum) values (103, 'PROD', 'Rufus Stoppable', 4163334444);
insert into employees(employeeID, departmentCode, name, phoneNum) values (104, 'PROD', 'Wade Genuis', 4164445555);
insert into employees(employeeID, departmentCode, name, phoneNum) values (105, 'PROD', 'Dr. Drakken', 6134567890); 
insert into employees(employeeID, departmentCode, name, phoneNum) values (106, 'PROD', 'Shego Go', 6131012002);

insert into employees(employeeID, departmentCode, name, phoneNum) values (201, 'GROC', 'Harry Potter', 6131234567);
insert into employees(employeeID, departmentCode, name, phoneNum) values (202, 'GROC', 'Hermione Granger', 4165556666);
insert into employees(employeeID, departmentCode, name, phoneNum) values (203, 'GROC', 'Ron Weasley', 6130002215);
insert into employees(employeeID, departmentCode, name, phoneNum) values (204, 'GROC', 'Albus Dumbledore', 4166667777);
insert into employees(employeeID, departmentCode, name, phoneNum) values (205, 'GROC', 'Severus Snape', 6131111111);
insert into employees(employeeID, departmentCode, name, phoneNum) values (206, 'GROC', 'Neville Longbottom', 6132200101);
insert into employees(employeeID, departmentCode, name, phoneNum) values (207, 'GROC', 'Ginny Weasley', 6137279696);
insert into employees(employeeID, departmentCode, name, phoneNum) values (208, 'GROC', 'Rubeus Hagrid', 2896164569);
insert into employees(employeeID, departmentCode, name, phoneNum) values (209, 'GROC', 'Minerva McGonagall', 6136335412);

insert into employees(employeeID, departmentCode, name, phoneNum) values (301, 'BAKE', 'Anikin Skywalker', 4162120000);
insert into employees(employeeID, departmentCode, name, phoneNum) values (302, 'BAKE', 'Obi-Wan Kenobi', 6139999999);
insert into employees(employeeID, departmentCode, name, phoneNum) values (303, 'BAKE', 'Padme Amidala', 9055023469);

insert into employees(employeeID, departmentCode, name, phoneNum) values (401, 'DNE', 'Spencer Reid', 9056547890);
insert into employees(employeeID, departmentCode, name, phoneNum) values (402, 'DNE', 'Penelop Garcia', 4162221515);
insert into employees(employeeID, departmentCode, name, phoneNum) values (403, 'DNE', 'Derek Morgan', 6138884561);
insert into employees(employeeID, departmentCode, name, phoneNum) values (404, 'DNE', 'JJ Jareau', 2896011025);
insert into employees(employeeID, departmentCode, name, phoneNum) values (405, 'DNE', 'Emily Presentiss', 9055478325);

insert into employees(employeeID, departmentCode, name, phoneNum) values (501, 'DELI', 'Clark Kent', 2895476666);
insert into employees(employeeID, departmentCode, name, phoneNum) values (502, 'DELI', 'Bruce Wayne', 6135498888);
insert into employees(employeeID, departmentCode, name, phoneNum) values (503, 'DELI', 'Martian Manhunter', 9056498589);
insert into employees(employeeID, departmentCode, name, phoneNum) values (504, 'DELI', 'John Stewart', 4161233000);

insert into employees(employeeID, departmentCode, name, phoneNum) values (601, 'FROZ', 'Arthur Read', 9056663589);
insert into employees(employeeID, departmentCode, name, phoneNum) values (602, 'FROZ', 'Buster Baxter', 6130002222);
insert into employees(employeeID, departmentCode, name, phoneNum) values (603, 'FROZ', 'Francine Frensky', 4165056161);
insert into employees(employeeID, departmentCode, name, phoneNum) values (604, 'FROZ','Binky Barnes', 2893219510);
insert into employees(employeeID, departmentCode, name, phoneNum) values (605, 'FROZ','Alan Powers', 2895477011);

-------------------------------------------------------------------------------------------------------------------------
-- Insert some equipment

insert into equipments(equipID, equipName) values (1, 'bailer');
insert into equipments(equipID, equipName) values (2, 'freezer');
insert into equipments(equipID, equipName) values (3, 'fridge');
insert into equipments(equipID, equipName) values (4, 'RFID');
insert into equipments(equipID, equipName) values (5, 'meat slicer');
insert into equipments(equipID, equipName) values (6, 'oven');

-------------------------------------------------------------------------------------------------------------------------
-- Equipment to Employee relation
-- Bailer
insert into equipment_employee(employeeID, equipID) values (101, 1);
insert into equipment_employee(employeeID, equipID) values (102, 1);
insert into equipment_employee(employeeID, equipID) values (103, 1);
insert into equipment_employee(employeeID, equipID) values (104, 1);	
insert into equipment_employee(employeeID, equipID) values (105, 1);
insert into equipment_employee(employeeID, equipID) values (106, 1);

insert into equipment_employee(employeeID, equipID) values (201, 1);
insert into equipment_employee(employeeID, equipID) values (202, 1);	
insert into equipment_employee(employeeID, equipID) values (203, 1);
insert into equipment_employee(employeeID, equipID) values (204, 1);
insert into equipment_employee(employeeID, equipID) values (205, 1);
insert into equipment_employee(employeeID, equipID) values (206, 1);
insert into equipment_employee(employeeID, equipID) values (207, 1);
insert into equipment_employee(employeeID, equipID) values (208, 1);
insert into equipment_employee(employeeID, equipID) values (209, 1);

insert into equipment_employee(employeeID, equipID) values (401, 1);
insert into equipment_employee(employeeID, equipID) values (402, 1);
insert into equipment_employee(employeeID, equipID) values (403, 1);
insert into equipment_employee(employeeID, equipID) values (404, 1);
insert into equipment_employee(employeeID, equipID) values (405, 1);

-- Freezer
insert into equipment_employee(employeeID, equipID) values (601, 2);
insert into equipment_employee(employeeID, equipID) values (602, 2);
insert into equipment_employee(employeeID, equipID) values (603, 2);
insert into equipment_employee(employeeID, equipID) values (604, 2);
insert into equipment_employee(employeeID, equipID) values (605, 2);

-- Fridge
insert into equipment_employee(employeeID, equipID) values (101, 3);
insert into equipment_employee(employeeID, equipID) values (102, 3);
insert into equipment_employee(employeeID, equipID) values (103, 3);
insert into equipment_employee(employeeID, equipID) values (104, 3);	
insert into equipment_employee(employeeID, equipID) values (105, 3);
insert into equipment_employee(employeeID, equipID) values (106, 3);

insert into equipment_employee(employeeID, equipID) values (401, 3);
insert into equipment_employee(employeeID, equipID) values (402, 3);
insert into equipment_employee(employeeID, equipID) values (403, 3);
insert into equipment_employee(employeeID, equipID) values (404, 3);
insert into equipment_employee(employeeID, equipID) values (405, 3);

insert into equipment_employee(employeeID, equipID) values (501, 3);
insert into equipment_employee(employeeID, equipID) values (502, 3);
insert into equipment_employee(employeeID, equipID) values (503, 3);
insert into equipment_employee(employeeID, equipID) values (504, 3);

-- RFID 
insert into equipment_employee(employeeID, equipID) values (101, 4);
insert into equipment_employee(employeeID, equipID) values (201, 4);
insert into equipment_employee(employeeID, equipID) values (301, 4);	
insert into equipment_employee(employeeID, equipID) values (401, 4);
insert into equipment_employee(employeeID, equipID) values (501, 4);
insert into equipment_employee(employeeID, equipID) values (601, 4);	

-- Meat Slicer
insert into equipment_employee(employeeID, equipID) values (501, 5);
insert into equipment_employee(employeeID, equipID) values (502, 5);
insert into equipment_employee(employeeID, equipID) values (503, 5);
insert into equipment_employee(employeeID, equipID) values (504, 5);

-- Oven
insert into equipment_employee(employeeID, equipID) values (301, 6);
insert into equipment_employee(employeeID, equipID) values (302, 6);
insert into equipment_employee(employeeID, equipID) values (303, 6);

-------------------------------------------------------------------------------------------------------------------------
-- end inserting data 
COMMIT TRANSACTION;