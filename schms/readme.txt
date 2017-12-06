Steps to run the application
1) Create database called schms

2)After running the application, automatically will be created an admin user  with credentials:
username: omar
password: omar


3) After the application started running, execute the following script in MySql under the schms database
It will create a trigger which will run every time a new professor is inserted into the database

DELIMITER 
$$
USE `schms`
$$
CREATE 
TRIGGER `schms`.`PROFESSOR_ROLE` 
AFTER INSERT ON `schms`.`professor`
FOR EACH ROW
BEGIN 
INSERT INTO user_role(role_id,user_id) 
VALUES(1,NEW.id);
END$$

4) After login, start creating Professors, Courses, etc...


