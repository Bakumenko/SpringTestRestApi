create table projects
(
    id          serial      not null
                constraint projects_pk
                primary key,
    title       varchar(20),
    description varchar(200),
    income      int,
    user_id     integer
                constraint projects_users_id_fk
                references users
);

alter table projects
    owner to postgres;