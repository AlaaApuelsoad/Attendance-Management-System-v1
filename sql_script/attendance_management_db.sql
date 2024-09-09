create database attendance_management;
use attendance_management;
-- drop database attendance_management;

-- Role table
create table role(
	id bigint primary key auto_increment,
	role_name varchar(50) not null unique
)auto_increment = 1;

-- table user
create table user(
	id bigint primary key auto_increment,
	user_name varchar(100) not null,
	password varchar(100) not null,
	enable varchar(20) default 'true',
	role_id bigint not null,
	FOREIGN KEY (role_id) REFERENCES role(id)
 )	auto_increment = 1;
 


-- select user_name, password, enable from user where user_name = 'systemadmin';

-- create table system_admin
create table admin(
	id bigint primary key auto_increment,
	user_id bigint not null,
	first_name varchar(100) not null,
	last_name varchar(100) not null,
	email varchar(100) not null unique,
	phone varchar(20) not null unique,
	foreign key (user_id) references user(id)
)	auto_increment = 1; 

-- Student table
create table student(
	id bigint primary key auto_increment,
	user_id bigint not null,
	first_name varchar(100) not null,
	last_name varchar(100) not null,
	email varchar(100) not null unique,
	phone varchar(20) not null unique,
	foreign key (user_id) references user(id)
)	auto_increment = 1;

-- table instructor
create table instructor(
	id bigint primary key auto_increment,
	user_id bigint not null,
	first_name varchar(100) not null,
	last_name varchar(100) not null,
	email varchar(100) not null unique,
	phone varchar(20) not null unique,
	FOREIGN KEY (user_id) REFERENCES user(id)
);

-- Course table
create table course(
	id bigint primary key auto_increment,
	course_name varchar (100)  unique,
	description varchar(255)
)	auto_increment = 1;

-- instrucotr_course table many to many
create table instructor_course(
	instructor_id bigint not null,
	course_id bigint not null,
	assign_data timestamp not null default current_timestamp,
	primary key (instructor_id,course_id),
	foreign key (instructor_id) references instructor(id),
	foreign key (course_id) references course(id)
);

-- Enrollment table (many to many relationship between student and course)
CREATE TABLE enrollment (
    student_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    enrollment_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (course_id) REFERENCES course(id)
);

-- Attendance table
CREATE TABLE attendance (
	id bigint primary key auto_increment ,
    student_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    status VARCHAR(100),
    attendance_time TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (course_id) REFERENCES course(id)
)auto_increment = 1;


-- insert into role (role_name) values
--  ('admin'),('instructor'),('student');
 
--  insert into user (user_name,password,role_id) 
--  values ('alaaapuelsoad','{bcrypt}$2y$10$OItHJIb4rYrY5SwQqpwbieVmih2PZbgCoedQtr6OewpKw5AmSfuj6',1);





-- -- Trigger to set status value based on Student attendance Time
-- -- Change the delimiter to //
-- DELIMITER //

-- -- Create the trigger
-- CREATE TRIGGER set_attendance_status
-- BEFORE INSERT ON attendance
-- FOR EACH ROW
-- BEGIN
--     -- If attendance time is on or before 9:00 AM, set status to 'OnTime'
--     IF TIME(NEW.attendance_time) <= '09:00:00' THEN
--         SET NEW.status = 'OnTime';
--     -- If attendance time is between 9:00:01 AM and 9:15:00 AM, set status to 'Late'
--     ELSEIF TIME(NEW.attendance_time) > '09:00:00' AND TIME(NEW.attendance_time) <= '09:15:00' THEN
--         SET NEW.status = 'Late';
--     -- If attendance time is after 9:15:00 AM, set status to 'Absent'
--     ELSE
--         SET NEW.status = 'Absent';
--     END IF;
-- END//

-- -- Change the delimiter back to ;
-- DELIMITER ;
