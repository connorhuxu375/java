

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

public class CreaterSysRoleRight {
	
	
	public static void main(String[] args) throws Exception {
		Config config = new Config();
		String tableName   ="SYS_ROLERIGHT";
			Map createrInfos = new HashMap<String,LabelInfo>(){{
				this.put("ROLEID", new LabelInfo("��ɫ", FormType._201SELECT , SearchType._1SELECT ));//type:int
				this.put("MODULEID", new LabelInfo("ģ��", FormType._201SELECT , SearchType._1SELECT ));//type:int
				this.put("MODULERIGHT", new LabelInfo("Ȩ��", FormType._101TEXT , SearchType._2STRING+SearchType._0NSEARCH ));//type:varchar
			}};
			 
		ApplicationContext context = new ClassPathXmlApplicationContext(
		"com/nvm/rock/creater/spring/application*.xml");
		DBHelperDao dao = (DBHelperDao)context.getBean("dbHelperDao");
		
		new JavaUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(tableName);
		//new JspUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(tableName);
		
		DoBakFile.bakAll(config);
	}
}
