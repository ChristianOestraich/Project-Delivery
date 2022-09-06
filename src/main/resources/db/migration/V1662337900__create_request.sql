create table requests (
    id             bigint primary key,
    totalValue     numeric(10, 2)      not null,
    user_id bigint not null references users(id),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);