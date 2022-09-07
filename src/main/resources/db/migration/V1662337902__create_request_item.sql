create table requests_items (
    quantity   int not null,
    item_id    bigint not null references items (id),
    request_id bigint not null references deliveries (id)
);