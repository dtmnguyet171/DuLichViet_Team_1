DROP DATABASE IF EXISTS du_lich_viet;
CREATE DATABASE du_lich_viet;
use du_lich_viet;

create table account
(
    id        int auto_increment
        primary key,
    address   varchar(255)                                  null,
    email     varchar(255)                                  not null,
    full_name varchar(255)                                  null,
    password  varchar(255)                                  not null,
    phone     varchar(255)                                  null,
    role      enum ('ADMIN', 'USER') default 'USER'         null,
    status    enum ('ACTIVE', 'INACTIVE') default 'ACTIVE'  null,
    username  varchar(255)                                  not null
);

create table tour
(
    id             int auto_increment
        primary key,
    arrival        varchar(255)                                                not null,
    content        text                                                        not null,
    depart         varchar(255)                                                not null,
    duration       int                                                         not null,
    image          longtext                                                    not null,
    max_guest_size int                                                         not null,
    price          int                                                         not null,
    status         enum ('AVAILABLE', 'UNAVAILABLE') default 'AVAILABLE'       not null,
    title          varchar(255)                                                not null,
    transport      enum ('CAR', 'TRAIN', 'AIRPLANE')                           not null,
    type           enum ('HOLIDAY', 'HOT_TOUR', 'SHORT_TOUR', 'TEAM_BUILDING') not null
);

create table booking
(
    id           int auto_increment
        primary key,
    booking_date date                                         null,
    guest_size   int                                          null,
    note         varchar(255)                                 null,
    price        double                                       null,
    status       enum ('CONFIRM', 'CANCEL') default 'CONFIRM' null,
    account_id   int                                          null,
    tour_id      int                                          null,
    constraint FK8wma53xqkrw3l3r2o2uwg6kjy
        foreign key (tour_id) references tour (id),
    constraint FKq7b5wddjf73v4q196jnkjkfex
        foreign key (account_id) references account (id)
);


