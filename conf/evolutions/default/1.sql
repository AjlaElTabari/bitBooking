# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table app_user (
  id                        integer auto_increment not null,
  firstname                 varchar(255),
  lastname                  varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  phone_number              varchar(255),
  constraint pk_app_user primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table app_user;

SET FOREIGN_KEY_CHECKS=1;

