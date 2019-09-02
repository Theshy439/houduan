package com.qsoa.admin.servier.impl;

import javax.management.RuntimeErrorException;

import org.hibernate.validator.constraints.SafeHtml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qsoa.admin.mapper.AdminInfoMapper;
import com.qsoa.admin.pojo.AdminInfo;
import com.qsoa.admin.servier.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminInfoMapper adminInfoMapper;
	private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void insert(AdminInfo adminInfo) {
		adminInfoMapper.insert(adminInfo);	
		//throw new RuntimeException("主动抛出个异常");
	}

}
