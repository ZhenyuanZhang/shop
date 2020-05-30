########################################
# MySQL Crash Course
# http://www.forta.com/books/0672327120/
# Example table creation scripts
########################################
DROP TABLE IF EXISTS orderitems;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS vendors;

######################
# Create vendors table
######################
CREATE TABLE IF NOT EXISTS vendors
(
  vend_id      int         NOT NULL AUTO_INCREMENT COMMENT '供应商ID',
  vend_name    varchar(50) NOT NULL COMMENT '供应商名称',
  vend_address varchar(50) COMMENT '供应商地址',
  vend_city    varchar(50) COMMENT '供应商所在城市',
  vend_state   char(5)     COMMENT '供应商所在省份',
  vend_zip     char(10)    COMMENT '供应商邮政编码',
  vend_country varchar(50) COMMENT '供应商所在国家',
  PRIMARY KEY (vend_id)
) ENGINE=InnoDB;

#######################
# Create products table
#######################
CREATE TABLE IF NOT EXISTS products
(
  prod_id    varchar(36)   NOT NULL COMMENT '产品ID',
  vend_id    int           NOT NULL COMMENT '产品供应商的ID',
  prod_name  char(255)     NOT NULL COMMENT '产品名',
  prod_price decimal(8,2)  NOT NULL COMMENT '产品价格',
  prod_desc  text          COMMENT '产品描述',
  PRIMARY KEY(prod_id)
) ENGINE=InnoDB;

########################
# Create customers table
########################
CREATE TABLE IF NOT EXISTS customers
(
  cust_id      int          NOT NULL AUTO_INCREMENT COMMENT '顾客ID',
  cust_name    varchar(50)  NOT NULL COMMENT '顾客名称',
  cust_address varchar(50)  COMMENT '顾客地址',
  cust_city    varchar(50)  COMMENT '顾客所在城市',
  cust_state   char(9)      COMMENT '顾客所在省份',
  cust_zip     char(10)     COMMENT '顾客邮政编码',
  cust_country varchar(50)  COMMENT '顾客所在国家',
  cust_contact char(50)     COMMENT '顾客的联系方式',
  cust_email   varchar(255) COMMENT '顾客的email地址',
  PRIMARY KEY (cust_id)
) ENGINE=InnoDB;

#####################
# Create orders table
#####################
CREATE TABLE IF NOT EXISTS orders
(
  order_num  int      NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  order_date datetime NOT NULL COMMENT '订单日期',
  cust_id    int      NOT NULL COMMENT '顾客ID',
  PRIMARY KEY (order_num)
) ENGINE=InnoDB;

#########################
# Create orderitems table
#########################
CREATE TABLE IF NOT EXISTS orderitems
(
  order_num  int          NOT NULL COMMENT '订单ID',
  order_item int          NOT NULL COMMENT '该物品在订单中的排序号',
  prod_id    varchar(36)  NOT NULL COMMENT '产品ID',
  quantity   int          NOT NULL COMMENT '数量',
  item_price decimal(8,2) NOT NULL COMMENT '商品价格',
  PRIMARY KEY (order_num, order_item)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS operationlog;
#########################
# Create operationlog table
#########################
CREATE TABLE IF NOT EXISTS operationlog
(
  id             int          NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  meta_data      varchar(200) NOT NULL COMMENT '节点信息',
  business_type  varchar(50)  NULL COMMENT '资源类型',
  operation_type varchar(50)  NULL COMMENT '资源类型',
  args           TEXT         NULL DEFAULT NULL COMMENT '方法入参',
  `result`       varchar(100) NULL DEFAULT NULL COMMENT '方法结果',
  error_msg      TEXT         NULL DEFAULT NULL COMMENT '方法异常',
  `operator`     varchar(12)  NOT NULL COMMENT '请求调用者',
  time_consuming int          NULL DEFAULT 0 COMMENT '业务调用耗时',
  operator_time  timestamp    NOT NULL default CURRENT_TIMESTAMP, 
  PRIMARY KEY (id)
) ENGINE=InnoDB;





