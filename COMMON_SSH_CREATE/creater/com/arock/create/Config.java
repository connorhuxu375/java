package com.arock.create;

import java.util.HashMap;
import java.util.Map;

import com.arock.create.util.FormType;
import com.arock.create.util.LabelInfo;
import com.arock.create.util.SearchType;

public class Config {
	public String javaFileFolder = "generation";
	public String javaOutputFileRootDir = "f:/1learning/myeclipse.10/workspaces/COMMON_SSH_WEB_TEST/"+javaFileFolder+"/";
	public String jspFileFolder = "pages";
	public String jspOutputRootDir = "f:/1learning/myeclipse.10/workspaces/COMMON_SSH_WEB_TEST/WebRoot/"+jspFileFolder+"/";
	public String modelFileRootDir = "f:/1learning/myeclipse.10/workspaces/COMMON_SSH_JAVA/creater/";
	public String author = "huxu";
	public String authorInfo = "huxuquan@netviom.com";
}
/*
SELECT 'this.' 
+'put("'+ upper(syscolumns.name) +'", new LabelInfo('   
+'"'+upper(syscolumns.name) + '",' 
+' FormType._101TEXT ,'  
+' SearchType._2STRING '   
+'));//type:'+(systypes.name )
FROM syscolumns,systypes 
WHERE (syscolumns.id=object_id('TST_ACCOUNT') AND syscolumns.xusertype=systypes.xusertype) 
ORDER BY syscolumns.colorder
*/
