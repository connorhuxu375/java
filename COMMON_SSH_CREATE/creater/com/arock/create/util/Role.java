package com.arock.create.util;

public class Role {
	public static int getRole(int type){
		return type%100/10 ;
	}
	
	public static int getFileType(int type){
		 if(getRole(type)>=1){
			 return type -(getRole(type)*10);
		 }else{
			 return type;
		 }
	}
	public static void main(String[] args) {
		System.out.println(Role.createJavaSearchDao(101));
		System.out.println(Role.getFileType(101));
	}
	/**
	 * 是否生成POJO
	 * 描述：  
	 * @author : zhk@zhk.com
	 * @version: 2014-10-27 下午03:33:32
	 * @param type
	 * @return  
	 * 返回值:boolean
	 */
	public static boolean createJavaPojo(int type){
		return type!= FormType._0NO ;
	}
	/**
	 * 是否生成FORM HTML
	 * 描述：  
	 * @author : zhk@zhk.com
	 * @version: 2014-10-27 下午03:34:17
	 * @param type
	 * @return  
	 * 返回值:boolean
	 */
	public static boolean createJspForm(int type){
		return !(type== FormType._0NO  ||  getRole(type)>0 );
	}
	/**
	 * 是否创建DAO查询HQL
	 * 描述：  
	 * @author : zhk@zhk.com
	 * @version: 2014-10-27 下午03:33:22
	 * @param type
	 * @return  
	 * 返回值:boolean
	 */
	public static boolean createJavaSearchDao(int type){
		return !(type== SearchType._0NO  ||  getRole(type)>0);
	}
	
	/**
	 * 是否生成查询表单HTML
	 * 描述：  
	 * @author : zhk@zhk.com
	 * @version: 2014-10-27 下午03:35:16
	 * @param type
	 * @return  
	 * 返回值:boolean
	 */
	public static boolean createJspSearchForm(int type){
		return createJavaSearchDao(type);
	}
	
	/**
	 * 是否生成查询表单HTML
	 * 描述：  
	 * @author : zhk@zhk.com
	 * @version: 2014-10-27 下午03:35:16
	 * @param type
	 * @return  
	 * 返回值:boolean
	 */
	public static boolean createJspList(int type){
		return type!= SearchType._0NO ;
	}
}
