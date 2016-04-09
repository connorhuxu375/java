

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
			this.put("device_id".toUpperCase(),  new LabelInfo("设备标识" ,	FormType._101TEXT   ,	SearchType._2STRING));
			this.put("ip_address".toUpperCase(), new LabelInfo("设备地址", 	FormType._101TEXT,		SearchType._2STRING));
			this.put("network_TYPE".toUpperCase(), new LabelInfo("设备网络", 	FormType._201SELECT,  SearchType._1SELECT));
			this.put("province_name".toUpperCase(), new LabelInfo("设备省份",	FormType._201SELECT,  SearchType._1SELECT));
			this.put("city_name".toUpperCase(), new LabelInfo("设备城市", 	FormType._101TEXT,		SearchType._2STRING));
		
			
			this.put("network_name".toUpperCase(), new LabelInfo("网络名称", 	FormType._101TEXT,SearchType._2STRING));
			this.put("ip_range".toUpperCase(), new LabelInfo("网址区间", 		FormType._101TEXT,SearchType._0NO));
			this.put("ip_status".toUpperCase(), new LabelInfo("网址状态", 	FormType._101TEXT,SearchType._2STRING));
			this.put("company_desc".toUpperCase(), new LabelInfo("公司描述", 	FormType._101TEXT,SearchType._2STRING));
			this.put("company_name".toUpperCase(), new LabelInfo("公司名称", 	FormType._101TEXT,SearchType._2STRING));
			this.put("company_add".toUpperCase(), new LabelInfo("公司地址", 	FormType._101TEXT,SearchType._2STRING));
			this.put("company_phone".toUpperCase(), new LabelInfo("公司电话",	FormType._101TEXT,SearchType._0NO));
			this.put("data_resource".toUpperCase(), new LabelInfo("数据来源",	FormType._101TEXT,SearchType._2STRING));
			this.put("search_time".toUpperCase(), new LabelInfo("核对时间", 	FormType._104DATE,SearchType._3DATE));
		}};
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
		"com/nvm/rock/creater/spring/application*.xml");
		DBHelperDao dao = (DBHelperDao)context.getBean("dbHelperDao");
		
		new JavaUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(tableName);
		new JspUtil().inits(createrInfos  ,config, dao).setType(Type.jsp_data).executeTable(tableName);
	}
}
