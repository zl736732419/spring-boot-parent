DROP TABLE IF EXISTS users123;
CREATE TABLE users123 (
  id INT PRIMARY KEY AUTO_INCREMENT, 
  username VARCHAR(50), 
  pwd VARCHAR(32), 
  age INT,
  is_delete INT NOT NULL DEFAULT 0,
  create_time TIMESTAMP NOT NULL,
  update_time TIMESTAMP NOT NULL);