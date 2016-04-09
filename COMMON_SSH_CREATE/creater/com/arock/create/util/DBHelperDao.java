package com.arock.create.util;


import java.util.List;

import com.zhk.core.base.daobase.DaoBase;
/**
 * 
 * 描述：Product DAO 接口
 * @author	: zhk@zhk.com Email:zhk@zhk.com
 * @version : Create v1.0
 * @date    : 2014-08-18 15
 */
public interface DBHelperDao extends DaoBase   {
	/*****************************************
	 * 其它DAO需提供的方法在此申明  
	 ****************************************/  
	List getTable(String databaseName);
	List getFieldInfo(String tableName);
}
