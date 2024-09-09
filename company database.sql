create table employee(
emp_id int primary key,
first_name varchar(40),
last_name varchar(40),
birth_date date,
sex varchar(1),
salary int,
super_id int,
branch_id int
);

create table branch(
branch_id int primary key,
branch_name varchar(40),
mgr_id int,
mgr_start_date date,
foreign key (mgr_id) references employee (emp_id) on delete set null
);

alter table employee
add foreign key (branch_id) references branch(branch_id) on delete set null;
alter table employee 
add foreign key (super_id) references employee(emp_id) on delete set null;

create table client(
client_id int primary key,
clinet_name varchar(40),
branch_id int,
foreign key (branch_id) references branch(branch_id) on delete set null
);

create table work_with(
emp_id int,
client_id int,
total_sales int,
primary key(emp_id,client_id),
foreign key (emp_id) references employee (emp_id) on delete cascade,
foreign key (client_id) references client (client_id) on delete cascade
);

create table branch_supplier(
branch_id int,
supplier_name varchar(40),
supply_type varchar(40),
primary key(branch_id,supplier_name),
foreign key (branch_id) references branch( branch_id) on delete cascade
);
-- Corporate
INSERT INTO employee VALUES(100, 'David', 'Wallace', '1967-11-17', 'M', 250000, NULL, NULL);

INSERT INTO branch VALUES(1, 'Corporate', 100, '2006-02-09');

UPDATE employee
SET branch_id = 1
WHERE emp_id = 100;

INSERT INTO employee VALUES(101, 'Jan', 'Levinson', '1961-05-11', 'F', 110000, 100, 1);

-- Scranton
INSERT INTO employee VALUES(102, 'Michael', 'Scott', '1964-03-15', 'M', 75000, 100, NULL);

INSERT INTO branch VALUES(2, 'Scranton', 102, '1992-04-06');

UPDATE employee
SET branch_id = 2
WHERE emp_id = 102;

INSERT INTO employee VALUES(103, 'Angela', 'Martin', '1971-06-25', 'F', 63000, 102, 2);
INSERT INTO employee VALUES(104, 'Kelly', 'Kapoor', '1980-02-05', 'F', 55000, 102, 2);
INSERT INTO employee VALUES(105, 'Stanley', 'Hudson', '1958-02-19', 'M', 69000, 102, 2);

-- Stamford
INSERT INTO employee VALUES(106, 'Josh', 'Porter', '1969-09-05', 'M', 78000, 100, NULL);

INSERT INTO branch VALUES(3, 'Stamford', 106, '1998-02-13');

UPDATE employee
SET branch_id = 3
WHERE emp_id = 106;

INSERT INTO employee VALUES(107, 'Andy', 'Bernard', '1973-07-22', 'M', 65000, 106, 3);
INSERT INTO employee VALUES(108, 'Jim', 'Halpert', '1978-10-01', 'M', 71000, 106, 3);

-- BRANCH SUPPLIER
INSERT INTO branch_supplier VALUES(2, 'Hammer Mill', 'Paper');
INSERT INTO branch_supplier VALUES(2, 'Uni-ball', 'Writing Utensils');
INSERT INTO branch_supplier VALUES(3, 'Patriot Paper', 'Paper');
INSERT INTO branch_supplier VALUES(2, 'J.T. Forms & Labels', 'Custom Forms');
INSERT INTO branch_supplier VALUES(3, 'Uni-ball', 'Writing Utensils');
INSERT INTO branch_supplier VALUES(3, 'Hammer Mill', 'Paper');
INSERT INTO branch_supplier VALUES(3, 'Stamford Lables', 'Custom Forms');

-- CLIENT
INSERT INTO client VALUES(400, 'Dunmore Highschool', 2);
INSERT INTO client VALUES(401, 'Lackawana Country', 2);
INSERT INTO client VALUES(402, 'FedEx', 3);
INSERT INTO client VALUES(403, 'John Daly Law, LLC', 3);
INSERT INTO client VALUES(404, 'Scranton Whitepages', 2);
INSERT INTO client VALUES(405, 'Times Newspaper', 3);
INSERT INTO client VALUES(406, 'FedEx', 2);


-- WORKS_WITH
INSERT INTO work_with VALUES(105, 400, 55000);
INSERT INTO work_with VALUES(102, 401, 267000);
INSERT INTO work_with VALUES(108, 402, 22500);
INSERT INTO work_with VALUES(107, 403, 5000);
INSERT INTO work_with VALUES(108, 403, 12000);
INSERT INTO work_with VALUES(105, 404, 33000);
INSERT INTO work_with VALUES(107, 405, 26000);
INSERT INTO work_with VALUES(102, 406, 15000);
INSERT INTO work_with VALUES(105, 406, 130000);

select * from employee;
select * from branch;
select * from client;
select * from work_with;
select * from branch_supplier;

select * from client
where clinet_name like'%school';

select first_name as company_name from employee 
union
select branch_name from branch;

select salary from employee
union
select total_sales from work_with;

select emp_id,first_name,branch_name from employee 
left join branch
on emp_id = mgr_id;

insert into branch values (4,'Buffalo',null,null); 
select * from employee;
select * from branch;

select client.client_id , client.clinet_name , branch_name
from client
join branch
on branch.branch_id = client.branch_id;

select employee.first_name,employee.last_name
 from employee
where employee.emp_id in(
      select work_with.emp_id
      from work_with
      where total_sales >30000);
      
delete from employee 
where emp_id = 102;
select * from employee;

delete from branch_supplier 
where branch_id = 2;
select * from branch_supplier;









