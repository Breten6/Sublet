
set names utf8;

drop table if exists tab_favorite;
drop table if exists tab_house_img;
drop table if exists tab_house;
drop table if exists tab_seller;
drop table if exists tab_user;


/*==============================================================*/
/* Table: tab_favorite                                          */
/*==============================================================*/
create table tab_favorite
(
   rid                  int not null,
   date                 date not null,
   uid                  int not null,
   primary key (rid, uid)
);

/*==============================================================*/
/* Table: tab_house                                             */
/*==============================================================*/
create table tab_house
(
   rid                  int not null auto_increment,
   rname                varchar(500) not null,
   price                double not null,
   houseIntroduce       varchar(1000),
   count                int default 0,
   sid                  int,
   region               varchar(10),
   primary key (rid),
   unique key AK_nq_sourceId (sourceId)
);


/*==============================================================*/
/* Table: tab_seller                                            */
/*==============================================================*/
create table tab_seller
(
   sid                  int not null auto_increment,
   sname                varchar(200) not null,
   conswechat            varchar(20) not null,
   primary key (sid),
   unique key AK_Key_2 (sname)
);

/*==============================================================*/
/* Table: tab_user                                              */
/*==============================================================*/
create table tab_user
(
   uid                  int not null auto_increment,
   username             varchar(100) not null,
   password             varchar(32) not null,
   sex                  char(1),
   wechat               varchar(100),
   email                varchar(100),
   status               char(1) ,
--    activated or not
   code					varchar(50),
   
   primary key (uid),
   unique key AK_nq_username (username),
   unique key AK_nq_code (code)
);

alter table tab_favorite add constraint FK_house_favorite foreign key (rid)
      references tab_route (rid) on delete restrict on update restrict;

alter table tab_favorite add constraint FK_user_favorite foreign key (uid)
      references tab_user (uid) on delete restrict on update restrict;
tab_sellertab_seller
alter table tab_house add constraint FK_category_house foreign key (cid)
      references tab_category (cid) on delete restrict on update restrict;

alter table tab_house add constraint FK_seller_house foreign key (sid)
      references tab_seller (sid) on delete restrict on update restrict;

alter table tab_house_img add constraint FK_house_houseimg foreign key (rid)
      references tab_house (rid) on delete restrict on update restrict;
	  
	  