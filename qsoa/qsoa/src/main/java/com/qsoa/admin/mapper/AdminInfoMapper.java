package com.qsoa.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.qsoa.admin.pojo.AdminInfo;

@Mapper
public interface AdminInfoMapper {
	/**
	 * 保存数据
	 */
	int insert(AdminInfo adminInfo);
	/**
	 * 验证用户名和密码
	 */
	int checkAdminInfo(@Param("username")String username,String password);
	/**
	 * 设置密码
	 */
	int setPassword(Integer id,String password,String salt);
	/**
	 * 获取盐值
	 */
	String getSalt(String username);
	/**
	 * 获取单个对象
	 */
	AdminInfo getAdminInfoByUsernameAndPassword(String username,String password);
}
