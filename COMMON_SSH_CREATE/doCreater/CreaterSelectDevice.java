

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
			this.put("device_id".toUpperCase(),  new LabelInfo("设备标识" ,	FormType._101TEXT   ,	SearchType._2STRING));
			this.put("select_device_id".toUpperCase(),  new LabelInfo("已选设备标识" ,	FormType._101TEXT   ,	SearchType._2STRING));
			this.put("device_ip".toUpperCase(), new LabelInfo("网络地址", 	FormType._101TEXT,		SearchType._2STRING));
			this.put("device_src_port".toUpperCase(), new LabelInfo("网络端口", 	FormType._101TEXT,		SearchType._2STRING));
			this.put("network_type".toUpperCase(), new LabelInfo("网络类型", 	FormType._201SELECT,		SearchType._1SELECT));
			this.put("over_type".toUpperCase(), new LabelInfo("协议类型", 	FormType._201SELECT,		SearchType._1SELECT));
			this.put("province_name".toUpperCase(), new LabelInfo("设备省份", 	FormType._201SELECT,		SearchType._1SELECT));
			this.put("city_name".toUpperCase(), new LabelInfo("设备城市", 	FormType._101TEXT,		SearchType._2STRING));
			
		}};
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
		"com/nvm/rock/creater/spring/application*.xml");
		DBHelperDao dao = (DBHelperDao)context.getBean("dbHelperDao");
		
		new JavaUtil().inits(createrInfos ,config, dao).setType(Type.mdlFile).executeTable(tableName);
	}
}
