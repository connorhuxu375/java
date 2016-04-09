

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
			this.put("MSG_ID", 			new LabelInfo("��Ϣ��ʶ", 	FormType._101TEXT , SearchType._2STRING +SearchType._0NSEARCH ));//type:int
			this.put("DEST_ID", 		new LabelInfo("Ŀ�ĺ���", 	FormType._101TEXT , SearchType._2STRING +SearchType._0NSEARCH));//type:varchar
			this.put("SERVICE_ID", 		new LabelInfo("ҵ������", 	FormType._101TEXT , SearchType._2STRING  +SearchType._0NSEARCH));//type:varchar
			this.put("MSG_FMT", 		new LabelInfo("��Ϣ��ʽ", 	FormType._101TEXT , SearchType._2STRING  +SearchType._0NSEARCH));//type:int
			this.put("SRC_TERMINAL_ID", new LabelInfo("Դ�ն�", 		FormType._101TEXT , SearchType._2STRING  ));//type:varchar
			this.put("MSG_LENGTH", 		new LabelInfo("��Ϣ����", 	FormType._101TEXT , SearchType._2STRING  +SearchType._0NSEARCH ));//type:int
			this.put("MSG_CONTENT", 	new LabelInfo("��Ϣ����",		FormType._101TEXT , SearchType._2STRING ));//type:varchar
			this.put("PROCESS_TIME", 	new LabelInfo("����ʱ��", 	FormType._104DATE , SearchType._3DATE ));//type:datetime
			this.put("PROCESS_STATUS", 	new LabelInfo("����״̬", 	FormType._201SELECT , SearchType._1SELECT ));//type:int
			this.put("ADD_TIME", 		new LabelInfo("���ʱ��", 	FormType._104DATE , SearchType._3DATE ));//type:datetime
			this.put("NOTE", 			new LabelInfo("��ע", 		FormType._101TEXT , SearchType._2STRING+SearchType._0NSEARCH ));//type:varchar	
		}};
		ApplicationContext context = new ClassPathXmlApplicationContext(
		"com/nvm/rock/creater/spring/application*.xml");
		DBHelperDao dao = (DBHelperDao)context.getBean("dbHelperDao");
		
		new JavaUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(tableName);
		new JspUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(tableName);
	}
}
