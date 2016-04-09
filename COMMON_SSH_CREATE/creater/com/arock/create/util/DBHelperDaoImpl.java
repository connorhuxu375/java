package com.arock.create.util;

 /**
  * ���ɹ��̷������ݿ������
  */
import java.util.List;

import org.apache.log4j.Logger;

import com.zhk.core.base.daobase.DaoBaseImpl;
 
 
/**
 * 
 * ������Product DAO ʵ����
 * @author	: zhk@zhk.com Email:zhk@zhk.com
 * @version : Create v1.0
 * @date    : 2014-08-18 15
 */
public class DBHelperDaoImpl  extends DaoBaseImpl  implements DBHelperDao {
	public final  Logger log = Logger.getLogger(DBHelperDaoImpl.class);

	/****************************************
	 * ��������
	****************************************/
	@Override
	public List getFieldInfo(String tableName) {
		String sql =" select syscolumns.name as name , systypes.name as type, syscolumns.length from syscolumns,systypes"+
				" where (syscolumns.id=object_id('"+tableName+"') and syscolumns.xusertype=systypes.xusertype)"+
				" order by syscolumns.colorder";
		return queryListBySql(sql, new Object[]{});
	}

	@Override
	public List getTable(String databaseName) {
		String sql = "SELECT Name FROM "+databaseName+"..SysObjects Where XType='U' ORDER BY Name";
		return queryListBySql(sql, new Object[]{});
	}
	 
    
	
}
