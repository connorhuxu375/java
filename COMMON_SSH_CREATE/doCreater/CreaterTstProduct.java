

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

public class CreaterTstProduct {
	
	
	public static void main(String[] args) throws Exception {
		String tableName   ="TST_PRODUCT";
		Config config = new Config();
		Map createrInfos = new HashMap<String,LabelInfo>(){{
			//this.put("SEQID", new LabelInfo("SEQID", FormType._101TEXT , SearchType._2STRING ));//type:int
			this.put("PRODUCT_CODE", new LabelInfo("产品代码", 	FormType._101TEXT , 				SearchType._2STRING ));//type:varchar
			this.put("PRODUCT_NAME", new LabelInfo("产品名称", 	FormType._101TEXT , 				SearchType._2STRING ));//type:varchar
			this.put("PRODUCT_TYPE", new LabelInfo("产品类型", 	FormType._201SELECT , 				SearchType._1SELECT ));//type:int
			this.put("PRODUCT_LEVEL", new LabelInfo("产品级别",FormType._101TEXT , 				SearchType._1SELECT+SearchType._0NSEARCH ));//type:int
			this.put("PRICE", new LabelInfo("价格", 				FormType._101TEXT , 				SearchType._2STRING+SearchType._0NSEARCH ));//type:numeric
			this.put("STATUS", new LabelInfo("状态", 				FormType._201SELECT , 				SearchType._1SELECT ));//type:int
			this.put("SPNUMBER", new LabelInfo("SP 号", 			FormType._101TEXT , 				SearchType._2STRING ));//type:varchar
			this.put("SERVICE_CODE", new LabelInfo("业务代码", 	FormType._101TEXT , 				SearchType._2STRING ));//type:varchar
			this.put("VAC_PRODUCT", new LabelInfo("是否VAC产品", 	FormType._202RADIO , 				SearchType._1SELECT ));//type:int
			this.put("CREATE_TIME", new LabelInfo("添加时间",	FormType._104DATE+FormType._0NFORM,	SearchType._3DATE ));//type:datetime
			this.put("NOTE", new LabelInfo("备注", 					FormType._101TEXT , 				SearchType._2STRING ));//type:varchar
		}};
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
		"com/nvm/rock/creater/spring/application*.xml");
		DBHelperDao dao = (DBHelperDao)context.getBean("dbHelperDao");
		//new JavaUtil().inits(createrInfos,config , dao).setType(Type.all).executeTable(tableName);
		new JspUtil().inits(createrInfos ,config , dao).setType(Type.jsp_input).executeTable(tableName);
		DoBakFile.bakAll(config);
		
	}
}
