

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

public class CreaterTimeCfg {
	
	
	public static void main(String[] args) throws Exception {
		Config config = new Config();
		String tableName   ="NET_CHECK_TIME_CFG";
		Map createrInfos = new HashMap<String,LabelInfo>(){{
			this.put("WEEK_CYCLE".toUpperCase(),  new LabelInfo("每周周期" ,	FormType._203CHECKBOX   ,	SearchType._1SELECT));
			this.put("TIME_START".toUpperCase(), new LabelInfo("开始时间", 	FormType._104DATE,		SearchType._0NO));
			this.put("TIME_END".toUpperCase(), new LabelInfo("结束时间", 	FormType._104DATE,		SearchType._0NO));
			this.put("USER_STATUS".toUpperCase(), new LabelInfo("是否启用", 	FormType._202RADIO,		SearchType._1SELECT));
		}};
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
		"com/nvm/rock/creater/spring/application*.xml");
		DBHelperDao dao = (DBHelperDao)context.getBean("dbHelperDao");
		
		new JavaUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(tableName);
		new JspUtil().inits(createrInfos ,config, dao).setType(Type.jsp_data).executeTable(tableName);
		new JspUtil().inits(createrInfos ,config, dao).setType(Type.jsp_input).executeTable(tableName);
	}
}
