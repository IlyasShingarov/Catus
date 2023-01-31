
drop table if exists userEntities cascade;
drop table if exists projects cascade;
drop table if exists columns cascade;
drop table if exists tasks cascade;
drop table if exists comments cascade;
drop table if exists project_users cascade;
drop table if exists groups cascade;
drop table if exists task_users cascade;


create table if not exists userEntities
(
    id serial
        constraint users_pk
            primary key,
    login text unique not null,
    name text not null,
    title text not null,
    password text not null,
    in_group int not null
        references groups(id),
    is_active bool default true
);

create table if not exists projects
(
    id serial
        constraint projects_pk
            primary key,
    title text not null,
    description text,
    status text,

    create_dttm date not null default CURRENT_DATE,

    created_by int,

    constraint projects_creator_fk
        foreign key(created_by)
            references userEntities(id)
);

create table if not exists project_users
(
    user_id int not null,
    project_id int not null,

    constraint project_users_user_fk
        foreign key (user_id)
            references userEntities(id),

    constraint project_users_project_fk
        foreign key (project_id)
            references projects(id)

);

create table if not exists columns
(
    id serial
        constraint columns_pk
            primary key,
    title text not null,
    project int not null,
    constraint columns_project_fk
        foreign key(project)
            references projects(id)
);

create table if not exists tasks
(
    id serial
        constraint tasks_pk
            primary key,
    title text not null,
    description text,
    created_by int not null,
    constraint tasks_creator_fk
        foreign key(created_by)
            references userEntities(id),

    create_dttm date not null default current_date,

    status int not null,
    constraint tasks_status_fk
        foreign key(status)
            references columns(id),

    project int not null,
    constraint tasks_project_fk
        foreign key(project)
            references projects(id)
);

create table if not exists comments
(
    id serial
        constraint comments_pk
            primary key,

    content text not null,
    created_by int not null,
    constraint comments_creator_fk
        foreign key(created_by)
            references userEntities(id),

    task int not null,
    constraint comments_task_fk
        foreign key(task)
            references tasks(id)
);

create table if not exists groups
(
    id serial
        constraint groups_pk
            primary key,
    name text not null
);

create table if not exists task_users
(
    user_id int not null,
    task_id int not null,

    constraint task_users_user_fk
        foreign key (user_id)
            references userEntities(id),

    constraint task_users_project_fk
        foreign key (task_id)
            references tasks(id)

);