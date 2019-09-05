package com.qsoa.admin.servier;

import com.qsoa.admin.pojo.AdminInfo;

public interface AdminService {
	int insert(AdminInfo admininfo);
	
	boolean checkInfo(String username,String password);
	
	boolean setPassword(String password,Integer id);
	
	AdminInfo getAdminInfoByUsernameAndPassword(String username,String password);
}
