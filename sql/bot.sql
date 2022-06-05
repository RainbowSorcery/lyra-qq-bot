create table interview_questions
(
    id          bigint           not null
        primary key,
    question    varchar(500)     not null,
    answer      varchar(500)     not null,
    create_date datetime         null,
    print_count bigint default 0 null
);

create table pony_music_163
(
    id         int          not null
        primary key,
    music_name varchar(200) null
);

create table sign_vpn_log
(
    id          bigint auto_increment
        primary key,
    log         varchar(200) null,
    create_date datetime     not null,
    constraint sign_vpn_log_id_uindex
        unique (id)
);

create table sign_vpn_user
(
    id       bigint auto_increment
        primary key,
    username varchar(20)  null,
    password varchar(200) null,
    constraint sing_vpn_user_id_uindex
        unique (id)
);

create table user
(
    id bigint auto_increment
        primary key,
    qq bigint null,
    constraint user_qq_uindex
        unique (qq)
);

