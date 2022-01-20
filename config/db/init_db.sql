create table resume
(
    uuid char(36) not null
        constraint resume_pk
            primary key,
    full_name text not null
);

alter table resume
    owner to ilshat;

create unique index resume_uuid_uindex
    on resume (uuid);

create table contact
(
    id          serial
        constraint contact_pk
            primary key,
    type        text     not null,
    value       text     not null,
    resume_uuid char(36) not null
        constraint contact_resume_uuid_fk
            references resume
            on update restrict on delete cascade
);

alter table contact
    owner to ilshat;

drop index contact_uuid_type_index;

create unique index contact_uuid_type_index
    on contact (resume_uuid, type);

