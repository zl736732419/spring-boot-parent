-- 角色表
CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `role_tag` varchar(50) NOT NULL COMMENT '角色标识，用于shiro权限认证',
  `avatar` varchar(255) DEFAULT NULL COMMENT '角色头像',
  `status` int(11) NOT NULL DEFAULT '100' COMMENT '角色状态 100正常 10禁用',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除 0未删除 1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 资源表
CREATE TABLE `tb_url_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `name` varchar(50) NOT NULL COMMENT '资源名称',
  `url` varchar(255) NOT NULL COMMENT '资源地址',
  `sort_num` int(11) NOT NULL DEFAULT 1 COMMENT '排序字段',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除 0未删除 1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 用户表
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '加密密码',
  `salt` varchar(32) NOT NULL COMMENT '密码盐，用于加密密码',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `nickname` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `status` int(11) NOT NULL DEFAULT '100' COMMENT '用户状态 100正常 10锁定',
  `active_time` timestamp NULL DEFAULT NULL COMMENT '解锁时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除 0未删除 1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 角色资源表
CREATE TABLE `tb_role_url_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `role_id` int(11) NOT NULL COMMENT '角色id，对应tb_role.id',
  `resource_id` int(11) NOT NULL COMMENT '资源id, 对应tb_url_resource.id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 用户角色表
CREATE TABLE `tb_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `user_id` int(11) NOT NULL COMMENT '用户标识，对应tb_user.id',
  `role_id` int(11) NOT NULL COMMENT '角色标识，对应tb_role.id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

