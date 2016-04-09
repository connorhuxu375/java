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

import javax.persistence.Transient;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import com.arock.create.Config;
import com.zhk.core.common.util.ExcelHelper;
import com.zhk.core.common.util.TableUtil;

/**
 * 生成JAVA工具类
 * 描述：  
 * @author	: zhk@zhk.com
 * @version : 2014-8-21 下午11:46:29
 */
public class JavaUtil {
	public final  static Logger log = Logger.getLogger(JavaUtil.class);
	String javaFileFolder  ;
	String javaOutputFileRootDir  ;
	String modelFileRootDir  ;
	Config config ;
	DBHelperDao dao;
	Map<String,LabelInfo> searchs = new HashMap<String, LabelInfo>();
	public JavaUtil inits(Map<String,LabelInfo> searchs,Config config , DBHelperDao dao){
		this.dao = dao;
		
		if(searchs!=null){
			this.searchs = searchs;
		}
		this.config = config;
		  javaFileFolder = config.javaFileFolder;
		  javaOutputFileRootDir = config.javaOutputFileRootDir;
		  modelFileRootDir = config.modelFileRootDir;
		return this;
	} 
	int type = 0;
	public JavaUtil setType(int type ){
		this.type = type;
		return this;
	}
	
	public static void main(String [] agrs) throws Exception{
	}
	
	public void executeDatabase(String database ,String tableName) throws Exception{
		List tables = dao.getTable(database);
		for (Iterator iterator = tables.iterator(); iterator.hasNext();) {
			String tempTableName = (String) iterator.next();
			if(tableName.equals(tempTableName)){
				System.out.println("开始生成JAVA:"+tempTableName);
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
		
		
		File dir = new File(modelFileRootDir+"modelFile/modelJava");
		File [] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			String f = modelName.substring(0,1).toLowerCase();
			String o = modelName.substring(1);
			if(files[i].isFile()){
				createJavaFile(files[i],pkgName,modelName,f+o,javaOutputFileRootDir, tableName,fields);
			}
		}
		
	}
	
	public   void createJavaFile(File file , String subpackage , String Model , String model,String outputdir,String tableName,List<FieldInfo> fields) throws Exception{
		String modelFileName = file.getName();
		
		//处理输出的文件路径
		boolean xmlFile = modelFileName.indexOf("applicationContext")!=-1;
		boolean mdlFile = modelFileName.indexOf("$Model$.")!=-1;
		boolean actFile = modelFileName.indexOf("$Model$Action")!=-1;
		boolean serFile = modelFileName.indexOf("$Model$Service")!=-1;
		boolean daoFile = modelFileName.indexOf("$Model$Dao")!=-1;
		boolean tstFile = modelFileName.indexOf("Test$Model$")!=-1;
		 if(tstFile){
			outputdir = outputdir+"com/nvm/"+config.author+"/"+subpackage+"/test/";
		}else if(mdlFile || actFile){
			outputdir = outputdir+"com/nvm/"+config.author+"/"+subpackage+"/action/";
		}else if(serFile ){
			outputdir = outputdir+"com/nvm/"+config.author+"/"+subpackage+"/service/";
		}else if(daoFile ){
			outputdir = outputdir+"com/nvm/"+config.author+"/"+subpackage+"/dao/";
		}else if(xmlFile){
			outputdir = outputdir+"com/nvm/"+config.author+"/zpring/";
		}
		String createrFile = modelFileName
		.replaceAll("\\$Model\\$", Model)
		.replaceAll("\\$subpackage\\$", model)
		.replaceAll("_model", "");
		boolean creater = false ;
		if(type == Type.all){
			creater = true;
		} 
		if(type == Type.mdlFile && mdlFile){
			creater = true;
		} 
		if(type == Type.actFile && actFile){
			creater = true;
		} 
		if(type == Type.serFile && serFile){
			creater = true;
		} 
		if(type == Type.daoFile && daoFile){
			creater = true;
		} 
		if(type == Type.xmlFile && xmlFile){
			creater = true;
		} 
		if(type == Type.tstFile && tstFile){
			creater = true;
		}
		
		if(!creater){
			return ;
		}
		BufferedReader br=null;
		BufferedWriter bw=null;
		String line ="";
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
				//createFile.delete();
				createFile.createNewFile();
			}
			
			bw = new BufferedWriter(new FileWriter(createFile));
			
			
			log.info(fields);
			
			while ((line = br.readLine()) != null) {
				if(mdlFile){
					line = line.replaceAll("%%TableName%%", tableName);
					line = line.replaceAll("%%GetSetFields%%", getPojoFields(fields));
				}
				
				line = line.replaceAll("%%dao_fields_seach%%", getFiedSearch(fields));
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
				br.close();
				bw.flush();
				bw.close();
			} catch (Exception e) {
			}
		}
		System.out.println("模版文件:"+file.getAbsolutePath().replaceAll("\\\\", "/"));
		System.out.println("输出文件:"+outputdir+""+createrFile);
		System.out.println();
	}

	private String getFiedSearch(List<FieldInfo> fields) {
		StringBuffer sb = new StringBuffer();
		for (Iterator iterator = fields.iterator(); iterator.hasNext();) {
			FieldInfo fieldInfo = (FieldInfo) iterator.next();
			
			if(fieldInfo.getColumnName().equalsIgnoreCase("seqid")){
				continue;
			}
			if(this.searchs.get(fieldInfo.getColumnName()) == null){
				continue;
			}
			
			if(!Role.createJavaSearchDao(this.searchs.get(fieldInfo.getColumnName()).searchType)){
				continue;
			}
			 
			int realType = Role.getFileType(this.searchs.get(fieldInfo.getColumnName()).searchType);
			
			String search =  getItemFile(realType).toString();
			
			search=search.replaceAll("%%getMethodName%%", fieldInfo.getName);
			search=search.replaceAll("%%FieldNameVal%%", fieldInfo.fieldName);
			search=search.replaceAll("%%FieldLabelVal%%", fieldInfo.fieldLabel);
			sb.append(search);
		} 
		sb.append("\r\n\t\tlog.info(\"查询语句:\"+condHql.toString());");
		sb.append("\r\n\t\tlog.info(\"查询参数:\"+StringUtils.arrayToCommaDelimitedString(condParam.toArray()));");
		return sb.toString();
	}
	
	public StringBuffer getItemFile(int type)  {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			if(!Role.createJavaPojo(type)){
				return sb;
			}
			 
			 
			File file = new File(this.modelFileRootDir+"modelFile/modelJava/item/dao_fields_seach"+"_"+Role.getFileType(type)); 
			br = new BufferedReader(new FileReader(file));
			String line = null;
			
			while ((line = br.readLine()) != null) {
				sb.append("\r\n"+line);
			}
			return sb;
		} catch (IOException e) {
			if(br!=null){
				try {
					br.close();
				} catch (IOException e1) {
				}
			}
			e.printStackTrace();
			throw new RuntimeException("getItemFile error");
		}
		
	}
	

	private String getPojoFields(List<FieldInfo> fields) {
		log.info( " all  " + fields);
		StringBuffer sb = new StringBuffer();
		for (Iterator iterator = fields.iterator(); iterator.hasNext();) {
			FieldInfo fi = (FieldInfo) iterator.next();
			if(fi.fieldName.equalsIgnoreCase("seqid")){
				log.info(fi.getColumnName()+"seqid 对应列表没有参数");
				continue;
			}
			if(searchs.get(fi.getColumnName())==null){
				log.info(fi.getColumnName()+"对应列表没有参数");
				continue;
			}
			if(!Role.createJavaPojo(this.searchs.get(fi.getColumnName()).fromType)){
				log.info(fi.getColumnName()+" FormType._0NO  ");
				continue;
			}
			
			
			String field = getItemModelFile(searchs.get(fi.getColumnName()).fromType).toString();
			field = field.replaceAll("%%FieldColumn%%", fi.getColumnName());
			field = field.replaceAll("%%FieldLableVal%%", this.searchs.get(fi.getColumnName()).label);
			field = field.replaceAll("%%FieldMaxLen%%", fi.len+"");
			field = field.replaceAll("%%FieldNameVal%%", TableUtil.javakey(fi.fieldName));
			field = field.replaceAll("%%setMethodName%%", fi.setName);
			field = field.replaceAll("%%getMethodName%%", fi.getName);
			field = field.replaceAll("%%FieldType%%", TableUtil.dbtype2javatype(fi.fieldType));
			sb.append(field);
		}
		return sb.toString();
	}
	
	public StringBuffer getItemModelFile(int type)  {
		BufferedReader br=null;
		try {
			File file = new File(this.modelFileRootDir+"modelFile/modelJava/item/model_fields"+"_"+Role.getFileType(type)); 
			br = new BufferedReader(new FileReader(file));
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append("\n"+line);
			}
			return sb;
		} catch (IOException e) {
			if(br!=null){
				try {
					br.close();
				} catch (IOException e1) {
				}
			}
			e.printStackTrace();
			throw new RuntimeException("getItemFile error");
		}
		
	}
	
	
}
