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
	 * �Ƿ�����POJO
	 * ������  
	 * @author : zhk@zhk.com
	 * @version: 2014-10-27 ����03:33:32
	 * @param type
	 * @return  
	 * ����ֵ:boolean
	 */
	public static boolean createJavaPojo(int type){
		return type!= FormType._0NO ;
	}
	/**
	 * �Ƿ�����FORM HTML
	 * ������  
	 * @author : zhk@zhk.com
	 * @version: 2014-10-27 ����03:34:17
	 * @param type
	 * @return  
	 * ����ֵ:boolean
	 */
	public static boolean createJspForm(int type){
		return !(type== FormType._0NO  ||  getRole(type)>0 );
	}
	/**
	 * �Ƿ񴴽�DAO��ѯHQL
	 * ������  
	 * @author : zhk@zhk.com
	 * @version: 2014-10-27 ����03:33:22
	 * @param type
	 * @return  
	 * ����ֵ:boolean
	 */
	public static boolean createJavaSearchDao(int type){
		return !(type== SearchType._0NO  ||  getRole(type)>0);
	}
	
	/**
	 * �Ƿ����ɲ�ѯ��HTML
	 * ������  
	 * @author : zhk@zhk.com
	 * @version: 2014-10-27 ����03:35:16
	 * @param type
	 * @return  
	 * ����ֵ:boolean
	 */
	public static boolean createJspSearchForm(int type){
		return createJavaSearchDao(type);
	}
	
	/**
	 * �Ƿ����ɲ�ѯ��HTML
	 * ������  
	 * @author : zhk@zhk.com
	 * @version: 2014-10-27 ����03:35:16
	 * @param type
	 * @return  
	 * ����ֵ:boolean
	 */
	public static boolean createJspList(int type){
		return type!= SearchType._0NO ;
	}
}
