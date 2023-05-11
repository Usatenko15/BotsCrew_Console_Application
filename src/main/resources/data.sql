CREATE TABLE lector (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL,
                        degree VARCHAR(255) NOT NULL,
                        salary DECIMAL(10, 2) NOT NULL
);

CREATE TABLE department (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            name VARCHAR(255) NOT NULL,
                            head_of_department_id INT,
                            FOREIGN KEY (head_of_department_id) REFERENCES lector(id)
);

CREATE TABLE lector_department (
                                   lector_id INT NOT NULL,
                                   department_id INT NOT NULL,
                                   PRIMARY KEY (lector_id, department_id),
                                   FOREIGN KEY (lector_id) REFERENCES lector(id),
                                   FOREIGN KEY (department_id) REFERENCES department(id)
);

-- Insert lectors
INSERT INTO lector (name, degree, salary) VALUES ('John Doe', 'PROFESSOR', 5000);
INSERT INTO lector (name, degree, salary) VALUES ('Jane Smith', 'ASSISTANT', 3000);
INSERT INTO lector (name, degree, salary) VALUES ('Michael Johnson', 'ASSOCIATE_PROFESSOR', 4000);
INSERT INTO lector (name, degree, salary) VALUES ('Sarah Anderson', 'ASSISTANT', 3200);
INSERT INTO lector (name, degree, salary) VALUES ('Robert Davis', 'PROFESSOR', 5500);
INSERT INTO lector (name, degree, salary) VALUES ('Emily Wilson', 'ASSOCIATE_PROFESSOR', 4200);

-- Insert departments
INSERT INTO department (name, head_of_department_id) VALUES ('Department 1', 1);
INSERT INTO department (name, head_of_department_id) VALUES ('Department 2', 2);
INSERT INTO department (name, head_of_department_id) VALUES ('Department 3', 3);
INSERT INTO department (name, head_of_department_id) VALUES ('Department 4', 4);
INSERT INTO department (name, head_of_department_id) VALUES ('Department 5', 5);

-- Set head of department
UPDATE department SET head_of_department_id = 1 WHERE id = 1;
UPDATE department SET head_of_department_id = 2 WHERE id = 2;
UPDATE department SET head_of_department_id = 3 WHERE id = 3;
UPDATE department SET head_of_department_id = 4 WHERE id = 4;
UPDATE department SET head_of_department_id = 5 WHERE id = 5;

-- Assign lectors to departments
INSERT INTO lector_department (lector_id, department_id) VALUES (1, 1);
INSERT INTO lector_department (lector_id, department_id) VALUES (1, 2);
INSERT INTO lector_department (lector_id, department_id) VALUES (2, 2);
INSERT INTO lector_department (lector_id, department_id) VALUES (2, 3);
INSERT INTO lector_department (lector_id, department_id) VALUES (3, 3);
INSERT INTO lector_department (lector_id, department_id) VALUES (3, 1);
INSERT INTO lector_department (lector_id, department_id) VALUES (4, 4);
INSERT INTO lector_department (lector_id, department_id) VALUES (5, 5);
INSERT INTO lector_department (lector_id, department_id) VALUES (6, 5);

