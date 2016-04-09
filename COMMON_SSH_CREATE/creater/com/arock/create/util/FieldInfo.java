package com.arock.create.util;
/**
 * 生成过程用类.勿动.
 * 描述：  
 * @author	: zhk@zhk.com
 * @version : 2014-8-21 下午11:45:32
 */
public   class FieldInfo {
		public String fieldName ; 
		public String getName ; 
		public String fieldLabel ;
		public String setName ; 
		public String fieldType;
		public int len;
		private String columnName;
		public String getColumnName() {
			return columnName.toUpperCase();
		}
		
		
		FieldInfo( String fieldName , String getName ,  String setName ,  String fieldType, int len,String columnName,String fieldLable){
				this.fieldName = fieldName;
			    this.getName = getName;
			    this.setName =setName;
			    this.fieldType=fieldType;
			    this.len=len;
			    this.columnName = columnName;
			    this.fieldLabel = fieldLable;
		}
		
		public static void main(String[] args)  {
	    	try {
	        	 System.loadLibrary("Referenced Libraries");
	    	 } catch (Exception e) {
	        	 System.out.println("11");
	    	 }
	   System.out.println("22");
	}

		
}