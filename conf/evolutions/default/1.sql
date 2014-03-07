# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table album (
  id                        bigint not null,
  album_name                varchar(255),
  constraint pk_album primary key (id))
;

create table artist (
  id                        bigint not null,
  artist_name               varchar(255),
  label_id                  varchar(255),
  constraint uq_artist_artist_name unique (artist_name),
  constraint pk_artist primary key (id))
;

create table label (
  id                        bigint not null,
  label_name                varchar(255),
  constraint uq_label_label_name unique (label_name),
  constraint pk_label primary key (id))
;

create table song (
  id                        bigint not null,
  song_name                 varchar(255),
  constraint pk_song primary key (id))
;

create sequence album_seq;

create sequence artist_seq;

create sequence label_seq;

create sequence song_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists album;

drop table if exists artist;

drop table if exists label;

drop table if exists song;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists album_seq;

drop sequence if exists artist_seq;

drop sequence if exists label_seq;

drop sequence if exists song_seq;

