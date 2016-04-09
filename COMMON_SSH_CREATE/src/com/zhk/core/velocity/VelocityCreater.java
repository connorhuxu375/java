package com.zhk.core.velocity;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import com.zhk.core.db.Author;
import com.zhk.core.db.Table;


public abstract class VelocityCreater {
	String [] templateFiles;
	VelocityEngine ve ;

	public String[] getTemplateFiles() {
		return templateFiles;
	}

	public VelocityCreater setTemplateFile(String templateFile) {
		this.templateFiles = new String[]{templateFile};
		return this;
	}
	
	public VelocityCreater setTemplateFile(String ... templateFiles) {
		this.templateFiles = templateFiles;
		return this;
	}
	

	public void init() {
		Velocity.init(Config.velocityProperties);
		ve = new VelocityEngine();
		// 模板文件所在的路径
		ve.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, Config.modelFileRootDir);
		ve.setProperty(Velocity.INPUT_ENCODING, "GBK");
		ve.setProperty(Velocity.OUTPUT_ENCODING, "GBK");
		ve.init();
	}
	public void doCreater() throws Exception {
		for (int i = 0; i < templateFiles.length; i++) {
			String templateFile = templateFiles[i];
			createrOne(templateFile);
		}
		
	}
	public void createrOne(String templateFile) throws Exception {
			Template  template = ve.getTemplate(templateFile);
		//	BufferedWriter writer = writer = new BufferedWriter(new OutputStreamWriter(System.out));
			String outDir = null;
			String fileType = templateFile.replaceAll(".vm", "");
			String outFileName = getTable().getClassName()+fileType+".java";
			String packageName = getTable().getPackageName().replaceAll("\\.", "/")+"";
			
			if(fileType.equalsIgnoreCase("applicationContext")){
				outFileName = "applicationContext-"+getTable().getClassName()+".xml";
				outDir=Config.javaOutputFileRootDir+""+Config.spring ;
			}else
			if(fileType.startsWith("item_")){
				outFileName = fileType+"_"+getTable().getClassName()+".jsp";
				outDir=Config.jspOutputRootDir+""+this.getTable().getPrefix() ;
			}else 
			if(fileType.equalsIgnoreCase("Action")){
				outFileName = "Ajax"+getTable().getClassName()+fileType+".java";
				outDir=Config.javaOutputFileRootDir+""+packageName ;
			}else
			if(fileType.equalsIgnoreCase("Pojo")){
				outFileName = ""+getTable().getClassName()+".java";
				outDir=Config.javaOutputFileRootDir+""+packageName ;
			}else{
				outFileName = ""+getTable().getClassName()+fileType+".java";
				outDir=Config.javaOutputFileRootDir+""+packageName ;
			}
			
			File file = new File(outDir );
			//创建目录
			if(!file.exists()){
				file.mkdirs();
			}
			//备份
			String realFile = outDir+"/"+outFileName;
			File createFile = new File(realFile);
			if(createFile.exists()){
				String dest = createFile.getAbsoluteFile()+""+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				createFile.renameTo(new File(dest));
				createFile.createNewFile();
			}
			//输出
			FileWriter writer = new FileWriter(new File(realFile));
			VelocityContext context = new VelocityContext();
			context.put("A", getAuthor());
			context.put("T", getTable());
			template.merge(context, writer);
			writer.flush();
			writer.close();
			System.out.println("成功生成:"+realFile);
			
	}

	public  abstract  Author getAuthor() ;
	public abstract  Table getTable() ;
	
}
