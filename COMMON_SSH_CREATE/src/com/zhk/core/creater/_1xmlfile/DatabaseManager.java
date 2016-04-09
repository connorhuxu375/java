package com.zhk.core.creater._1xmlfile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.zhk.core.db.Colunm;
import com.zhk.core.db.Table;
import com.zhk.core.util.JaxbUtil;
import com.zhk.core.util.TableUtil;

/**
 * 
 * 描述： 从数据库 依据TABLE NAME 生成初步的XML文档  
 * @author	: zhk@zhk.com
 * @version : 2014-11-28 上午11:37:44
 */
public class DatabaseManager {
	public void executeTable(String table , String filename) throws Exception{
		
		ApplicationContext context = new FileSystemXmlApplicationContext(
		"file://f:/1learning/myeclipse.10/workspaces/COMMON_SSH_CREATE/src/com/nvm/rock/creater/zpring/application*.xml");
		
		
		TableDao dao = (TableDao)context.getBean("tableDaoImpl");
		String packageName = TableUtil.tableNameToPackageName(table);
		String objectName = TableUtil.columnNameToFieldName(table);
		String className = TableUtil.tableNameToObjectName(table);
		String prefix = TableUtil.tableNameToPackageName(table);
		
    	Table t = new Table();
		t.setClassName(className);
		t.setPackageName("com.nvm.huxu."+packageName);
		t.setObjectName(objectName);
		t.setTableName(table);
		t.setPrefix(prefix);
		
		List list = dao.getFieldInfo(table);
		System.out.println("list:"+list.size());
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objs = (Object[]) iterator.next();
			String columnName = (String)objs[0];
			String fieldName =TableUtil.columnNameToFieldName(columnName);
			String setName =TableUtil.columnNameToSetMethodName(columnName);
			String getName =TableUtil.columnNameToGetMethodName(columnName);
			String fieldType =(String)objs[1] ;
			Short len =(Short)objs[2] ;
			Colunm c1 = new Colunm();
			c1.setLabel(columnName.toUpperCase()+" lable");
			
			c1.setColumn(columnName.toUpperCase());
			c1.setCheckType(TableUtil.dbtype2checktype(fieldType).trim());
			c1.setJavaType(TableUtil.dbtype2javatype(fieldType).trim());
			c1.setFormat(TableUtil.dbtype2format(fieldType).trim());
			
			c1.setMax(len);
			c1.setMin(0);
			c1.setExport(1);
			c1.setMust(1);
			t.getColunms().add(c1);
		}
		
		String xmlstr = JaxbUtil.vo2xml(t);
		File file = new File(filename);
		BufferedWriter fr = new BufferedWriter(new  FileWriter(file));
		fr.write(xmlstr);
		
		fr.close();
		System.out.println("生成XML成功:"+filename);
	}
	
	public static void main(String[] args) throws Exception {
		String tableName = "VIEW_ACC_STUDENT_MAP";
		new DatabaseManager().executeTable(tableName,"F:/1learning/myeclipse.10/workspaces/COMMON_SSH_CREATE/src/com/nvm/rock/creater/_1xmlfile/"+tableName+".xml");
	}
}	
