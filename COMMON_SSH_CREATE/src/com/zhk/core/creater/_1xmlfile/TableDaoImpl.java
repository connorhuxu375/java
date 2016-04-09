package com.zhk.core.creater._1xmlfile;

 /**
  * 生成过程访问数据库相关类
  */
import java.util.List;

import org.apache.log4j.Logger;

import com.zhk.core.base.daobase.DaoBaseImpl;
 
 
/**
 * 
 * 描述：Product DAO 实现类
 * @author	: zhk@zhk.com Email:zhk@zhk.com
 * @version : Create v1.0
 * @date    : 2014-08-18 15
 */
public class TableDaoImpl  extends DaoBaseImpl  implements TableDao {
	public final  Logger log = Logger.getLogger(TableDaoImpl.class);

	/****************************************
	 * 其它方法
	****************************************/
	@Override
	public List getFieldInfo(String tableName) {
		String sql =" select syscolumns.name as name , systypes.name as type, syscolumns.length from syscolumns,systypes"+
				" where (syscolumns.id=object_id('"+tableName+"') and syscolumns.xusertype=systypes.xusertype)"+
				" order by syscolumns.colorder";
		List list =  queryListBySql(sql, new Object[]{});
		System.out.println("list.size():"+list.size());
		return list ;
	}

	@Override
	public List getTable(String databaseName) {
		String sql = "SELECT Name FROM "+databaseName+"..SysObjects Where XType='U' ORDER BY Name";
		return queryListBySql(sql, new Object[]{});
	}
	 
    
	
}
