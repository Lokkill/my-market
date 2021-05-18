BEGIN;

CREATE Table products (
id                  bigserial primary key,
title               varchar(255),
price               numeric(8,2),
created_at           timestamp default current_timestamp,
updated_at           timestamp default current_timestamp
);

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

create table order_items (
    id                      bigserial primary key,
    product_id              bigint references products (id),
    quantity                int,
    price_per_product       numeric (8, 2),
    price                   numeric (8, 2),
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

create table users (
    id                      bigserial primary key,
    username                varchar(30) not null unique,
    password                varchar(80) not null,
    email                   varchar(80) unique,
    create_at               timestamp default current_timestamp,
    update_at               timestamp default current_timestamp
);

create table user_order (
    id                      bigserial primary key,
    user_id                 bigint not null references users (id)
);

create table cart (
    order_id                bigint not null references user_order (id),
    item_id                 bigint not null references order_items (id),
    primary key (order_id, item_id)
);

create table roles (
    id                      bigserial primary key,
    name                    varchar(50) not null unique,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

CREATE TABLE users_roles (
    user_id                 bigint not null references users (id),
    role_id                 bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values
('ROLE_USER'),
('ROLE_ADMIN');

insert into users (username, password, email)
values
('user', '$2y$12$4g1SOm4vGFSF/CbT84nOzOyygKSuTtRshecj7HYOCC1xUPjhkVPWG', 'malfoi@gmail.com'),
('admin', '$2y$12$4g1SOm4vGFSF/CbT84nOzOyygKSuTtRshecj7HYOCC1xUPjhkVPWG', 'potter@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2);


COMMIT;