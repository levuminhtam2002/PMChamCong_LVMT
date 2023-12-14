CREATE TABLE employee(
	id INT PRIMARY KEY AUTO_INCREMENT,
	code VARCHAR(20),
	NAME NVARCHAR(2048)
);

CREATE TABLE department(
   id INT PRIMARY KEY AUTO_INCREMENT,
   NAME NVARCHAR(2048),

);
CREATE TABLE employee_department (
	employee_id INT  REFERENCES employee(id),
	department_id INT REFERENCES department(id),
	position INT DEFAULT 1
    -- 1 : Worker, 2 - Officer, 3 - DepartmentManager, 4 - HRManager
);

CREATE TABLE workers_records(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	employee_code VARCHAR(20),
	shift1 DECIMAL(10,2) DEFAULT 0.0,
	shift2 DECIMAL(10,2) DEFAULT 0.0,
	shift3 DECIMAL(10,2) DEFAULT 0.0,
	record_date DATE
);

CREATE TABLE officers_records(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	employee_code VARCHAR(20),
	morningSession BINARY,
	afternoonSession BINARY,
	hoursLate DECIMAL(10,2) DEFAULT 0.0,
	hoursEarlyLeave DECIMAL(10,2) DEFAULT 0.0,
	record_date DATE
);
