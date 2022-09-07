create table items (
    id          bigint primary key,
    name_item        varchar(150)   not null,
    description varchar(150)   not null,
    value_item       numeric(10, 2) not null,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);