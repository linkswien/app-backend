--liquibase formatted sql

--changeset florian:initial-setup
create table "user"
(
    id          serial
        constraint user_pk primary key,
    email       varchar not null
        constraint email_unique unique,
    username    varchar not null,
    "firstName" varchar not null,
    "lastName"  varchar not null
);

create table role
(
    id          serial
        constraint role_pk
            primary key,
    name        varchar not null,
    description varchar not null
);

create table "userRole"
(
    "userId" int not null
        constraint userrole_user_id_fk
            references "user"
            on update cascade on delete cascade,
    "roleId" int not null
        constraint userrole_role_id_fk
            references role
            on update cascade on delete cascade,
    constraint userrole_pk
        primary key ("userId", "roleId")
);

create table "pushToken"
(
    id             serial
        constraint pushtoken_pk
            primary key,
    "userId"       int       not null
        constraint pushtoken_user_id_fk
            references "user"
            on update cascade on delete cascade,
    token          varchar   not null,
    "expirationTs" timestamp not null
);

create table "eventType"
(
    id           serial
        constraint eventtype_pk
            primary key,
    "name"       varchar not null,
    "dataSchema" varchar not null default '{}',
    "iconUrl"    varchar not null
);

create table "event"
(
    id            serial
        constraint event_pk
            primary key,
    "typeId"      int       not null
        constraint event_type_id_fk
            references "eventType"
            on update cascade on delete cascade,
    "userId"      int       not null
        constraint event_user_id_fk
            references "user"
            on update cascade on delete cascade,
    "creationTs"  timestamp not null,
    "title"       varchar   not null,
    "headerImg"   varchar   not null,
    "description" varchar   not null,
    "typeData"    varchar   not null
);

create table "subscription"
(
    "userId"  int not null
        constraint subscription_user_id_fk
            references "user"
            on update cascade on delete cascade,
    "eventId" int not null
        constraint subscription_event_id_fk
            references "event"
            on update cascade on delete cascade,
    constraint subscription_pk
        primary key ("userId", "eventId")
);

create table "notification"
(
    id            serial    not null
        constraint notification_pk
            primary key,
    "creationTs"  timestamp not null,
    "eventId"     int       not null
        constraint subscription_event_id_fk
            references "event"
            on update cascade on delete cascade,
    "creatorId"   int       null
        constraint subscription_creator_id_fk
            references "user"
            on update cascade on delete cascade,
    "recipientId" int       not null
        constraint subscription_recipient_id_fk
            references "user"
            on update cascade on delete cascade
)

