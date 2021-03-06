create table users
(
    id          serial not null
                constraint users_pk
                primary key,
    username    varchar(50),
    password    varchar(500),
    first_name  varchar(50),
    last_name   varchar(50),
    username    varchar(50),
    role_id     integer
                constraint users_roles_id_fk
                references roles
);

alter table users
    owner to postgres;

create unique index users_username_uindex
    on users (username);