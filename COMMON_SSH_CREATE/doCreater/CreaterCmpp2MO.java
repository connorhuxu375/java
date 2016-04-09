

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

public class CreaterCmpp2MO {
	
	
	public static void main(String[] args) throws Exception {
		Config config = new Config();
		String tableName   ="CMPP2_MO";
		Map createrInfos = new HashMap<String,LabelInfo>(){{
			this.put("MSG_ID", 			new LabelInfo("信息标识", 	FormType._101TEXT , SearchType._2STRING +SearchType._0NSEARCH ));//type:int
			this.put("DEST_ID", 		new LabelInfo("目的号码", 	FormType._101TEXT , SearchType._2STRING +SearchType._0NSEARCH));//type:varchar
			this.put("SERVICE_ID", 		new LabelInfo("业务类型", 	FormType._101TEXT , SearchType._2STRING  +SearchType._0NSEARCH));//type:varchar
			this.put("MSG_FMT", 		new LabelInfo("信息格式", 	FormType._101TEXT , SearchType._2STRING  +SearchType._0NSEARCH));//type:int
			this.put("SRC_TERMINAL_ID", new LabelInfo("源终端", 		FormType._101TEXT , SearchType._2STRING  ));//type:varchar
			this.put("MSG_LENGTH", 		new LabelInfo("消息长度", 	FormType._101TEXT , SearchType._2STRING  +SearchType._0NSEARCH ));//type:int
			this.put("MSG_CONTENT", 	new LabelInfo("消息内容",		FormType._101TEXT , SearchType._2STRING ));//type:varchar
			this.put("PROCESS_TIME", 	new LabelInfo("处理时间", 	FormType._104DATE , SearchType._3DATE ));//type:datetime
			this.put("PROCESS_STATUS", 	new LabelInfo("处理状态", 	FormType._201SELECT , SearchType._1SELECT ));//type:int
			this.put("ADD_TIME", 		new LabelInfo("添加时间", 	FormType._104DATE , SearchType._3DATE ));//type:datetime
			this.put("NOTE", 			new LabelInfo("备注", 		FormType._101TEXT , SearchType._2STRING+SearchType._0NSEARCH ));//type:varchar	
		}};
		ApplicationContext context = new ClassPathXmlApplicationContext(
		"com/nvm/rock/creater/spring/application*.xml");
		DBHelperDao dao = (DBHelperDao)context.getBean("dbHelperDao");
		
		new JavaUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(tableName);
		new JspUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(tableName);
	}
}
