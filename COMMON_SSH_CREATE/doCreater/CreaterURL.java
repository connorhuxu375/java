

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

public class CreaterURL {
	
	
	public static void main(String[] args) throws Exception {
		Config config = new Config();
		String tableName   ="URL_MAP";
		Map createrInfos = new HashMap<String,LabelInfo>(){{
			this.put("short_url".toUpperCase(),  new LabelInfo("短地址" ,		FormType._101TEXT,		SearchType._2STRING));
			this.put("long_url".toUpperCase(), new LabelInfo("长地址", 		FormType._101TEXT,	SearchType._2STRING));
			this.put("platform_name".toUpperCase(), new LabelInfo("平台名称",	FormType._101TEXT,  SearchType._2STRING));
			this.put("url_name".toUpperCase(), new LabelInfo("地址名称",		FormType._101TEXT,  SearchType._2STRING));
			this.put("access_count".toUpperCase(), new LabelInfo("访问次数",	FormType._101TEXT,  	SearchType._0NO));
			this.put("update_time".toUpperCase(), new LabelInfo("生成时间", 	FormType._104DATE,	SearchType._3DATE));
		 }};
		ApplicationContext context = new ClassPathXmlApplicationContext(
		"com/nvm/rock/creater/spring/application*.xml");
		DBHelperDao dao = (DBHelperDao)context.getBean("dbHelperDao");
		
		new JavaUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(tableName);
		new JspUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(tableName);
	}
}
