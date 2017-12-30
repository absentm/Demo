CREATE DATABASE springbootdemo;

CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(254) NOT NULL DEFAULT '',
  `password` varchar(254) NOT NULL DEFAULT '',
  `age` int(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO user(id, user_name, password, age) VALUES (1, "zhangsan", "zhangsan", 18);
INSERT INTO user(id, user_name, password, age) VALUES (2, "lisi", "lisi", 22);
INSERT INTO user(id, user_name, password, age) VALUES (3, "wangwu", "wangwu", 26);

