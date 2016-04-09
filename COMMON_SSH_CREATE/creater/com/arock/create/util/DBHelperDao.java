package com.arock.create.util;


import java.util.List;

import com.zhk.core.base.daobase.DaoBase;
/**
 * 
 * ������Product DAO �ӿ�
 * @author	: zhk@zhk.com Email:zhk@zhk.com
 * @version : Create v1.0
 * @date    : 2014-08-18 15
 */
public interface DBHelperDao extends DaoBase   {
	/*****************************************
	 * ����DAO���ṩ�ķ����ڴ�����  
	 ****************************************/  
	List getTable(String databaseName);
	List getFieldInfo(String tableName);
}
