create table roles
(
    id      serial      not null
            constraint roles_pk
            primary key,
    name    varchar(20) not null
);

alter table roles
    owner to postgres;