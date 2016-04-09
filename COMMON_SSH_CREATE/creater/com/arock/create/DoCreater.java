package com.arock.create;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.arock.create.util.DBHelperDao;
import com.arock.create.util.FormType;
import com.arock.create.util.JavaUtil;
import com.arock.create.util.JspUtil;
import com.arock.create.util.LabelInfo;
import com.arock.create.util.SearchType;
import com.arock.create.util.Type;
/**
 * 1:设置好数据库相关参数.并初使化databaseName  tableName 两个参数
 * 		I  :第一次生成需配置 applicationContext-common.xml 连接参数
 * 	 	II :表名格式:TST_ACCOUNT 
 * 		   第一个下划前前的为模块命.默认为子包(JAVA)与子文件夹(JSP) ,如果不是这种格式.默认包名为:cmn
 *		III:(暂只支持主键 int seqid identity)
 * 2:依据要生成的表字段初使化好 createrInfos 参数
 * 3:使用JUTIL TEST 执行相应方法.已封状相关方法 生成文件在对应 generation 文件夹.
 * 4:把action包中的 Model VO类设置到 hibernate.cfg.xml  如:<mapping class="com.zhk.core.tst.action.TstAccount" />
 * 5:刷新项目.编译成功即可直接访问相应INDEX.JSP.测试.并调整相关细节需求即可.
 * 
 * 6:其它需处理:排序
 * @author	: zhk@zhk.com
 * @version : 2014-8-21 下午11:35:56
 */
public class DoCreater {
	/*
	String tableName   ="NET_USER";
	Map createrInfos = new HashMap<String,LabelInfo>(){{
		this.put("account".toUpperCase(),  new LabelInfo("帐号" ,FormType._101TEXT   ,	SearchType._2STRING));
		this.put("password".toUpperCase(), new LabelInfo("密码", FormType._102PASSWORD,SearchType._0NO));
	}};
	
	String tableName   ="NET_DEVICE_IPV4_RECORD";
	Map createrInfos = new HashMap<String,LabelInfo>(){{
		this.put("device_id".toUpperCase(),  new LabelInfo("设备" ,FormType._101TEXT   ,	SearchType._2STRING));
		this.put("device_ip".toUpperCase(), new LabelInfo("地址", FormType._101TEXT,		SearchType._2STRING));
		
		this.put("device_network_type".toUpperCase(), new LabelInfo("用户网络", FormType._201SELECT,SearchType._1SELECT));
		this.put("device_src_port".toUpperCase(), new LabelInfo("端口", FormType._101TEXT,SearchType._2STRING));
		
		this.put("over_type".toUpperCase(), new LabelInfo("实际网络", FormType._201SELECT,SearchType._1SELECT));
		
		this.put("record_time".toUpperCase(), new LabelInfo("记录时间", FormType._104DATE,SearchType._3DATE));
	}};
	*/
	/*
DROP VIEW NET_VIEW_DEVICE_IPV4_CHECK
CREATE VIEW NET_VIEW_DEVICE_IPV4_CHECK
AS 
SELECT * FROM (
SELECT A.*,B.device_id FROM NET_DEVICE_IPV4_CHECK  AS  A
LEFT JOIN NET_DEVICE_IPV4_RECORD AS B ON B.seqid =A.record_seqid
) AS NET_VIEW_DEVICE_IPV4_CHECK
*/
	/*String tableName   ="NET_VIEW_DEVICE_IPV4_CHECK";
	Map createrInfos = new HashMap<String,LabelInfo>(){{
		this.put("record_seqid".toUpperCase(),  new LabelInfo("记录ID" ,FormType._201SELECT   ,	SearchType._1SELECT));
		this.put("device_id".toUpperCase(),  new LabelInfo("设备" ,FormType._101TEXT   ,	SearchType._2STRING));
		this.put("ip_address".toUpperCase(), new LabelInfo("地址", FormType._101TEXT,		SearchType._2STRING));
		this.put("network_name".toUpperCase(), new LabelInfo("网络", FormType._101TEXT,SearchType._2STRING));
		this.put("ip_range".toUpperCase(), new LabelInfo("区间", FormType._101TEXT,SearchType._2STRING));
		this.put("ip_status".toUpperCase(), new LabelInfo("状态", FormType._101TEXT,SearchType._2STRING));
		this.put("company_desc".toUpperCase(), new LabelInfo("公司描述", FormType._101TEXT,SearchType._2STRING));
		this.put("company_name".toUpperCase(), new LabelInfo("公司名称", FormType._101TEXT,SearchType._2STRING));
		this.put("company_add".toUpperCase(), new LabelInfo("公司地址", FormType._101TEXT,SearchType._2STRING));
		this.put("company_phone".toUpperCase(), new LabelInfo("公司电话", FormType._101TEXT,SearchType._2STRING));
		this.put("data_resource".toUpperCase(), new LabelInfo("数据来源", FormType._101TEXT,SearchType._2STRING));
		this.put("search_time".toUpperCase(), new LabelInfo("核对时间", FormType._104DATE,SearchType._3DATE));
	}};
	*/
	Config config = new Config();
	String tableName   ="NET_DEVICE_IPV4_REPORT";
	Map createrInfos = new HashMap<String,LabelInfo>(){{
		this.put("search_seqid".toUpperCase(),  new LabelInfo("记录ID" ,FormType._201SELECT   ,	SearchType._1SELECT));
		this.put("record_seqid".toUpperCase(),  new LabelInfo("记录ID" ,FormType._201SELECT   ,	SearchType._1SELECT));
		this.put("device_id".toUpperCase(), new LabelInfo("设备ID", FormType._101TEXT,		SearchType._2STRING));
		this.put("device_network_type".toUpperCase(), new LabelInfo("设备网络", FormType._201SELECT,SearchType._1SELECT));
		this.put("device_ip".toUpperCase(), new LabelInfo("设备IP", FormType._101TEXT,SearchType._2STRING));
		this.put("yyyymmddhh".toUpperCase(), new LabelInfo("检查时段", FormType._101TEXT,SearchType._2STRING));
		this.put("check_time".toUpperCase(), new LabelInfo("操作时间", FormType._104DATE,SearchType._3DATE));
		this.put("check_result".toUpperCase(), new LabelInfo("检查结果", FormType._201SELECT,SearchType._1SELECT));
		this.put("note".toUpperCase(), new LabelInfo("备注信息", FormType._101TEXT,SearchType._2STRING));
	}};
	

	DBHelperDao dao;
	@Before
	public  void zinitField(){
		System.out.println("===============生成开始===============");
		String tableName   ="NET_USER";
		
			//this.createrInfos = createrInfos;
			ApplicationContext context = new ClassPathXmlApplicationContext(
			"com/nvm/rock/zpring/application*.xml");
			this.dao = (DBHelperDao)context.getBean("dbHelperDao");

	}
	/**
	 * 
	 * 描述：依据提供的信息.生成所有文件(生成后需在hibernate.cfg.xml 里添加MODEL类的映射)
	 * @author : zhk@zhk.com
	 * @version: 2014-8-20 上午11:48:33
	 * @return :
	 */
	@Test
	public void createFileAll() throws Exception {
		new JavaUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(
				 tableName );
		new JspUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable( tableName);
	}

	/**
	 * 
	 * 描述：生成所有JAVA后端的类(MODEL ACTION SERVICE DA0 SPRING XML)
	 * 生成后需在hibernate.cfg.xml 里添加MODEL类的映射
	 * 
	 * @author : zhk@zhk.com
	 * @version: 2014-8-20 上午11:49:33
	 * @return :
	 */
	@Test
	public void createFileJava() throws Exception {

		new JavaUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(
				 tableName);

	}

	@Test
	public void createFileJavaModel() throws Exception {

		new JavaUtil().inits(createrInfos ,config, dao).setType(Type.mdlFile).executeTable(
				 tableName);

	}

	@Test
	public void createFileJavaAction() throws Exception {

		new JavaUtil().inits(createrInfos ,config, dao).setType(Type.actFile).executeTable(
				 tableName);

	}

	@Test
	public void createFileJavaDao() throws Exception {

		new JavaUtil().inits(createrInfos ,config, dao).setType(Type.daoFile).executeTable(
				 tableName);

	}

	@Test
	public void createFileJavaService() throws Exception {

		new JavaUtil().inits(createrInfos ,config, dao).setType(Type.serFile).executeTable(
				 tableName);

	}

	@Test
	public void createFileJavaUtilTest() throws Exception {

		new JavaUtil().inits(createrInfos ,config, dao).setType(Type.tstFile).executeTable(
				 tableName);

	}

	@Test
	public void createFileXml() throws Exception {

		new JavaUtil().inits(createrInfos ,config, dao).setType(Type.xmlFile).executeTable(
				 tableName);

	}

	/**
	 * 
	 * 描述：生成JSP端所有页面.访问INDEX.JSP即可. 如需单独调试INPUT 与 DATA 需要 这两个文件加入 <%@include
	 * file="/import/include/resource.jsp" %>
	 * 
	 * @author : zhk@zhk.com
	 * @version: 2014-8-20 上午11:50:44
	 * @return :
	 */
	@Test
	public void createFileJspAll() throws Exception {

		new JspUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(
				 tableName);

	}

	/**
	 * 如需单独调试INPUT需要 这个文件加入 <%@include file="/import/include/resource.jsp" %>
	 * 
	 * @author : zhk@zhk.com
	 * @version: 2014-8-20 上午11:52:06
	 * @return :
	 */
	@Test
	public void createFileJspInputFile() throws Exception {

		new JspUtil().inits(createrInfos ,config, dao).setType(Type.jsp_input)
				.executeTable( tableName);

	}

	/**
	 * 如需单独调试INPUT需要 这个文件加入 <%@include file="/import/include/resource.jsp" %>
	 * 描述：
	 * 
	 * @author : zhk@zhk.com
	 * @version: 2014-8-20 上午11:52:28
	 * @return :
	 */
	@Test
	public void createFileJspListFile() throws Exception {

		new JspUtil().inits(createrInfos ,config, dao).setType(Type.jsp_data).executeTable(
				 tableName);

	}

	@Test
	public void createFileJspIndexFile() throws Exception {

		new JspUtil().inits(createrInfos ,config, dao).setType(Type.jsp_index)
				.executeTable( tableName);

	}

	@Test
	public void createFileJspMenuFile() throws Exception {

		new JspUtil().inits(createrInfos ,config, dao).setType(Type.jsp_menu).executeTable(
				 tableName);

	}

	@Test
	public void createFileJspTabsFile() throws Exception {

		new JspUtil().inits(createrInfos ,config, dao).setType(Type.jsp_tabs).executeTable(
				 tableName);

	}

	@After
	public void createOk() throws Exception {
		System.out.println("===============生成完成===============");
		DoBakFile.bakAll();
	}
}
