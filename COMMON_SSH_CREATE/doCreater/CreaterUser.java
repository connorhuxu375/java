

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

public class CreaterUser {
	
	
	public static void main(String[] args) throws Exception {
		Config config = new Config();
		String tableName   ="NET_USER";
		Map createrInfos = new HashMap<String,LabelInfo>(){{
			this.put("account".toUpperCase(),  new LabelInfo("�ʺ�" ,	FormType._101TEXT   ,	SearchType._2STRING));
			this.put("password".toUpperCase(), new LabelInfo("����", 	FormType._102PASSWORD,SearchType._0NO));
			this.put("use_status".toUpperCase(), new LabelInfo("״̬", 	FormType._202RADIO,SearchType._1SELECT));
			this.put("province_name".toUpperCase(), new LabelInfo("ʡ��",FormType._201SELECT,SearchType._2STRING));
			this.put("city_name".toUpperCase(), new LabelInfo("����", 	FormType._201SELECT,SearchType._2STRING));
			this.put("usernumber".toUpperCase(), new LabelInfo("�ֻ���", FormType._101TEXT,SearchType._2STRING));
			this.put("email".toUpperCase(), new LabelInfo("����", 		FormType._101TEXT,SearchType._2STRING));
			this.put("note".toUpperCase(), new LabelInfo("������Ϣ", 		FormType._103TEXTAREA,SearchType._0NO));
		}};
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
		"com/nvm/rock/creater/spring/application*.xml");
		DBHelperDao dao = (DBHelperDao)context.getBean("dbHelperDao");
		
		new JavaUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(tableName);
		new JspUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(tableName);
	}
}
