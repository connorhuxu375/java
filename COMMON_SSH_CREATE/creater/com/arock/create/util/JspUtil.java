package com.arock.create.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.arock.create.Config;
import com.zhk.core.common.util.TableUtil;
/**
 * 生成JSP工具类
 * 描述：  
 * @author	: zhk@zhk.com
 * @version : 2014-8-21 下午11:46:13
 */
public class JspUtil {
	public final  static Logger log = Logger.getLogger(JavaUtil.class);
	String jspFileFolder ;
	String jspOutputRootDir  ;
	String modelFileRootDir ;
	Config config ;
	DBHelperDao dao;
	Map<String,LabelInfo> searchs = new HashMap<String, LabelInfo>();
	public JspUtil inits(Map<String,LabelInfo> searchs ,Config config , DBHelperDao dao){
		this.dao = dao;
		
		if(searchs!=null){
			this.searchs = searchs;
		}
		
		this.config = config;
		jspFileFolder = config.jspFileFolder;
		jspOutputRootDir = config.jspOutputRootDir;
		modelFileRootDir = config.modelFileRootDir;
		  
		return this;
	}  
	int type = 0;
	public JspUtil setType(int type ){
		this.type = type;
		return this;
	}
 
	
	public void executeDatabase(String database ,String tableName) throws Exception{
		List tables = dao.getTable(database);
		for (Iterator iterator = tables.iterator(); iterator.hasNext();) {
			String tempTableName = (String) iterator.next();
			if(tableName.equals(tempTableName)){
				System.out.println("开始生成JSP:"+tempTableName);
				executeTable(tableName);
			}
		}
	}
	
	
	public void executeTable(String table) throws Exception{
		List list = dao.getFieldInfo(table);
		List<FieldInfo> fields = new ArrayList<FieldInfo>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objs = (Object[]) iterator.next();
			String columnName = (String)objs[0];
			if(columnName.equalsIgnoreCase("seqid")){
				continue;
			}
			
			String fieldName =TableUtil.columnNameToFieldName(columnName);
			String setName =TableUtil.columnNameToSetMethodName(columnName);
			String getName =TableUtil.columnNameToGetMethodName(columnName);
			String fieldType =(String)objs[1] ;
			Short len =(Short)objs[2] ;
			FieldInfo fi = new FieldInfo( fieldName , getName , setName ,fieldType,len,columnName,"待处理");
			fields.add(fi);
		}
		String packageName = TableUtil.tableNameToPackageName(table);
		String objectName = TableUtil.tableNameToObjectName(table);
		createrModel(packageName, objectName,table,fields);
	}
	
	
	public   void createrModel(String pkgName,String modelName,String tableName,List<FieldInfo> fields) throws Exception {
		
		
		
		File dir = new File(modelFileRootDir+"modelFile/modelJsp");
		File [] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			String f = modelName.substring(0,1).toLowerCase();
			String o = modelName.substring(1);
			if(files[i].isFile()){
				createJavaFile(files[i],pkgName,modelName,f+o,jspOutputRootDir, tableName,fields);
			}
		}
		
	}
	
	public   void createJavaFile(File file , String subpackage , String Model , String model,String outputdir,String tableName,List<FieldInfo> fields) throws Exception{
		String modelFileName = file.getName();
		
		//处理输出的文件路径
		outputdir = outputdir+subpackage+"/"+model+"/"; 
		String createrFile = modelFileName
		.replaceAll("\\$Model\\$", Model)
		.replaceAll("\\$subpackage\\$", model)
		.replaceAll("_model", "");
		
		boolean jsp_data = modelFileName.indexOf("data")!=-1;
		boolean jsp_input  = modelFileName.indexOf("input")!=-1;
		boolean jsp_index= modelFileName.indexOf("index")!=-1;
		boolean jsp_menu= modelFileName.indexOf("menu")!=-1;
		boolean jsp_tabs= modelFileName.indexOf("tabs")!=-1;
		boolean creater = false ;
		if(type == Type.all){
			creater = true;
		} 
		if(type == Type.jsp_input && jsp_input){
			creater = true;
		}
		if(type == Type.jsp_data && jsp_data){
			creater = true;
		}
		if(type == Type.jsp_index && jsp_index){
			creater = true;
		}
		if(type == Type.jsp_menu && jsp_menu){
			creater = true;
		}
		if(type == Type.jsp_tabs && jsp_tabs){
			creater = true;
		}
		
		if(!creater){
			return;
		}
		
		
		BufferedReader br=null;
		BufferedWriter bw=null;
		try {
			br = new BufferedReader(new FileReader(file));
			try {
				File srcDir = new File(outputdir);
				if(!srcDir.exists()){
					srcDir.mkdirs();
				}
			} catch (Exception e) {
			}
			File createFile = new File(outputdir + "" + createrFile);
			if(createFile.exists()){
				String dest = createFile.getAbsoluteFile()+""+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				createFile.renameTo(new File(dest));
				createFile.createNewFile();
			}
			 
			bw = new BufferedWriter(new FileWriter(createFile));
			String line = null;
			while ((line = br.readLine()) != null) {
				
				
				line = line.replaceAll("%%jsp_field_inits%%",   getInitsItem(fields).toString());
				line = line.replaceAll("%%jsp_fields_search%%", getSearchItem(fields,2).append(getSearchItem(fields,3)).toString());
				line = line.replaceAll("%%jsp_fields_input%%",  getItemFormModel(fields).toString());
				line = line.replaceAll("%%jsp_fields_update%%", getItemModelUpdate(fields).toString());
				line = line.replaceAll("%%jsp_fields_list%%",   getItemModelList(fields).toString());
				line = line.replaceAll("%%jsp_fields_thead%%",  getItemFormModelThead(fields).toString());
				
				line = line.replaceAll("%%rootdir%%",jspFileFolder);
				line = line.replaceAll("%%Jquery%%", "\\$");
				line = line.replaceAll("%%subpackage%%", subpackage);
				line = line.replaceAll("%%Model%%", Model);
				line = line.replaceAll("%%model%%", model);
				line = line.replaceAll("%%author%%", config.author);
				line = line.replaceAll("%%authorInfo%%", config.authorInfo);
				line = line.replaceAll("%%version%%", "create version 1.0.0.0");
				line = line.replaceAll("%%date%%", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
				
				bw.write(line+"\r\n");
			}
		} catch (Exception e) {
			 throw e;
		}finally{
			try {
				if (br != null)
					br.close();
				if (bw != null)
					bw.flush();
				if (bw != null)
					bw.close();
			} catch (Exception e) {
			}
		}
		System.out.println("模版文件:"+file.getAbsolutePath().replaceAll("\\\\", "/"));
		System.out.println("输出文件:"+outputdir+""+createrFile);
		System.out.println();
		
	}
	
	


	public StringBuffer getInitsItem(List<FieldInfo> fields){
		//"firstName","lastName","password","city","sex","mylike","address" 
		StringBuffer sb = new StringBuffer();
		sb.append(getGeneratioinInfo("jsp_field_inits",true));
		sb.append("\r\n formitem_%%subpackage%%_%%Model%% = globalInits([");
		for (Iterator iterator = fields.iterator(); iterator.hasNext();) {
			FieldInfo fi = (FieldInfo) iterator.next();
			if(this.searchs.get(fi.getColumnName()) == null){
				System.out.println(fi.getColumnName()+" 不配置.");
				continue;
			}
			
			if(Role.createJspForm(this.searchs.get(fi.getColumnName()).fromType)){
				sb.append("\r\n\t'%%model%%_"+TableUtil.javakey(fi.fieldName)+"'"+" ,");
			}
			
			
		}
		sb.delete(sb.length()-2, sb.length());
		sb.append("\r\n]);");
		sb.append(getGeneratioinInfo("jsp_field_inits",false));
		return sb;
	}
	private Object getItemFormModelThead(List<FieldInfo> fields) {
		StringBuffer sb = new StringBuffer();
		StringBuffer one = this.getItemFile("jsp_fields_thead");
		sb.append(getGeneratioinInfo("jsp_fields_thead",true));
		for (Iterator iterator = fields.iterator(); iterator.hasNext();) {
			FieldInfo fi = (FieldInfo) iterator.next();
			if(this.searchs.get(fi.getColumnName()) == null){
				continue;
			}
			if(!Role.createJspList(this.searchs.get(fi.getColumnName()).fromType)){
				continue;
			}
				
			String item =  one.toString() ; 
			String fieldName = TableUtil.javakey(fi.fieldName);
			item = item.replaceAll("%%FieldLabelVal%%", this.searchs.get(fi.getColumnName()).label);
			item = item.replaceAll("%%FieldNameVal%%",  fieldName);
			sb.append(item);
		}
		sb.append(getGeneratioinInfo("jsp_fields_thead",false));
		return sb;
	}
	public StringBuffer getItemModelUpdate(List<FieldInfo> fields ){
		String type =  "jsp_fields_update";
		StringBuffer sb = new StringBuffer();
		StringBuffer one = this.getItemFile(type);
		sb.append(getGeneratioinInfo(type,true));
		for (Iterator iterator = fields.iterator(); iterator.hasNext();) {
			FieldInfo fi = (FieldInfo) iterator.next();
			if(this.searchs.get(fi.getColumnName()) == null){
				continue;
			}
			
			if(!Role.createJspForm(this.searchs.get(fi.getColumnName()).fromType)
					&& type.equals("jsp_fields_update")){
				continue;
			}
				
			String item =  one.toString() ; 
			String fieldName = TableUtil.javakey(fi.fieldName);
			item = item.replaceAll("%%FieldLabelVal%%", this.searchs.get(fi.getColumnName()).label);
			item = item.replaceAll("%%FieldNameVal%%",  fieldName);
			sb.append(item);
		}
		sb.append(getGeneratioinInfo(type,false));
		return sb;
	}
	public StringBuffer getItemModelList(List<FieldInfo> fields ){
		String type = "jsp_fields_list";
		StringBuffer sb = new StringBuffer();
		sb.append(getGeneratioinInfo(type,true));
		for (Iterator iterator = fields.iterator(); iterator.hasNext();) {
			FieldInfo fi = (FieldInfo) iterator.next();
			if(this.searchs.get(fi.getColumnName()) == null){
				continue;
			}
			
			if(!Role.createJspList(this.searchs.get(fi.getColumnName()).fromType) ){
				continue;
			}
			
			StringBuffer one = this.getItemFile(type+"_"+Role.getFileType(this.searchs.get(fi.getColumnName()).fromType));	
			String item =  one.toString() ; 
			String fieldName = TableUtil.javakey(fi.fieldName);
			item = item.replaceAll("%%FieldLabelVal%%", this.searchs.get(fi.getColumnName()).label);
			item = item.replaceAll("%%FieldNameVal%%",  fieldName);
			//item = item.replaceAll("%%Jquery%%",  "\\$");
			sb.append(item);
		}
		sb.append(getGeneratioinInfo(type,false));
		return sb;
	}
	
	public StringBuffer getItemFormModel(List<FieldInfo> fields){
		StringBuffer sb = new StringBuffer();
		sb.append(getGeneratioinInfo("jsp_fields_input",true));
		for (Iterator iterator = fields.iterator(); iterator.hasNext();) {
			FieldInfo fi = (FieldInfo) iterator.next();
			if(this.searchs.get(fi.getColumnName()) == null){
				continue;
			}
			
			if(!Role.createJspForm(this.searchs.get(fi.getColumnName()).fromType) ){
				continue;
			}
			
			StringBuffer one = this.getItemFile("jsp_fields_input_"+Role.getFileType(this.searchs.get(fi.getColumnName()).fromType));
			String item =  one.toString() ; 
			String fieldName = TableUtil.javakey(fi.fieldName);
			item = item.replaceAll("%%FieldLabelVal%%", this.searchs.get(fi.getColumnName()).label);
			item = item.replaceAll("%%FieldNameVal%%",  fieldName);
			sb.append(item);
		}
		sb.append(getGeneratioinInfo("jsp_fields_input",false));
		return sb;
	}
	
	 
	/**
	 * 
	 * 描述：  
	 * @author : zhk@zhk.com
	 * @version: 2014-8-19 下午02:13:00  
	 * selectType:1 : select 2:like 3:date  
	 * @return :
	 */
	public StringBuffer getSearchItem(List<FieldInfo> fields,int selectType){
		String type ="jsp_fields_search";
		StringBuffer sb = new StringBuffer();
		
		if(!Role.createJspSearchForm(selectType)){
			return sb;
		}
		
		sb.append(getGeneratioinInfo(type,true));
		
		List<FieldInfo> types = new ArrayList<FieldInfo>();
		for (int i = 0; i <fields.size(); i++) {
			FieldInfo fi = fields.get(i);
			int fieldSearchType=0;
			try {
				if(this.searchs.get(fi.getColumnName())==null){
					log.info(fi.getColumnName()+"对应列表没有参数");
					continue;
				}
				fieldSearchType = this.searchs.get(fi.getColumnName()).searchType;
			} catch (Exception e) {
				System.out.println(fi.getColumnName());
				e.printStackTrace();
			}
			if(selectType == 2 ){
				if(fieldSearchType  == 100 || fieldSearchType == 200){
					types.add(fi);
				}
			}else if(selectType == 3 ){
				if(fieldSearchType  == 300){
					types.add(fi);
				}
			} 
		}
		boolean has = false;
		int col = selectType == 3 ? 2 : 4 ;  
		for (int i = 0; i <types.size(); i++) {
			FieldInfo fi = types.get(i);
			StringBuffer one = this.getItemFile(type+"_"+Role.getFileType(this.searchs.get(fi.getColumnName()).searchType));
			String item =  one.toString() ; 
			String fieldName = TableUtil.javakey(fi.fieldName);
			item = item.replaceAll("%%FieldLabelVal%%", this.searchs.get(fi.getColumnName()).label);
			item = item.replaceAll("%%FieldNameVal%%",  fieldName);
			if( i%col == 0){
				if(i==0 ){
					sb.append("<tr>");
					has = true;
				}else{
					sb.append("</tr><tr>");
				}
			}
			sb.append(item);
		}
		int tdCount =col- types.size()%col;
		//如果正好.则不需加.
		boolean trFlag = tdCount>0;
		tdCount = types.size()%col == 0 ? 0 :tdCount;
		for (int i = 0; i < tdCount; i++) {
			if(col==2){
				sb.append("<td style='width:70px'>&nbsp;</td><td style='width:150px'>&nbsp;</td>" +
						  "<td style='width:70px'>&nbsp;</td><td style='width:150px'>&nbsp;</td>");
			}else{
				sb.append("<td style='width:70px'>&nbsp;</td><td style='width:150px'>&nbsp;</td>");
			}
		}
		if(trFlag&&has){
			sb.append("</tr>");
		}
		sb.append(getGeneratioinInfo(type,false));
		return sb;
	}
	
	public StringBuffer getItemFile(String fileName)  {
		BufferedReader br =null;
		try {
			File file = new File(modelFileRootDir+"modelFile/modelJsp/item/"+fileName); 
			br = new BufferedReader(new FileReader(file));
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line+"\r\n");
			}
			return sb;
		} catch (IOException e) {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e11) {
			}
			e.printStackTrace();
			throw new RuntimeException("getItemFile error");
		}
		
	}
	
	public String getGeneratioinInfo(String type, boolean start){
		if(
		  type.equalsIgnoreCase("jsp_fields_input")
		  ||type.equalsIgnoreCase("jsp_fields_thead")
		 ||type.equalsIgnoreCase("jsp_fields_search")){
			return "\r\n<!--=======generation "+(start?" start":"end")+"=====-->\r\n";
		}else
		if(
			type.equalsIgnoreCase("jsp_fields_inits")
		 ||type.equalsIgnoreCase("jsp_fields_update")
		 ||type.equalsIgnoreCase("jsp_fields_update")
		 ||type.equalsIgnoreCase("jsp_fields_list")
		 
		 
		){
			return "\r\n//=======generation "+(start ? "start":"  end")+"=====//\r\n";
		}
		return "";
	}
	
	
}
