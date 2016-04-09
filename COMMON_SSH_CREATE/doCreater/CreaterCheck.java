

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

public class CreaterCheck {
	
	
	public static void main(String[] args) throws Exception {
		Config config = new Config();
		String tableName   ="VIEWNET_DEVICE_IPV4_CHECK";
		Map createrInfos = new HashMap<String,LabelInfo>(){{
			this.put("device_id".toUpperCase(),  new LabelInfo("�豸��ʶ" ,	FormType._101TEXT   ,	SearchType._2STRING));
			this.put("ip_address".toUpperCase(), new LabelInfo("�豸��ַ", 	FormType._101TEXT,		SearchType._2STRING));
			this.put("network_TYPE".toUpperCase(), new LabelInfo("�豸����", 	FormType._201SELECT,  SearchType._1SELECT));
			this.put("province_name".toUpperCase(), new LabelInfo("�豸ʡ��",	FormType._201SELECT,  SearchType._1SELECT));
			this.put("city_name".toUpperCase(), new LabelInfo("�豸����", 	FormType._101TEXT,		SearchType._2STRING));
		
			
			this.put("network_name".toUpperCase(), new LabelInfo("��������", 	FormType._101TEXT,SearchType._2STRING));
			this.put("ip_range".toUpperCase(), new LabelInfo("��ַ����", 		FormType._101TEXT,SearchType._0NO));
			this.put("ip_status".toUpperCase(), new LabelInfo("��ַ״̬", 	FormType._101TEXT,SearchType._2STRING));
			this.put("company_desc".toUpperCase(), new LabelInfo("��˾����", 	FormType._101TEXT,SearchType._2STRING));
			this.put("company_name".toUpperCase(), new LabelInfo("��˾����", 	FormType._101TEXT,SearchType._2STRING));
			this.put("company_add".toUpperCase(), new LabelInfo("��˾��ַ", 	FormType._101TEXT,SearchType._2STRING));
			this.put("company_phone".toUpperCase(), new LabelInfo("��˾�绰",	FormType._101TEXT,SearchType._0NO));
			this.put("data_resource".toUpperCase(), new LabelInfo("������Դ",	FormType._101TEXT,SearchType._2STRING));
			this.put("search_time".toUpperCase(), new LabelInfo("�˶�ʱ��", 	FormType._104DATE,SearchType._3DATE));
		}};
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
		"com/nvm/rock/creater/spring/application*.xml");
		DBHelperDao dao = (DBHelperDao)context.getBean("dbHelperDao");
		
		new JavaUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(tableName);
		new JspUtil().inits(createrInfos  ,config, dao).setType(Type.jsp_data).executeTable(tableName);
	}
}
