

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

public class CreaterTstProduct {
	
	
	public static void main(String[] args) throws Exception {
		String tableName   ="TST_PRODUCT";
		Config config = new Config();
		Map createrInfos = new HashMap<String,LabelInfo>(){{
			//this.put("SEQID", new LabelInfo("SEQID", FormType._101TEXT , SearchType._2STRING ));//type:int
			this.put("PRODUCT_CODE", new LabelInfo("��Ʒ����", 	FormType._101TEXT , 				SearchType._2STRING ));//type:varchar
			this.put("PRODUCT_NAME", new LabelInfo("��Ʒ����", 	FormType._101TEXT , 				SearchType._2STRING ));//type:varchar
			this.put("PRODUCT_TYPE", new LabelInfo("��Ʒ����", 	FormType._201SELECT , 				SearchType._1SELECT ));//type:int
			this.put("PRODUCT_LEVEL", new LabelInfo("��Ʒ����",FormType._101TEXT , 				SearchType._1SELECT+SearchType._0NSEARCH ));//type:int
			this.put("PRICE", new LabelInfo("�۸�", 				FormType._101TEXT , 				SearchType._2STRING+SearchType._0NSEARCH ));//type:numeric
			this.put("STATUS", new LabelInfo("״̬", 				FormType._201SELECT , 				SearchType._1SELECT ));//type:int
			this.put("SPNUMBER", new LabelInfo("SP ��", 			FormType._101TEXT , 				SearchType._2STRING ));//type:varchar
			this.put("SERVICE_CODE", new LabelInfo("ҵ�����", 	FormType._101TEXT , 				SearchType._2STRING ));//type:varchar
			this.put("VAC_PRODUCT", new LabelInfo("�Ƿ�VAC��Ʒ", 	FormType._202RADIO , 				SearchType._1SELECT ));//type:int
			this.put("CREATE_TIME", new LabelInfo("���ʱ��",	FormType._104DATE+FormType._0NFORM,	SearchType._3DATE ));//type:datetime
			this.put("NOTE", new LabelInfo("��ע", 					FormType._101TEXT , 				SearchType._2STRING ));//type:varchar
		}};
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
		"com/nvm/rock/creater/spring/application*.xml");
		DBHelperDao dao = (DBHelperDao)context.getBean("dbHelperDao");
		//new JavaUtil().inits(createrInfos,config , dao).setType(Type.all).executeTable(tableName);
		new JspUtil().inits(createrInfos ,config , dao).setType(Type.jsp_input).executeTable(tableName);
		DoBakFile.bakAll(config);
		
	}
}
