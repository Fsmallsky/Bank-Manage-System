/*==============================================================*/
/* DBMS b_name:      ORACLE Version 10gR2                         */
/* Created on:     2018/6/16 18:45:00                           */
/*==============================================================*/

drop table Customer cascade constraints;
drop table branch cascade constraints;
drop table check_account cascade constraints;
drop table loan cascade constraints;
drop table save_account cascade constraints;
drop table staff cascade constraints;
drop table payout cascade constraints;
drop table loanp cascade constraints;

/*==============================================================*/
/* Table: "branch"                                              */
/*==============================================================*/
create table branch  (
   b_name             VARCHAR2(20)                    not null,
   city               VARCHAR2(20)                    not null,
   money              BINARY_DOUBLE                   default 0,
   constraint PK_BRANCH primary key (b_name)
);

/*==============================================================*/
/* Table: "Customer"                                            */
/*==============================================================*/
create table Customer  (
   c_id               VARCHAR2(20)                    not null,
   c_name             VARCHAR2(20)                    not null,
   c_phone            VARCHAR2(20)                    not null,
   c_address          VARCHAR2(20)                    not null,
   r_name             VARCHAR2(20),
   r_phone            VARCHAR2(20),
   r_email            VARCHAR2(20),
   relationship       VARCHAR2(20),
   constraint PK_CUSTOMER primary key (c_id)
);

/*==============================================================*/
/* Table: "staff"                                               */
/*==============================================================*/
create table staff  (
   s_id               VARCHAR2(20)                    not null,
   s_name             VARCHAR2(20)                    not null,
   s_phone            VARCHAR2(20)                    not null,
   s_address          VARCHAR2(20)                    not null,
   state_time         DATE                            not null,
   manager            VARCHAR2(20)                    not null,
   constraint PK_STAFF primary key (s_id)
);

/*==============================================================*/
/* Table: "check"                                               */
/*==============================================================*/
create table check_account  (
   b_name             VARCHAR2(20)                    not null,
   a_id               VARCHAR2(20)                    not null,
   c_id               VARCHAR2(20),
   total_num          BINARY_DOUBLE                  default 0,
   state_date         DATE,
   last_op            DATE,
   overdraft          BINARY_DOUBLE,
   s_id               VARCHAR2(20),
   constraint PK_CHECK primary key (a_id),
   constraint UQ_C unique(c_id, b_name),
   constraint FK1_check foreign key (b_name) references branch(b_name),
   constraint FK2_check foreign key (s_id) references staff(s_id)
);

/*==============================================================*/
/* Table: "save"                                                */
/*==============================================================*/
create table save_account  (
   b_name             VARCHAR2(20)                    not null,
   a_id               VARCHAR2(20)                    not null,
   c_id               VARCHAR2(20) ,
   total_num          BINARY_DOUBLE                  default 0,
   state_date         DATE,
   last_op            DATE,
   rate               BINARY_DOUBLE,
   money_type         VARCHAR2(20),
   s_id               VARCHAR2(20), 
   constraint PK_SAVE primary key (a_id), 
   constraint UQ_S unique(c_id, b_name),
   constraint FK_save foreign key (b_name) references branch(b_name),
   constraint FK2_save foreign key (s_id) references staff(s_id)
);

/*==============================================================*/
/* Table: "loan"                                                */
/*==============================================================*/
create table loan  (
   loan_id            INTEGER                         not null,
   b_name             VARCHAR2(20),
   loan_num           BINARY_DOUBLE,
   s_id               VARCHAR2(20),
   state              VARCHAR2(20),
   remain             BINARY_DOUBLE,
   constraint PK_LOAN primary key (loan_id),
   constraint FK_loan foreign key (b_name) references branch(b_name),
   constraint FK2_loan foreign key (s_id) references staff(s_id)
);

/*==============================================================*/
/* Table: 付款                                                    */
/*==============================================================*/
create table payout  (
   loan_id            INTEGER                         not null,
   pay_date           DATE,
   pay_num            BINARY_DOUBLE,
   constraint PK_payout primary key (loan_id, pay_date),
   constraint FK1_payout foreign key (loan_id) references loan(loan_id)
);
create table  loanp (
   loan_id      INTEGER,
   c_id         VARCHAR2(20),
   constraint PK_loanp primary key (loan_id, c_id),
   constraint FK1_loanp foreign key (loan_id) references loan(loan_id),
   constraint FK2_loanp foreign key (c_id) references customer(c_id)
);


delete from payout;
delete from loanp;
delete from loan;
delete from save_account;
delete from check_account;
delete from staff;
delete from customer;
delete from branch;


insert into branch values('中国分行', '北京', 98765.3);
insert into branch values('美国分行', '纽约', 87654.2);
insert into branch values('法国分行', '巴黎', 76543.4);

insert into customer values ('c001', 'cname1', '110', '中南海', '彭丽媛', '111', 'c111@china.cn', 'wife');
insert into customer values ('c002', '特朗普', '911', '白宫',   '奥巴马', '222', 'c222@USA.cn',   'brother');
insert into customer values ('c003', 'cname3', '330', 'caddr3', 'rname1', '331', '333@qq.com', 'baba');

insert into staff values ('s001', 'sname1', '001', 'saddr1', to_date('04-03-1997','dd-mm-yyyy'), 's001');
insert into staff values ('s002', 'sname2', '002', 'saddr2', to_date('02-01-2000','dd-mm-yyyy'), 's001');
insert into staff values ('s003', 'sname3', '003', 'saddr3', to_date('23-10-2018','dd-mm-yyyy'), 's001');
insert into staff values ('s004', 'sname4', '003', 'saddr4', to_date('23-10-2018','dd-mm-yyyy'), 's004');

insert into save_account values ('中国分行', 'a001', 'c001', 666.666, to_date('22-12-2012','dd-mm-yyyy'), to_date('13-05-2018','dd-mm-yyyy'), 10, '￥', 's001');
insert into save_account values ('美国分行', 'a002', 'c002', 22.22, to_date('20-01-2014','dd-mm-yyyy'), to_date('04-03-2017','dd-mm-yyyy'), 0.001, '$', 's002');
insert into save_account values ('法国分行', 'a003', 'c001', 11.11, to_date('01-02-2015','dd-mm-yyyy'), to_date('01-11-1009','dd-mm-yyyy'), 0.01, '$', 's003');

insert into check_account values('法国分行', 'a004', 'c001', -12.12, to_date('27-09-2005','dd-mm-yyyy'), to_date('11-11-2019','dd-mm-yyyy'), 0.01, 's001');
insert into check_account values('中国分行', 'a005', 'c003', 131.7, to_date('24-11-2015','dd-mm-yyyy'), to_date('23-07-2020','dd-mm-yyyy'), 100, 's002');

insert into loan values (1, '中国分行', 233, 's001','正在发放',133);
insert into loan values (2, '美国分行', 999, 's004','正在发放',500);
insert into loan values (3, '美国分行', 121, 's004','尚未发放',121);

insert into payout values (1, to_date('22-09-2011','dd-mm-yyyy'), 10);
insert into payout values (1, to_date('21-09-2011','dd-mm-yyyy'), 10);
insert into payout values (1, to_date('20-09-2011','dd-mm-yyyy'), 10);
insert into payout values (1, to_date('25-09-2011','dd-mm-yyyy'), 10);
insert into payout values (1, to_date('23-10-2011','dd-mm-yyyy'), 60);
insert into payout values (2, to_date('30-12-2011','dd-mm-yyyy'), 499);

insert into loanp values (1, 'c001');
insert into loanp values (1, 'c002');
insert into loanp values (1, 'c003');
insert into loanp values (2, 'c001');

commit;

select * from customer;
select * from branch;
select * from staff;
select * from check_account;
select * from save_account;
select * from loan;
select * from payout;
select * from loanp;

--delete from payout where pay_date = to_date('02-04-2012','dd-mm-yyyy');
