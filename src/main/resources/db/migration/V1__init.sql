BEGIN;

CREATE Table products (id bigserial primary key, title varchar(255), price int);
insert into products (title, price) values
('Milk', 100),
('Egg', 80),
('Bread', 40),
('Ice cream', 120),
('Cola', 60),
('Cola', 60),
('Cola', 60),
('Cola', 60),
('Cola', 60),
('Cola', 60),
('Cola', 60),
('Cola', 60),
('Cola', 60),
('Cola', 60),
('Cola', 60),
('Cola', 60),
('Cola', 60),
('Cola', 60),
('Cola', 60);

COMMIT;