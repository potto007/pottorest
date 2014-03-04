# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table artist (
  id                        bigint not null,
  username                  varchar(255),
  name_first                varchar(255),
  name_last                 varchar(255),
  constraint pk_user primary key (id))
;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists artist;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists user_seq;

