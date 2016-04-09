

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

public class CreaterViewSysRoleRight {
	
	
	public static void main(String[] args) throws Exception {
		Config config = new Config();
		String tableName   ="VIEW_SYS_ROLERIGHT";
			Map createrInfos = new HashMap<String,LabelInfo>(){{
				this.put("ROLEID", 	new LabelInfo("角色", 	FormType._201SELECT , SearchType._1SELECT ));//type:varchar
				this.put("FNAME", 	new LabelInfo("一级模块", FormType._101TEXT , SearchType._2STRING ));//type:varchar
				this.put("SMANE", 	new LabelInfo("二级模块", FormType._101TEXT , SearchType._2STRING + SearchType._0NSEARCH));//type:varchar
				this.put("ORDERON", new LabelInfo("排序", 	FormType._101TEXT , SearchType._2STRING + SearchType._0NSEARCH));//type:int
				this.put("NALL", 	new LabelInfo("所有权限值", FormType._101TEXT , SearchType._2STRING + SearchType._0NSEARCH ));//type:varchar
				this.put("RALL", 	new LabelInfo("所有权限", FormType._101TEXT , SearchType._2STRING ));//type:varchar
				this.put("N1", new LabelInfo("读取权限", FormType._101TEXT , SearchType._2STRING ));//type:int
				this.put("R1", new LabelInfo("读取权限", FormType._101TEXT , SearchType._2STRING ));//type:varchar
				this.put("N2", new LabelInfo("添加权限", FormType._101TEXT , SearchType._2STRING ));//type:int
				this.put("R2", new LabelInfo("添加权限", FormType._101TEXT , SearchType._2STRING ));//type:varchar
				this.put("N3", new LabelInfo("更新权限", FormType._101TEXT , SearchType._2STRING ));//type:int
				this.put("R3", new LabelInfo("更新权限", FormType._101TEXT , SearchType._2STRING ));//type:varchar
				this.put("N4", new LabelInfo("删除权限", FormType._101TEXT , SearchType._2STRING ));//type:int
				this.put("R4", new LabelInfo("删除权限", FormType._101TEXT , SearchType._2STRING ));//type:varchar
				this.put("N5", new LabelInfo("扩展权限", FormType._101TEXT , SearchType._2STRING ));//type:int
				this.put("R5", new LabelInfo("扩展权限", FormType._101TEXT , SearchType._2STRING ));//type:varchar
				this.put("N6", new LabelInfo("扩展权限", FormType._101TEXT , SearchType._2STRING ));//type:int
				this.put("R6", new LabelInfo("扩展权限", FormType._101TEXT , SearchType._2STRING ));//type:varchar
				this.put("N7", new LabelInfo("扩展权限", FormType._101TEXT , SearchType._2STRING ));//type:int
				this.put("R7", new LabelInfo("扩展权限", FormType._101TEXT , SearchType._2STRING ));//type:varchar
				this.put("N8", new LabelInfo("扩展权限", FormType._101TEXT , SearchType._2STRING ));//type:int
				this.put("R8", new LabelInfo("扩展权限", FormType._101TEXT , SearchType._2STRING ));//type:varchar
			
			}};
			 
		ApplicationContext context = new ClassPathXmlApplicationContext(
		"com/nvm/rock/creater/spring/application*.xml");
		DBHelperDao dao = (DBHelperDao)context.getBean("dbHelperDao");
		
		new JavaUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(tableName);
		 new JspUtil().inits(createrInfos ,config, dao).setType(Type.jsp_data).executeTable(tableName);
		
		DoBakFile.bakAll(config);
	}
}
