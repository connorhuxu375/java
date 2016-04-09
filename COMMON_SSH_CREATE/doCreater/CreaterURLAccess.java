

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

public class CreaterURLAccess {
	
	
	public static void main(String[] args) throws Exception {
		Config config = new Config();
		String tableName   ="URL_ACCESS";
		Map createrInfos = new HashMap<String,LabelInfo>(){{
			this.put("url_map_seqid".toUpperCase(), new LabelInfo("地址ID",		FormType._0NO,  SearchType._0NO));
			this.put("url_name".toUpperCase(), new LabelInfo("地址名称",		FormType._101TEXT,  SearchType._2STRING));
			this.put("client_ip".toUpperCase(), new LabelInfo("客户IP",		FormType._101TEXT,  SearchType._2STRING));
			this.put("access_time".toUpperCase(), new LabelInfo("访问时间", 	FormType._104DATE,	SearchType._3DATE));
		 }};
		ApplicationContext context = new ClassPathXmlApplicationContext(
		"com/nvm/rock/creater/spring/application*.xml");
		DBHelperDao dao = (DBHelperDao)context.getBean("dbHelperDao");
		
		new JavaUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(tableName);
		new JspUtil().inits(createrInfos ,config, dao).setType(Type.jsp_data).executeTable(tableName);
	}
}
