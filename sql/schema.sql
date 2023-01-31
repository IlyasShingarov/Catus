create schema if not exists catus;

create table if not exists catus.groups
(
    id serial
    constraint groups_pk
    primary key,
    name text not null
);

create table if not exists catus.users
(
    id serial
    constraint users_pk
    primary key,
    login text unique not null,
    name text not null,
    avatar_url text not null,
    role text not null,
    password text not null,
    in_group int
    references catus.groups(id),
    is_active bool default true
    );

create table if not exists catus.projects
(
    id serial
    constraint projects_pk
    primary key,
    title text not null,
    description text,
    status text,

    create_dttm date not null default current_date,

    created_by int not null,

    constraint projects_creator_fk
    foreign key(created_by)
    references catus.users(id)
    );

create table if not exists catus.project_users
(
    user_id int not null,
    project_id int not null,

    constraint project_users_user_fk
    foreign key (user_id)
    references catus.users(id),

    constraint project_users_project_fk
    foreign key (project_id)
    references catus.projects(id)

    );

create table if not exists catus.tasks
(
    id serial
    constraint tasks_pk
    primary key,
    title text not null,
    description text,
    created_by int not null,
    constraint tasks_creator_fk
    foreign key(created_by)
    references catus.users(id),

    create_dttm date not null default current_date,

    status varchar(50) not null,
    type varchar(100),
    due_date date,

    project int not null,
    constraint tasks_project_fk
    foreign key(project)
    references catus.projects(id)
    );


create table if not exists catus.comments
(
    id serial
    constraint comments_pk
    primary key,

    content text not null,
    created_by int not null,
    constraint comments_creator_fk
    foreign key(created_by)
    references catus.users(id),

    task int not null,
    constraint comments_task_fk
    foreign key(task)
    references catus.tasks(id)
    );

