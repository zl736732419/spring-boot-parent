-- CREATE SCHEMA
DROP TABLE IF EXISTS users3;
CREATE TABLE users3 (
  id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50),
  pwd VARCHAR(32),
  age INT,
  is_delete INT NOT NULL DEFAULT 0,
  create_time TIMESTAMP NOT NULL,
  update_time TIMESTAMP NOT NULL);

-- LOAD INIT DATA
INSERT INTO users3(username, pwd, age, is_delete, create_time, update_time)
VALUES ('xiaolian', "123456", 25, 0, now(), now());