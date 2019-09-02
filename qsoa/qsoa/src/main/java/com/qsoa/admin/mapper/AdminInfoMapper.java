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
}
