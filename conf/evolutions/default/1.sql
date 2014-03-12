# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table album (
  album_id                  bigint not null,
  album_name                varchar(255),
  artist_artist_id          bigint,
  constraint pk_album primary key (album_id))
;

create table artist (
  artist_id                 bigint not null,
  artist_name               varchar(255),
  label_label_id            bigint,
  constraint uq_artist_artist_name unique (artist_name),
  constraint pk_artist primary key (artist_id))
;

create table label (
  label_id                  bigint not null,
  label_name                varchar(255),
  constraint uq_label_label_name unique (label_name),
  constraint pk_label primary key (label_id))
;

create table song (
  song_id                   bigint not null,
  song_name                 varchar(255),
  artist_artist_id          bigint,
  album_album_id            bigint,
  constraint uq_song_1 unique (song_name,artist_artist_id),
  constraint pk_song primary key (song_id))
;

create sequence album_seq;

create sequence artist_seq;

create sequence label_seq;

create sequence song_seq;

alter table album add constraint fk_album_artist_1 foreign key (artist_artist_id) references artist (artist_id) on delete restrict on update restrict;
create index ix_album_artist_1 on album (artist_artist_id);
alter table artist add constraint fk_artist_label_2 foreign key (label_label_id) references label (label_id) on delete restrict on update restrict;
create index ix_artist_label_2 on artist (label_label_id);
alter table song add constraint fk_song_artist_3 foreign key (artist_artist_id) references artist (artist_id) on delete restrict on update restrict;
create index ix_song_artist_3 on song (artist_artist_id);
alter table song add constraint fk_song_album_4 foreign key (album_album_id) references album (album_id) on delete restrict on update restrict;
create index ix_song_album_4 on song (album_album_id);



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

