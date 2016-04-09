

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

public class CreaterTstAccount {
	
	
	public static void main(String[] args) throws Exception {
		Config config = new Config();
		String tableName   ="TST_ACCOUNT";
		Map createrInfos = new HashMap<String,LabelInfo>(){{
				this.put("username".toUpperCase(), 	new LabelInfo("姓名",  FormType._101TEXT   ,		SearchType._2STRING));
				this.put("account".toUpperCase(),  	new LabelInfo("帐号" , FormType._101TEXT   ,		SearchType._2STRING));
				this.put("password".toUpperCase(), 	new LabelInfo("密码",  FormType._102PASSWORD   ,	SearchType._0NO));
				this.put("sex".toUpperCase(), 		new LabelInfo("性别" , FormType._202RADIO ,		SearchType._1SELECT) );
				this.put("birthday".toUpperCase(), 	new LabelInfo("生日" , FormType._104DATE   ,		SearchType._3DATE));
				this.put("mylike".toUpperCase(), 	new LabelInfo("爱好" , FormType._203CHECKBOX   ,		SearchType._0NO));
				this.put("city".toUpperCase(), 		new LabelInfo("城市" , FormType._201SELECT,			SearchType._1SELECT));
				this.put("regdate".toUpperCase(), 	new LabelInfo("注册",	FormType._104DATE   ,		SearchType._3DATE));
				this.put("note".toUpperCase(), 		new LabelInfo("备注信息",	FormType._103TEXTAREA  ,	SearchType._0NO));
		}};
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
		"com/nvm/rock/creater/spring/application*.xml");
		DBHelperDao dao = (DBHelperDao)context.getBean("dbHelperDao");
	 	new JavaUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(tableName);
		new JspUtil().inits(createrInfos ,config, dao).setType(Type.jsp_data).executeTable(tableName);
		
		DoBakFile.bakAll(config);
	}
}
