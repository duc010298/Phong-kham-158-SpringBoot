set time zone +7;

create extension if not exists "uuid-ossp";

create table if not exists app_user
(
    id                 serial       not null primary key,
    user_name          varchar(36)  not null unique,
    encrypted_password varchar(128) not null,
    full_name          varchar(250) not null
);

create table if not exists app_role
(
    id        int         not null primary key,
    role_name varchar(30) not null unique
);

create table if not exists user_role
(
    user_id int not null,
    role_id int not null,
    primary key (user_id, role_id),
    constraint user_role_fk1 foreign key (user_id) references app_user (id),
    constraint user_role_fk2 foreign key (role_id) references app_role (id)
);

create table if not exists customer
(
    id                     uuid         not null primary key,
    name                   varchar(250) not null,
    name_search            varchar(250) not null,
    year_of_birth          int          not null,
    address                varchar(250) not null,
    address_search         varchar(250) not null,
    day_visit              date         not null,
    expected_date_of_birth date,
    result                 varchar(250) not null,
    note                   varchar(250),
    report                 text
);

create table if not exists report_form
(
    id           uuid         not null primary key,
    report_name  varchar(100) not null,
    order_number int          not null,
    content      text         not null,
    last_edit    timestamp    not null
);