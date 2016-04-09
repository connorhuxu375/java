

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

public class CreaterSysModule {
	
	
	public static void main(String[] args) throws Exception {
		Config config = new Config();
		String tableName   ="SYS_MODULE";
		Map createrInfos = new HashMap<String,LabelInfo>(){{
			this.put("FMODULEID", 		new LabelInfo("父模块", 	FormType._201SELECT , 	SearchType._1SELECT ));//type:int
			this.put("MODULEDES", 		new LabelInfo("模块名称", FormType._101TEXT , 	SearchType._2STRING ));//type:varchar
			this.put("MODULELEVEL", 	new LabelInfo("模块级别", FormType._201SELECT , 	SearchType._1SELECT ));//type:int
			this.put("MODULEADDRESS", 	new LabelInfo("模块地址", FormType._101TEXT , 	SearchType._2STRING ));//type:varchar
			this.put("MODULEORDER",	 	new LabelInfo("模块排序", FormType._101TEXT , 	SearchType._2STRING ));//type:int
		}};
			 
		ApplicationContext context = new ClassPathXmlApplicationContext(
		"com/nvm/rock/creater/spring/application*.xml");
		DBHelperDao dao = (DBHelperDao)context.getBean("dbHelperDao");
		
		new JavaUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(tableName);
		new JspUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(tableName);
		
		DoBakFile.bakAll(config);
	}
}
