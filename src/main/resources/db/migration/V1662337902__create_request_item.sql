create table requests_items (
    id         bigint primary key,
    item_id bigint not null references items(id),
    request_id bigint not null references deliveries(id)
);