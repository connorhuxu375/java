

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

public class CreaterReport {
	/**
	 * 	A.seqid,
		R.device_id,R.device_ip,R.device_network_type,CY.province_name,  CY.city_name,
		c.company_add , c.company_desc,c.company_name,
		A.yyyymmddhh,A.check_time,A.check_result,A.note,
		a.judge_province_name,a.judge_net_type,a.judge_worker,a.judge_time
	 * 描述：  
	 * @author : zhk@zhk.com
	 * @version: 2014-8-29 上午09:12:10  
	 * @return :
	 */
	
	public static void main(String[] args) throws Exception {
		
		Config config = new Config();
		String tableName   ="VIEWNET_DEVICE_IPV4_REPORT";
		Map createrInfos = new HashMap<String,LabelInfo>(){{
			this.put("device_id".toUpperCase(),  new LabelInfo("设备标识" ,	FormType._101TEXT   ,	SearchType._2STRING));
			this.put("device_network_type".toUpperCase(), new LabelInfo("设备网络", 	FormType._201SELECT,  SearchType._1SELECT));
			this.put("province_name".toUpperCase(), new LabelInfo("设备省份",	FormType._201SELECT,  SearchType._1SELECT));
			this.put("city_name".toUpperCase(), new LabelInfo("设备城市", 	FormType._101TEXT,		SearchType._2STRING));
			
			this.put("yyyymmddhh".toUpperCase(), new LabelInfo("监测时段", 	FormType._101TEXT,SearchType._2STRING));
			this.put("device_ip".toUpperCase(), new LabelInfo("设备地址", 	FormType._101TEXT,		SearchType._2STRING));
			
			
			this.put("company_add".toUpperCase(), new LabelInfo("出口地址", 	FormType._101TEXT,SearchType._2STRING));
			this.put("company_desc".toUpperCase(), new LabelInfo("出口描述", 	FormType._101TEXT,SearchType._2STRING));
			this.put("company_name".toUpperCase(), new LabelInfo("出口名称", 	FormType._101TEXT,SearchType._2STRING));
			
			
			
			this.put("check_time".toUpperCase(), new LabelInfo("监测时间", 	FormType._104DATE,SearchType._3DATE));
			this.put("check_net_type".toUpperCase(), new LabelInfo("监测网络类型", 	FormType._201SELECT,		SearchType._1SELECT));
			this.put("check_result".toUpperCase(), new LabelInfo("监测结论", 	FormType._201SELECT,SearchType._1SELECT));
			this.put("check_note".toUpperCase(), new LabelInfo("监测说明", 			FormType._101TEXT,SearchType._2STRING));
			
			this.put("judge_province_name".toUpperCase(), new LabelInfo("判定出口省份",	FormType._201SELECT,  SearchType._1SELECT));
			this.put("judge_net_type".toUpperCase(), new LabelInfo("判定网络类型", 	FormType._201SELECT,		SearchType._1SELECT));
			this.put("judge_worker".toUpperCase(), new LabelInfo("判定人员", 	FormType._101TEXT,		SearchType._2STRING));
			this.put("judge_time".toUpperCase(), new LabelInfo("判定时间", 	FormType._104DATE,		SearchType._3DATE));
			this.put("judge_note".toUpperCase(), new LabelInfo("判定说明", 	FormType._101TEXT,		SearchType._2STRING));
		}};
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
		"com/nvm/rock/creater/spring/application*.xml");
		DBHelperDao dao = (DBHelperDao)context.getBean("dbHelperDao");
		
		//new JavaUtil().inits(createrInfos , dao).setType(Type.mdlFile).executeTable(tableName);
		new JspUtil().inits(createrInfos ,config, dao).setType(Type.jsp_data).executeTable(tableName);
		
	}
}
