# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table mcustomer_numbers (
  cid                       bigint auto_increment not null,
  tel                       varchar(255),
  type                      varchar(255),
  constraint pk_mcustomer_numbers primary key (cid))
;

create table mfarmer_numbers (
  nid                       bigint auto_increment not null,
  tel                       varchar(255),
  constraint pk_mfarmer_numbers primary key (nid))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table mcustomer_numbers;

drop table mfarmer_numbers;

SET FOREIGN_KEY_CHECKS=1;

