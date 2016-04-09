

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

public class CreaterSysRole {
	
	
	public static void main(String[] args) throws Exception {
		Config config = new Config();
		String tableName   ="SYS_ROLE";
			Map createrInfos = new HashMap<String,LabelInfo>(){{
				this.put("ROLESTATE", new LabelInfo("角色状态", FormType._201SELECT , SearchType._1SELECT ));//type:int
				this.put("ROLETYPE", new LabelInfo("角色类型", FormType._201SELECT , SearchType._1SELECT ));//type:int
				this.put("ROLENAME", new LabelInfo("角色名称", FormType._101TEXT , SearchType._2STRING ));//type:varchar
			}};
			 
		ApplicationContext context = new ClassPathXmlApplicationContext(
		"com/nvm/rock/creater/spring/application*.xml");
		DBHelperDao dao = (DBHelperDao)context.getBean("dbHelperDao");
		
		new JavaUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(tableName);
		new JspUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(tableName);
		
		DoBakFile.bakAll(config);
	}
}
