package com.zhk.core.creater._2codefile;

import com.zhk.core.velocity.BakFileManager;
import com.zhk.core.velocity.TableCreater;

public class LetisCreater  {
	public static void main(String[] args) throws Exception {
		LetisCreater su = new LetisCreater();
		TableCreater pc = new TableCreater();
		pc.init();
		String xmlName = "VIEW_ACC_STUDENT_MAP.xml";//"USER_ACCOUNT_ROLE_MAP.xml";
		pc.parseTable("F:/1learning/myeclipse.10/workspaces/COMMON_SSH_CREATE/src/com/nvm/rock/creater/_2codefile/"+xmlName);
		pc.checkTable();
		pc.setTemplateFile(
				
			    "Pojo.vm",
				"Action.vm",
				"TestAction.vm",
				"Dao.vm",
				"DaoImpl.vm",
				
				"Service.vm",
				"ServiceImpl.vm",  
				"item_data.vm",
				"item_data_bt.vm",
				"item_input.vm",
				"item_detail.vm" ,
				"applicationContext.vm"
				)
				.doCreater();
		BakFileManager.bakAll();
	}
}
