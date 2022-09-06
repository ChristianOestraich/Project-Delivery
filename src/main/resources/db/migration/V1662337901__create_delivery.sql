create table deliveries (
    id                bigint primary key,
    deliveryAddress   varchar(150)      not null,
    request_id bigint not null references requests(id),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);