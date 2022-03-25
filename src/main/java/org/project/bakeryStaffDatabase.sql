DROP TABLE IF EXISTS bakeryStaff;


CREATE TABLE bakeryStaff(staffID int NOT NULL AUTO_INCREMENT,
                         firstName VARCHAR(50),
                         lastName VARCHAR(50),
                         ratePerHour double,
                         workHours int,
                         email VARCHAR(50),
                         PRIMARY KEY (staffID));


INSERT INTO bakeryStaff VALUES (1, "Tanya","Martin", 10.8, 20, "tm@gmail.com");
INSERT INTO bakeryStaff VALUES (2, "Siya","Salekar", 11.8, 40, "ss@gmail.com");
INSERT INTO bakeryStaff VALUES (3, "Josh","Butler", 12.8, 40, "jb@gmail.com");
INSERT INTO bakeryStaff VALUES (4, "Claire","Martin", 12.8, 30, "cm@gmail.com");
INSERT INTO bakeryStaff VALUES (5, "Efe","Leonard", 10.8, 20, "el@gmail.com");
INSERT INTO bakeryStaff VALUES (6, "Aaron","McCabe", 12.8, 20, "am@gmail.com");
INSERT INTO bakeryStaff VALUES (7, "John","Stewart", 11.8, 30, "jost@gmail.com");
INSERT INTO bakeryStaff VALUES (8, "Jake","Cheruil", 11.8, 20, "jach@gmail.com");
INSERT INTO bakeryStaff VALUES (9, "Patrick","Donchev", 10.8, 40, "ptr@gmail.com");
INSERT INTO bakeryStaff VALUES (10, "Hayley","Dixon", 11.8, 30, "hd@gmail.com");
INSERT INTO bakeryStaff VALUES (11, "Ira","Thete", 10.8, 10, "it@gmail.com");