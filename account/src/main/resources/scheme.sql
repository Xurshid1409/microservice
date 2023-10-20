create table if not exists customer
(
    id           int primary key,
    name         varchar,
    email        varchar,
    phone_number varchar,
    created_at   date,
    created_by   varchar,
    updated_at   date default null,
    updated_by   varchar
);

create table if not exists accounts
(
    id           int primary key,
    account_type varchar,
    branch_address varchar,
    created_at   date,
    created_by   varchar,
    updated_at   date default null,
    updated_by   varchar,
    customer_id  int
)