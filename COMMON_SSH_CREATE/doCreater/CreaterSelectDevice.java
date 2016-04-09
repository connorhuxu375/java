

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.arock.create.Config;
import com.arock.create.util.DBHelperDao;
import com.arock.create.util.FormType;
import com.arock.create.util.JavaUtil;
import com.arock.create.util.LabelInfo;
import com.arock.create.util.SearchType;
import com.arock.create.util.Type;

public class CreaterSelectDevice {
	
	
	public static void main(String[] args) throws Exception {
		Config config = new Config();
		String tableName   ="VIEWNET_SELECT_DEVICE";
		Map createrInfos = new HashMap<String,LabelInfo>(){{
			this.put("device_id".toUpperCase(),  new LabelInfo("�豸��ʶ" ,	FormType._101TEXT   ,	SearchType._2STRING));
			this.put("select_device_id".toUpperCase(),  new LabelInfo("��ѡ�豸��ʶ" ,	FormType._101TEXT   ,	SearchType._2STRING));
			this.put("device_ip".toUpperCase(), new LabelInfo("�����ַ", 	FormType._101TEXT,		SearchType._2STRING));
			this.put("device_src_port".toUpperCase(), new LabelInfo("����˿�", 	FormType._101TEXT,		SearchType._2STRING));
			this.put("network_type".toUpperCase(), new LabelInfo("��������", 	FormType._201SELECT,		SearchType._1SELECT));
			this.put("over_type".toUpperCase(), new LabelInfo("Э������", 	FormType._201SELECT,		SearchType._1SELECT));
			this.put("province_name".toUpperCase(), new LabelInfo("�豸ʡ��", 	FormType._201SELECT,		SearchType._1SELECT));
			this.put("city_name".toUpperCase(), new LabelInfo("�豸����", 	FormType._101TEXT,		SearchType._2STRING));
			
		}};
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
		"com/nvm/rock/creater/spring/application*.xml");
		DBHelperDao dao = (DBHelperDao)context.getBean("dbHelperDao");
		
		new JavaUtil().inits(createrInfos ,config, dao).setType(Type.mdlFile).executeTable(tableName);
	}
}
