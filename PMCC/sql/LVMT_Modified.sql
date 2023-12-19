
--Dat ten schema la pmchamcong
CREATE TABLE pmchamcong.employees (
    employee_id CHAR(30) PRIMARY KEY,
    name VARCHAR(255),
    department_id CHAR(10),
    employee_type VARCHAR(50),
    FOREIGN KEY (department_id) REFERENCES departments(department_id)
);
CREATE TABLE pmchamcong.departments (
    department_id CHAR(10) PRIMARY KEY,
    department_name VARCHAR(255)
);
CREATE TABLE pmchamcong.attendancerecords (
    record_id CHAR(10) PRIMARY KEY,
    employee_id CHAR(10),
    fingerscanner_id CHAR(10),
    date VARCHAR(10),
    time VARCHAR(8),
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

CREATE TABLE pmchamcong.modifiedattendancerecords (
    request_id CHAR(10) PRIMARY KEY,
    record_id CHAR(10),
    scan_id CHAR(10),
    employee_id CHAR(10),
    date VARCHAR(10),
    time VARCHAR(8),
    time_modified VARCHAR(8),
    date_modified VARCHAR(10),
    request_reason VARCHAR(255),
    request_status VARCHAR(50),
    request_type VARCHAR(50)
);

INSERT INTO pmchamcong.employees (employee_id, name, department_id, employee_type) VALUES
('EMP001', 'Nguyen Van A', 'DEPT001', 'NHANVIEN'),
('EMP002', 'Tran Thi B', 'DEPT002', 'CONGNHAN'),
('EMP003', 'Le Van C', 'DEPT003', 'NHANVIEN'),
('EMP004', 'Pham Thi D', 'DEPT004', 'CONGNHAN'),
('EMP005', 'Hoang Van E', 'DEPT005', 'NHANVIEN'),
('EMP006', 'Vu Thi F', 'DEPT006', 'CONGNHAN'),
('EMP007', 'Dang Van G', 'DEPT007', 'NHANVIEN'),
('EMP008', 'Bui Thi H', 'DEPT008', 'CONGNHAN'),
('EMP009', 'Dao Van I', 'DEPT009', 'NHANVIEN'),
('EMP010', 'Phan Thi J', 'DEPT010', 'CONGNHAN');


INSERT INTO pmchamcong.departments (department_id, department_name) VALUES
('DEPT001', 'Vận Hành Nhà Máy'),
('DEPT002', 'Công Nghệ Thông Tin'),
('DEPT003', 'Nhân Sự'),
('DEPT004', 'Kinh Doanh và Tiếp Thị'),
('DEPT005', 'Nghiên Cứu và Phát Triển'),
('DEPT006', 'Hỗ Trợ Khách Hàng'),
('DEPT007', 'Tài Chính'),
('DEPT008', 'Logistics'),
('DEPT009', 'Đảm Bảo Chất Lượng'),
('DEPT010', 'Thu Mua');

INSERT INTO pmchamcong.attendancerecords (record_id, employee_id, fingerscanner_id, date, time) VALUES
('RECORD001', 'EMP00001', 'SCAN0001', '2023-12-16', '08:30:00'),
('RECORD002', 'EMP00002', 'SCAN0002', '2023-12-16', '08:35:00'),
('RECORD003', 'EMP00003', 'SCAN0003', '2023-12-16', '08:40:00'),
('RECORD004', 'EMP00004', 'SCAN0004', '2023-12-16', '08:45:00'),
('RECORD005', 'EMP00005', 'SCAN0005', '2023-12-16', '08:50:00'),
('RECORD006', 'EMP00006', 'SCAN0006', '2023-12-16', '08:55:00'),
('RECORD007', 'EMP00007', 'SCAN0007', '2023-12-16', '09:00:00'),
('RECORD008', 'EMP00008', 'SCAN0008', '2023-12-16', '09:05:00'),
('RECORD009', 'EMP00009', 'SCAN0009', '2023-12-16', '09:10:00'),
('RECORD010', 'EMP00010', 'SCAN0010', '2023-12-16', '09:15:00');
INSERT INTO pmchamcong.modifiedattendancerecords (request_id, record_id, scan_id, employee_id, date, time, time_modified, date_modified, request_reason, request_status, request_type)
VALUES 
('REQ0001', 'RECORD001', 'SCAN0001', 'EMP001', '2023-12-16', '08:30:00', NULL, NULL, 'Dấu thời gian không chính xác', 'Pending', 'Chỉnh sửa chấm công'),
('REQ0002', 'RECORD002', 'SCAN0002', 'EMP002', '2023-12-16', '08:35:00', NULL, NULL, 'Máy quét hỏng', 'Pending', 'Chỉnh sửa chấm công'),
('REQ0003', 'RECORD003', 'SCAN0003', 'EMP003', '2023-12-16', '08:40:00', NULL, NULL, 'Quên chấm công', 'Pending', 'Chỉnh sửa chấm công'),
('REQ0004', 'RECORD004', 'SCAN0004', 'EMP004', '2023-12-16', '08:45:00', NULL, NULL, 'Sự cố kỹ thuật', 'Pending', 'Chỉnh sửa chấm công'),
('REQ0005', 'RECORD005', 'SCAN0005', 'EMP005', '2023-12-16', '08:50:00', NULL, NULL, 'Dấu thời gian không chính xác', 'Pending', 'Chỉnh sửa chấm công'),
('REQ0006', 'RECORD006', 'SCAN0006', 'EMP006', '2023-12-16', '08:55:00', NULL, NULL, 'Nhập sai ID', 'Pending', 'Chỉnh sửa chấm công'),
('REQ0007', 'RECORD007', 'SCAN0007', 'EMP007', '2023-12-16', '09:00:00', NULL, NULL, 'Dấu thời gian không chính xác', 'Pending', 'Chỉnh sửa chấm công'),
('REQ0008', 'RECORD008', 'SCAN0008', 'EMP008', '2023-12-16', '09:05:00', NULL, NULL, 'Đến muộn', 'Pending', 'Chỉnh sửa chấm công'),
('REQ0009', 'RECORD009', 'SCAN0009', 'EMP009', '2023-12-16', '09:10:00', NULL, NULL, 'Quên chấm công ra', 'Pending', 'Chỉnh sửa chấm công'),
('REQ0010', 'RECORD010', 'SCAN0010', 'EMP010', '2023-12-16', '09:15:00', NULL, NULL, 'Máy quét không hoạt động', 'Pending', 'Chỉnh sửa chấm công');
INSERT INTO pmchamcong.modifiedattendancerecords (request_id, record_id, scan_id, employee_id, date, time, time_modified, date_modified, request_reason, request_status, request_type)
VALUES
('REQ0011', NULL,NULL, 'EMP001', '2023-12-16', '08:30:00', NULL, NULL, 'Dấu thời gian không chính xác', 'Pending', 'Thêm chấm công'),
('REQ0012', NULL,NULL, 'EMP002', '2023-12-16', '08:35:00', NULL, NULL, 'Máy quét hỏng', 'Pending', 'Thêm chấm công'),
('REQ0013', NULL, NULL, 'EMP003', '2023-12-16', '08:40:00', NULL, NULL, 'Quên chấm công', 'Pending','Thêm chấm công'),
('REQ0014', NULL, NULL, 'EMP004', '2023-12-16', '08:45:00', NULL, NULL, 'Sự cố kỹ thuật', 'Pending', 'Thêm chấm công'),
('REQ0015', NULL, NULL, 'EMP005', '2023-12-16', '08:50:00', NULL, NULL, 'Dấu thời gian không chính xác', 'Pending', 'Thêm chấm công'),
('REQ0016', NULL, NULL, 'EMP006', '2023-12-16', '08:55:00', NULL, NULL, 'Nhập sai ID', 'Pending','Thêm chấm công'),
('REQ0017', NULL, NULL, 'EMP007', '2023-12-16', '09:00:00', NULL, NULL, 'Dấu thời gian không chính xác', 'Pending','Thêm chấm công'),
('REQ0018', NULL, NULL, 'EMP008', '2023-12-16', '09:05:00', NULL, NULL, 'Đến muộn', 'Pending', 'Thêm chấm công'),
('REQ0019', NULL, NULL, 'EMP009', '2023-12-16', '09:10:00', NULL, NULL, 'Quên chấm công ra', 'Pending', 'Thêm chấm công'),
('REQ0020', NULL, NULL, 'EMP010', '2023-12-16', '09:15:00', NULL, NULL, 'Máy quét không hoạt động', 'Pending', 'Thêm chấm công');

