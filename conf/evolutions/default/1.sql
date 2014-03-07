# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table artist (
  id                        bigint not null,
  artist_name               varchar(255),
  label_id                  varchar(255),
  constraint pk_artist primary key (id))
;

create sequence artist_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists artist;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists artist_seq;

