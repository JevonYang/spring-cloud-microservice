-- auto Generated on 2018-06-05 10:27:31 
-- DROP TABLE IF EXISTS Role;
CREATE TABLE Role(
	id BIGINT (15) NOT NULL AUTO_INCREMENT COMMENT 'id',
	name VARCHAR (50) NOT NULL DEFAULT '' COMMENT '角色名称',
	value VARCHAR (50) NOT NULL DEFAULT '' COMMENT '角色值',
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '角色表';
