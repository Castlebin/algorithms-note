-- 查看MySQL连接
show processlist;
-- 查看MySQL版本
select version();

-- 显式启动事务
begin;
-- 或者
start transaction;  -- 注意！执行了这两句之后，只有开始执行第一条SQL语句时，事务才正式开启

-- 或者
start transaction with consistent snapshot;   -- 这个命令可以立即开始事务，并且创建一个read view


-- 在 information_schema 库的 innodb_trx 这个表中查询长事务，比如下面这个语句，用于查找持续时间超过 60s 的事务
select * from information_schema.innodb_trx where TIME_TO_SEC(timediff(now(),trx_started))>60;

-- 结束事务
-- 1. 正常提交事务
commit;
-- 2. 回滚事务
rollback;
-- 3. 提交事务并立即开启下一个事务
commit work and chain;


-- 4、5 章节（索引）
create table T (
    id int primary key,
    k int not null,
    name varchar(16),
    index (k)
) engine=InnoDB;

create table T (
    ID int primary key,
    k int NOT NULL DEFAULT 0,
    s varchar(16) NOT NULL DEFAULT '',
    index k(k)
) engine=InnoDB;

insert into T values(100,1, 'aa'),(200,2,'bb'),(300,3,'cc'),(500,5,'ee'),(600,6,'ff'),(700,7,'gg');

select * from T where k between 3 and 5;

-- 可以使用下面的语句重建主键索引
alter table T engine=InnoDB;

/**
  SQL 注释 语句

  1. SQL标准支持两种注释，单行注释   --     （-- 空格）
  2. 多行注释  即本注释
  3. 另外MySQL还支持 #开头作为注释（无需多加空格）
 */

CREATE TABLE `t` (
    `id` int(11) NOT NULL,
    `k` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;
insert into t(id, k) values(1,1),(2,2);

/**
  第 10 篇 为什么 MySQL 会选错索引
  选错索引的例子，无法复现，留言区给了可以复现的 SQL，简单来说id设置为自增，不要指定id值
  （但是，这样其实与例子中数据就不一致了，虽然也可以）
  号外，号外，我终于把开头例子给复现了，修改建表语句（id自增），存储过程代码（不要指定id值）如下，即可复现不选择索引a问题：
 */
CREATE TABLE `t` (
 `id` int(11) auto_increment,
 `a` int(11) DEFAULT NULL,
 `b` int(11) DEFAULT NULL,
 PRIMARY KEY (`id`),
 KEY `a` (`a`),
 KEY `b` (`b`)
) ENGINE=InnoDB;

delimiter ;;
create procedure idata()
begin
declare i int;
set i=1;
while(i<=100000)do
        insert into t (`a`,`b`) values(i, i);
        set i=i+1;
    end while;
end;;
delimiter ;

/**
  对比原来的 SQL
 */

CREATE TABLE `t_1` (
 `id` int(11) NOT NULL,
 `a` int(11) DEFAULT NULL,
 `b` int(11) DEFAULT NULL,
 PRIMARY KEY (`id`),
 KEY `a` (`a`),
 KEY `b` (`b`)
) ENGINE=InnoDB;

delimiter ;;
create procedure idata_1()
begin
    declare i int;
    set i=1;
    while(i<=100000)do
            insert into t_1 values(i, i, i);
            set i=i+1;
        end while;
end;;
delimiter ;



