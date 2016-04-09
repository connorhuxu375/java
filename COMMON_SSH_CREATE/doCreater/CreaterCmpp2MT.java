

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
			this.put("MSG_ID", 				new LabelInfo("��Ϣ��ʶ", 	FormType._101TEXT , 	SearchType._2STRING  + SearchType._0NSEARCH));//type:int
			this.put("REGISTERED_DELIVERY", new LabelInfo("����״̬", 	FormType._201SELECT , 	SearchType._0NO   ));//type:int
			this.put("MSG_LEVEL", 			new LabelInfo("��Ϣ����", 	FormType._201SELECT , 	SearchType._0NO ));//type:int
			this.put("SERVICE_ID", 			new LabelInfo("ҵ������", 	FormType._101TEXT , 	SearchType._0NO ));//type:varchar
			this.put("FEE_USERTYPE", 		new LabelInfo("�Ʒ��û�����", FormType._201SELECT , 	SearchType._0NO ));//type:int
			this.put("FEE_TERMINAL_ID", 	new LabelInfo("�Ʒ��û�", 	FormType._101TEXT , 	SearchType._0NO ));//type:int
			this.put("MSG_FMT", 			new LabelInfo("��Ϣ��ʽ", 	FormType._101TEXT , 	SearchType._0NO));//type:int
			this.put("MSG_SRC", 			new LabelInfo("��Ϣ������Դ", FormType._101TEXT , 		SearchType._0NO));//type:varchar
			this.put("FEETYPE", 			new LabelInfo("�ʷ����", 	FormType._101TEXT , 	SearchType._0NO ));//type:varchar
			this.put("FEECODE", 			new LabelInfo("�ʷѴ���", 	FormType._101TEXT , 	SearchType._0NO ));//type:varchar
			this.put("VALID_TIME", 			new LabelInfo("�����Ч��", 	FormType._101TEXT , 	SearchType._0NO ));//type:datetime
			this.put("AT_TIME", 			new LabelInfo("��ʱ����ʱ��", FormType._101TEXT , 		SearchType._0NO ));//type:datetime
			this.put("SRC_ID", 				new LabelInfo("Դ����", 		FormType._101TEXT , 	SearchType._2STRING ));//type:varchar
			this.put("DESTUSR_TL", 			new LabelInfo("�û�����", 	FormType._101TEXT , 	SearchType._0NO));//type:int
			this.put("DEST_TERMINAL_ID", 	new LabelInfo("���ն��ź���", 	FormType._101TEXT , 	SearchType._2STRING ));//type:varchar
			this.put("MSG_LENGTH", 			new LabelInfo("��Ϣ����", 	FormType._101TEXT , 	SearchType._0NO));//type:int
			this.put("MSG_CONTENT", 		new LabelInfo("��Ϣ����", 	FormType._101TEXT , 	SearchType._2STRING ));//type:varchar
			this.put("RESERVE", 			new LabelInfo("����", 		FormType._101TEXT , 	SearchType._0NO ));//type:varchar
			this.put("ADD_TIME", 			new LabelInfo("���ʱ��", 	FormType._104DATE , 	SearchType._3DATE ));//type:datetime
			this.put("PROCESS_STATUS", 		new LabelInfo("����״̬", 	FormType._201SELECT , 	SearchType._1SELECT ));//type:int
			this.put("PROCESS_TIME", 		new LabelInfo("����ʱ��", 	FormType._104DATE , 	SearchType._3DATE ));//type:datetime
			this.put("RETRY_COUNT", 		new LabelInfo("���Դ���", 	FormType._101TEXT , 	SearchType._0NO));//type:int
			this.put("RESP_TIME", 			new LabelInfo("��Ӧʱ��", 	FormType._104DATE ,	 	SearchType._0NO ));//type:datetime
			this.put("RESP_RESULT", 		new LabelInfo("��Ӧ���", 	FormType._101TEXT , 	SearchType._2STRING  + SearchType._0NSEARCH));//type:int
			this.put("REPORT_TIME", 		new LabelInfo("����ʱ��", 	FormType._104DATE , 	SearchType._0NO ));//type:datetime
			this.put("REPORT_STAT", 		new LabelInfo("����״̬", 	FormType._201SELECT , 	SearchType._1SELECT ));//type:varchar
			this.put("REPORT_SUBMIT_TIME", 	new LabelInfo("�����ύʱ��", FormType._104DATE , 		SearchType._0NO ));//type:datetime
			this.put("REPORT_DONE_TIME", 	new LabelInfo("���浽��ʱ��", FormType._104DATE , 		SearchType._0NO ));//type:datetime
			this.put("NOTE", 				new LabelInfo("��ע", 		FormType._101TEXT , 	SearchType._0NO ));//type:varchar
			}};
			Map createrInfosdata = new HashMap<String,LabelInfo>(){{
				this.put("MSG_ID", 				new LabelInfo("��Ϣ��ʶ", 	FormType._101TEXT , 	SearchType._2STRING  + SearchType._0NSEARCH));//type:int
				this.put("SRC_ID", 				new LabelInfo("Դ����", 		FormType._101TEXT , 	SearchType._2STRING ));//type:varchar
				this.put("DEST_TERMINAL_ID", 	new LabelInfo("���ն��ź���", 	FormType._101TEXT , 	SearchType._2STRING ));//type:varchar
				this.put("MSG_CONTENT", 		new LabelInfo("��Ϣ����", 	FormType._101TEXT , 	SearchType._2STRING ));//type:varchar
				this.put("ADD_TIME", 			new LabelInfo("���ʱ��", 	FormType._104DATE , 	SearchType._3DATE ));//type:datetime
				this.put("PROCESS_STATUS", 		new LabelInfo("����״̬", 	FormType._201SELECT , 	SearchType._1SELECT ));//type:int
				this.put("PROCESS_TIME", 		new LabelInfo("����ʱ��", 	FormType._104DATE , 	SearchType._3DATE ));//type:datetime
				this.put("RESP_RESULT", 		new LabelInfo("��Ӧ���", 	FormType._101TEXT , 	SearchType._2STRING  + SearchType._0NSEARCH));//type:int
				this.put("REPORT_STAT", 		new LabelInfo("����״̬", 	FormType._201SELECT , 	SearchType._1SELECT ));//type:varchar
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
