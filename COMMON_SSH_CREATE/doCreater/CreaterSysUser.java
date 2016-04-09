

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

public class CreaterSysUser {
	
	
	public static void main(String[] args) throws Exception {
		Config config = new Config();
		String tableName   ="SYS_USER";
		Map createrInfos = new HashMap<String,LabelInfo>(){{
			this.put("ACCOUNT", 	new LabelInfo("用户帐号", FormType._101TEXT , 	SearchType._2STRING ));//type:varchar
			this.put("PASSWORD", 	new LabelInfo("帐号密码", FormType._102PASSWORD , SearchType._2STRING+SearchType._0NSEARCH ));//type:varchar
			this.put("ROLETYPE", 	new LabelInfo("角色类型", FormType._201SELECT , 	SearchType._1SELECT ));//type:int
			this.put("USE_STATUS", 	new LabelInfo("使用状态", FormType._201SELECT , 	SearchType._1SELECT ));//type:int
			this.put("PROVINCE_NAME", new LabelInfo("省份", 	FormType._201SELECT , 	SearchType._1SELECT ));//type:varchar
			this.put("USERNUMBER", 	new LabelInfo("手机号码", FormType._101TEXT , 	SearchType._2STRING ));//type:varchar
			this.put("EMAIL",	 	new LabelInfo("邮箱地址", FormType._101TEXT , 	SearchType._2STRING ));//type:varchar
			this.put("NOTE", 		new LabelInfo("备注信息", FormType._101TEXT , 	SearchType._2STRING ));//type:varchar
		}};
			 
		ApplicationContext context = new ClassPathXmlApplicationContext(
		"com/nvm/rock/creater/spring/application*.xml");
		DBHelperDao dao = (DBHelperDao)context.getBean("dbHelperDao");
		
		new JavaUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(tableName);
		new JspUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(tableName);
		
		DoBakFile.bakAll(config);
	}
}
