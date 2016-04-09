package com.zhk.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 转化表字段工具类
 * 描述：  
 * @author	: zhk@zhk.com
 * @version : 2014-8-21 下午11:47:17
 */
public class TableUtil {
	
	@SuppressWarnings("unchecked")     
	public static Method getGetMethod(Class objectClass, String fieldName) {     
	    StringBuffer sb = new StringBuffer();     
	    sb.append("get");     
	    sb.append(fieldName.substring(0, 1).toUpperCase());     
	    sb.append(fieldName.substring(1));     
	    try {     
	        return objectClass.getMethod(sb.toString());     
	    } catch (Exception e) {  
	    	throw new RuntimeException(fieldName+" error :"+e);   
	    }     
	}     
	@SuppressWarnings("unchecked")     
	public static Method getSetMethod(Class objectClass, String fieldName) {     
	    try {     
	        Class[] parameterTypes = new Class[1];     
	        Field field = objectClass.getDeclaredField(fieldName);     
	        parameterTypes[0] = field.getType();     
	        StringBuffer sb = new StringBuffer();     
	        sb.append("set");     
	        sb.append(fieldName.substring(0, 1).toUpperCase());     
	        sb.append(fieldName.substring(1));     
	        Method method = objectClass.getMethod(sb.toString(), parameterTypes);     
	        return method;     
	    } catch (Exception e) {     
	    	throw new RuntimeException(fieldName+" error :"+e);    
	    }     
	}   
	
	public static void invokeSet(Object o, String fieldName, Object value) {     
	    Method method = getSetMethod(o.getClass(), fieldName);     
	    try {     
	        method.invoke(o, new Object[] { value });     
	    } catch (Exception e) {     
	    	throw new RuntimeException(fieldName+" error :"+e);   
	    }     
	}     
	     
	     
	public static Object invokeGet(Object o, String fieldName) {     
	    Method method = getGetMethod(o.getClass(), fieldName);     
	    try {     
	        return method.invoke(o, new Object[0]);     
	    } catch (Exception e) {     
	        throw new RuntimeException(fieldName+" error :"+e); 
	    }     
	}
	
	public static Object sqlmap2objectvo( Map map , Class clazz ){
		Method [] mMethods = clazz.getMethods();
		Object objvo;
		try {
			objvo = clazz.newInstance();
		} catch (Exception e) {
			 throw new RuntimeException(clazz.getName()+" error :"+e);  
		}
		for (int i = 0; i < mMethods.length; i++) {
			 Method m = mMethods[i];
			 if(m.getName().startsWith("set")){
				 String fieldName =  setMethodNameToFieldName(m.getName()) ;
				 String columnName = setMethodNameToColumnName(m.getName());
				 Object obj = map.get(columnName);
				 if(obj == null){
					 continue;
				 }
				TableUtil.invokeSet(objvo , fieldName , obj);
			 }
		}
		return objvo;
	}
	
	public static String setMethodNameToFieldName(String setMethodName){
		setMethodName = setMethodName.substring(3);
		String a = setMethodName.substring(0,1);
		String b = setMethodName.substring(1,setMethodName.length());
		
		String fieldName =  a.toLowerCase() + b ;
		
		return fieldName;
		
	}
	public static String setMethodNameToColumnName(String setMethodName){
		//log.info(setMethodName);
		String columnName = "";
		//String mn = setMethodName.substring(3);
		Pattern p2 = Pattern.compile("[A-Z]{2,}");
		Matcher m2 = p2.matcher(setMethodName);
		while(m2.find()){
			String U = m2.group();
			setMethodName = setMethodName.replace(U, "_"+""+U.toLowerCase());
		}
		//log.info(setMethodName);
		Pattern p = Pattern.compile("[A-Z]");
		Matcher m = p.matcher(setMethodName);
		
		while(m.find()){
			String U = m.group();
			setMethodName = setMethodName.replace(U, "_"+U.toLowerCase());
		}
		columnName=setMethodName;
			
		columnName = columnName.substring(4);
		return columnName.toUpperCase();
	}
	public static String tableNameToObjectName(String tableName){
		tableName = columnNameToFieldName(tableName);
		String f = tableName.substring(0,1);
		String o = tableName.substring(1);
		String objectName = ""+f.toUpperCase()+o;
		return objectName;
	}
	public static String javakey(String key){
		if(key.equalsIgnoreCase("int")){
			return "kint";
		}else
		if(key.equalsIgnoreCase("float")){
			return "kfloat";
		}else
		if(key.equalsIgnoreCase("date")){
			return "kdate";
		}else
		if(key.equalsIgnoreCase("number")){
			return "knumber";
		}
		return key;
	}
	public static String dbtype2javatype(String dbtype){
		
		if(dbtype.equalsIgnoreCase("bit")){
			return " Integer ";
		}else if(dbtype.equalsIgnoreCase("int")){
			return " Integer ";
		}else if(dbtype.equalsIgnoreCase("float")){
			return " Float ";
		}else if(dbtype.equalsIgnoreCase("varchar")){
			return " String ";
		}else if(dbtype.equalsIgnoreCase("char")){
			return " String ";
		}else if(dbtype.equalsIgnoreCase("datetime")){
			return " Date ";
		}else if(dbtype.equalsIgnoreCase("date")){
			return " Date ";
		}else if(dbtype.equalsIgnoreCase("numeric")){
			return " Double ";
		}
		throw new RuntimeException("暂不支持:"+dbtype);
	}
	public static String dbtype2checktype(String dbtype){
		
		if(dbtype.equalsIgnoreCase("bit")){
			return " radio ";
		}else if(dbtype.equalsIgnoreCase("int")){
			return " select ";
		}else if(dbtype.equalsIgnoreCase("float")){
			return " text ";
		}else if(dbtype.equalsIgnoreCase("varchar")){
			return " text ";
		}else if(dbtype.equalsIgnoreCase("char")){
			return " text ";
		}else if(dbtype.equalsIgnoreCase("datetime")){
			return " datetime ";
		}else if(dbtype.equalsIgnoreCase("date")){
			return " datetime ";
		}else if(dbtype.equalsIgnoreCase("numeric")){
			return " text ";
		}
		throw new RuntimeException("暂不支持:"+dbtype);
	}
	public static String dbtype2format(String dbtype){
		
		if(dbtype.equalsIgnoreCase("bit")){
			return " int ";
		}else if(dbtype.equalsIgnoreCase("int")){
			return " int ";
		}else if(dbtype.equalsIgnoreCase("float")){
			return " float ";
		}else if(dbtype.equalsIgnoreCase("varchar")){
			return " text ";
		}else if(dbtype.equalsIgnoreCase("char")){
			return " text ";
		}else if(dbtype.equalsIgnoreCase("datetime")){
			return " datetime ";
		}else if(dbtype.equalsIgnoreCase("date")){
			return " datetime ";
		}else if(dbtype.equalsIgnoreCase("numeric")){
			return " float ";
		}
		throw new RuntimeException("暂不支持:"+dbtype);
	}
	public static String tableNameToPackageName(String tableName){
		 int index = tableName.indexOf("_");
		 if(index <= 0){
			 Matcher m = Pattern.compile("^[a-z0-9]+[A-Z]").matcher(tableName);;
			 if(m.find()){
				index =  m.end()-1;
			 }else{
				if(tableName.length()>4){
					index = 4;
				}else{
					index = tableName.length();
				}
			 }
		 }
		 index = index <=0 ? 0:index ;
		 if(index==0){
			 throw new RuntimeException("TABLE NAME ERROR");
		 }
		 String packname = tableName.toLowerCase().substring(0,index);
		 return packname;
	}
	
	public static String columnNameToFieldName(String columnName){
		columnName = columnName.toLowerCase();
		Pattern p = Pattern.compile("_([A-Za-z])");
		Matcher m = p.matcher(columnName);
		while(m.find()){
			String U = m.group(0);
			String U1 = m.group(1);
			columnName = columnName.replaceAll(U,U1.toUpperCase());
		}
		return columnName;
		
	}
	public static String columnNameToSetMethodName(String columnName){
		
		String fieldName = columnNameToFieldName(columnName);
		String f = fieldName.substring(0,1);
		String o = fieldName.substring(1);
		String setMethodName = "set"+f.toUpperCase()+o;
		return setMethodName;
		
	}
	public static String columnNameToGetMethodName(String columnName){
		
		String fieldName = columnNameToFieldName(columnName);
		String f = fieldName.substring(0,1);
		String o = fieldName.substring(1);
		String setMethodName = "get"+f.toUpperCase()+o;
		return setMethodName;
		
	}
	
	public static void main(String[] args) {
		System.out.println("iiiiii"+columnNameToFieldName("USER_ACCOUNT_APPLICATION"));
		System.out.println(setMethodNameToColumnName("setSeqid"));
		System.out.println(tableNameToObjectName("USER_ACCOUNT_APPLICATION"));
	}
	 
	public static String tableNameToPrefixName(String table) {
		 
		return null;
	}
	
}
