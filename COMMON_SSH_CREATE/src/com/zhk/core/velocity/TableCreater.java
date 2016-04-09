package com.zhk.core.velocity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;

import com.zhk.core.db.Author;
import com.zhk.core.db.Colunm;
import com.zhk.core.db.Table;
import com.zhk.core.util.JaxbUtil;


public class TableCreater extends VelocityCreater{
	Table table = null ;  
	Author author = new Author();
	public Table getTable() {
		return table;
	}

	public void checkTable( ) {
		for (Iterator iterator = table.getColunms().iterator(); iterator.hasNext();) {
			Colunm col = (Colunm) iterator.next();
			col.check();
			System.out.println("验证成功 "+col.getLabel()+"");
		}
	}

	public TableCreater( ) {
	}

	@Override
	public Author getAuthor() {
		return   author;
	}
	public void setAuthor(Author author) {
		    this.author = author;
	}
	public void setTable(Table table) {
	    this.table = table;
	}
	public   void  parseTable(String name) throws Exception {
		File file = new File(name);
		StringBuffer xml = new StringBuffer();
		BufferedReader fr = new BufferedReader(new  FileReader(file));
		String line = null;
		while((line=fr.readLine())!=null){
			xml.append(line);
		}
		fr.close();
		Table table = JaxbUtil.xml2vo(xml.toString(), Table.class);  
        this.table = table;
	}
}
