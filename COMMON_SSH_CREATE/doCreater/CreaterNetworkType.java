

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

public class CreaterNetworkType {
	
	
	public static void main(String[] args) throws Exception {
		Config config = new Config();
		String tableName   ="NET_NETWORK_TYPE";
		Map createrInfos = new HashMap<String,LabelInfo>(){{
			this.put("NET_NAME".toUpperCase(),  new LabelInfo("ÍøÂçÃû³Æ" ,	FormType._101TEXT   ,	SearchType._2STRING));
			this.put("NET_KEYWORD".toUpperCase(), new LabelInfo("¹Ø¼ü×Ö", 	FormType._101TEXT,		SearchType._2STRING));
		
		}};
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
		"com/nvm/rock/creater/spring/application*.xml");
		DBHelperDao dao = (DBHelperDao)context.getBean("dbHelperDao");
		
		new JavaUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(tableName);
		new JspUtil().inits(createrInfos ,config, dao).setType(Type.jsp_data).executeTable(tableName);
		new JspUtil().inits(createrInfos ,config, dao).setType(Type.jsp_input).executeTable(tableName);
	}
}
