

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

public class CreaterDevice {
	
	
	public static void main(String[] args) throws Exception {
		Config config = new Config();
		String tableName   ="NET_CHECK_DEVICE_CFG";
		Map createrInfos = new HashMap<String,LabelInfo>(){{
			this.put("device_id".toUpperCase(),  new LabelInfo("设备标识" ,	FormType._101TEXT   ,	SearchType._2STRING));
			this.put("use_status".toUpperCase(), new LabelInfo("是否启用", 	FormType._202RADIO,		SearchType._1SELECT));
			this.put("update_time".toUpperCase(), new LabelInfo("添加时间", 	FormType._104DATE,		SearchType._3DATE));
		}};
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
		"com/nvm/rock/creater/spring/application*.xml");
		DBHelperDao dao = (DBHelperDao)context.getBean("dbHelperDao");
		
		new JavaUtil().inits(createrInfos,config , dao).setType(Type.all).executeTable(tableName);
		new JspUtil().inits(createrInfos ,config, dao).setType(Type.jsp_data).executeTable(tableName);
		new JspUtil().inits(createrInfos ,config, dao).setType(Type.jsp_input).executeTable(tableName);
	}
}
