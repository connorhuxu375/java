

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.arock.create.Config;
import com.arock.create.util.DBHelperDao;
import com.arock.create.util.FormType;
import com.arock.create.util.JavaUtil;
import com.arock.create.util.JspUtil;
import com.arock.create.util.LabelInfo;
import com.arock.create.util.SearchType;
import com.arock.create.util.Type;

public class CreaterCmpp2MT {
	
	
	public static void main(String[] args) throws Exception {
		Config config = new Config();
		String tableName   ="CMPP2_MT";
		Map createrInfos = new HashMap<String,LabelInfo>(){{
			this.put("MSG_ID", 				new LabelInfo("信息标识", 	FormType._101TEXT , 	SearchType._2STRING  + SearchType._0NSEARCH));//type:int
			this.put("REGISTERED_DELIVERY", new LabelInfo("返回状态", 	FormType._201SELECT , 	SearchType._0NO   ));//type:int
			this.put("MSG_LEVEL", 			new LabelInfo("信息级别", 	FormType._201SELECT , 	SearchType._0NO ));//type:int
			this.put("SERVICE_ID", 			new LabelInfo("业务类型", 	FormType._101TEXT , 	SearchType._0NO ));//type:varchar
			this.put("FEE_USERTYPE", 		new LabelInfo("计费用户类型", FormType._201SELECT , 	SearchType._0NO ));//type:int
			this.put("FEE_TERMINAL_ID", 	new LabelInfo("计费用户", 	FormType._101TEXT , 	SearchType._0NO ));//type:int
			this.put("MSG_FMT", 			new LabelInfo("信息格式", 	FormType._101TEXT , 	SearchType._0NO));//type:int
			this.put("MSG_SRC", 			new LabelInfo("信息内容来源", FormType._101TEXT , 		SearchType._0NO));//type:varchar
			this.put("FEETYPE", 			new LabelInfo("资费类别", 	FormType._101TEXT , 	SearchType._0NO ));//type:varchar
			this.put("FEECODE", 			new LabelInfo("资费代码", 	FormType._101TEXT , 	SearchType._0NO ));//type:varchar
			this.put("VALID_TIME", 			new LabelInfo("存活有效期", 	FormType._101TEXT , 	SearchType._0NO ));//type:datetime
			this.put("AT_TIME", 			new LabelInfo("定时发送时间", FormType._101TEXT , 		SearchType._0NO ));//type:datetime
			this.put("SRC_ID", 				new LabelInfo("源号码", 		FormType._101TEXT , 	SearchType._2STRING ));//type:varchar
			this.put("DESTUSR_TL", 			new LabelInfo("用户数量", 	FormType._101TEXT , 	SearchType._0NO));//type:int
			this.put("DEST_TERMINAL_ID", 	new LabelInfo("接收短信号码", 	FormType._101TEXT , 	SearchType._2STRING ));//type:varchar
			this.put("MSG_LENGTH", 			new LabelInfo("信息长度", 	FormType._101TEXT , 	SearchType._0NO));//type:int
			this.put("MSG_CONTENT", 		new LabelInfo("信息内容", 	FormType._101TEXT , 	SearchType._2STRING ));//type:varchar
			this.put("RESERVE", 			new LabelInfo("保留", 		FormType._101TEXT , 	SearchType._0NO ));//type:varchar
			this.put("ADD_TIME", 			new LabelInfo("添加时间", 	FormType._104DATE , 	SearchType._3DATE ));//type:datetime
			this.put("PROCESS_STATUS", 		new LabelInfo("处理状态", 	FormType._201SELECT , 	SearchType._1SELECT ));//type:int
			this.put("PROCESS_TIME", 		new LabelInfo("处理时间", 	FormType._104DATE , 	SearchType._3DATE ));//type:datetime
			this.put("RETRY_COUNT", 		new LabelInfo("重试次数", 	FormType._101TEXT , 	SearchType._0NO));//type:int
			this.put("RESP_TIME", 			new LabelInfo("响应时间", 	FormType._104DATE ,	 	SearchType._0NO ));//type:datetime
			this.put("RESP_RESULT", 		new LabelInfo("响应结果", 	FormType._101TEXT , 	SearchType._2STRING  + SearchType._0NSEARCH));//type:int
			this.put("REPORT_TIME", 		new LabelInfo("报告时间", 	FormType._104DATE , 	SearchType._0NO ));//type:datetime
			this.put("REPORT_STAT", 		new LabelInfo("报告状态", 	FormType._201SELECT , 	SearchType._1SELECT ));//type:varchar
			this.put("REPORT_SUBMIT_TIME", 	new LabelInfo("报告提交时间", FormType._104DATE , 		SearchType._0NO ));//type:datetime
			this.put("REPORT_DONE_TIME", 	new LabelInfo("报告到达时间", FormType._104DATE , 		SearchType._0NO ));//type:datetime
			this.put("NOTE", 				new LabelInfo("备注", 		FormType._101TEXT , 	SearchType._0NO ));//type:varchar
			}};
			Map createrInfosdata = new HashMap<String,LabelInfo>(){{
				this.put("MSG_ID", 				new LabelInfo("信息标识", 	FormType._101TEXT , 	SearchType._2STRING  + SearchType._0NSEARCH));//type:int
				this.put("SRC_ID", 				new LabelInfo("源号码", 		FormType._101TEXT , 	SearchType._2STRING ));//type:varchar
				this.put("DEST_TERMINAL_ID", 	new LabelInfo("接收短信号码", 	FormType._101TEXT , 	SearchType._2STRING ));//type:varchar
				this.put("MSG_CONTENT", 		new LabelInfo("信息内容", 	FormType._101TEXT , 	SearchType._2STRING ));//type:varchar
				this.put("ADD_TIME", 			new LabelInfo("添加时间", 	FormType._104DATE , 	SearchType._3DATE ));//type:datetime
				this.put("PROCESS_STATUS", 		new LabelInfo("处理状态", 	FormType._201SELECT , 	SearchType._1SELECT ));//type:int
				this.put("PROCESS_TIME", 		new LabelInfo("处理时间", 	FormType._104DATE , 	SearchType._3DATE ));//type:datetime
				this.put("RESP_RESULT", 		new LabelInfo("响应结果", 	FormType._101TEXT , 	SearchType._2STRING  + SearchType._0NSEARCH));//type:int
				this.put("REPORT_STAT", 		new LabelInfo("报告状态", 	FormType._201SELECT , 	SearchType._1SELECT ));//type:varchar
				}};
		ApplicationContext context = new ClassPathXmlApplicationContext(
		"com/nvm/rock/creater/spring/application*.xml");
		DBHelperDao dao = (DBHelperDao)context.getBean("dbHelperDao");
		
		new JavaUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(tableName);
		new JspUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(tableName);
		
		new JavaUtil().inits(createrInfosdata, config, dao).setType(Type.daoFile).executeTable(tableName);
		new JspUtil().inits(createrInfosdata, config, dao).setType(Type.jsp_data).executeTable(tableName);
		DoBakFile.bakAll(config);
	}
}
