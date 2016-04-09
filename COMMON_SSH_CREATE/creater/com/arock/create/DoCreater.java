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
 * 1:���ú����ݿ���ز���.����ʹ��databaseName  tableName ��������
 * 		I  :��һ������������ applicationContext-common.xml ���Ӳ���
 * 	 	II :������ʽ:TST_ACCOUNT 
 * 		   ��һ���»�ǰǰ��Ϊģ����.Ĭ��Ϊ�Ӱ�(JAVA)�����ļ���(JSP) ,����������ָ�ʽ.Ĭ�ϰ���Ϊ:cmn
 *		III:(��ֻ֧������ int seqid identity)
 * 2:����Ҫ���ɵı��ֶγ�ʹ���� createrInfos ����
 * 3:ʹ��JUTIL TEST ִ����Ӧ����.�ѷ�״��ط��� �����ļ��ڶ�Ӧ generation �ļ���.
 * 4:��action���е� Model VO�����õ� hibernate.cfg.xml  ��:<mapping class="com.zhk.core.tst.action.TstAccount" />
 * 5:ˢ����Ŀ.����ɹ�����ֱ�ӷ�����ӦINDEX.JSP.����.���������ϸ�����󼴿�.
 * 
 * 6:�����账��:����
 * @author	: zhk@zhk.com
 * @version : 2014-8-21 ����11:35:56
 */
public class DoCreater {
	/*
	String tableName   ="NET_USER";
	Map createrInfos = new HashMap<String,LabelInfo>(){{
		this.put("account".toUpperCase(),  new LabelInfo("�ʺ�" ,FormType._101TEXT   ,	SearchType._2STRING));
		this.put("password".toUpperCase(), new LabelInfo("����", FormType._102PASSWORD,SearchType._0NO));
	}};
	
	String tableName   ="NET_DEVICE_IPV4_RECORD";
	Map createrInfos = new HashMap<String,LabelInfo>(){{
		this.put("device_id".toUpperCase(),  new LabelInfo("�豸" ,FormType._101TEXT   ,	SearchType._2STRING));
		this.put("device_ip".toUpperCase(), new LabelInfo("��ַ", FormType._101TEXT,		SearchType._2STRING));
		
		this.put("device_network_type".toUpperCase(), new LabelInfo("�û�����", FormType._201SELECT,SearchType._1SELECT));
		this.put("device_src_port".toUpperCase(), new LabelInfo("�˿�", FormType._101TEXT,SearchType._2STRING));
		
		this.put("over_type".toUpperCase(), new LabelInfo("ʵ������", FormType._201SELECT,SearchType._1SELECT));
		
		this.put("record_time".toUpperCase(), new LabelInfo("��¼ʱ��", FormType._104DATE,SearchType._3DATE));
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
		this.put("record_seqid".toUpperCase(),  new LabelInfo("��¼ID" ,FormType._201SELECT   ,	SearchType._1SELECT));
		this.put("device_id".toUpperCase(),  new LabelInfo("�豸" ,FormType._101TEXT   ,	SearchType._2STRING));
		this.put("ip_address".toUpperCase(), new LabelInfo("��ַ", FormType._101TEXT,		SearchType._2STRING));
		this.put("network_name".toUpperCase(), new LabelInfo("����", FormType._101TEXT,SearchType._2STRING));
		this.put("ip_range".toUpperCase(), new LabelInfo("����", FormType._101TEXT,SearchType._2STRING));
		this.put("ip_status".toUpperCase(), new LabelInfo("״̬", FormType._101TEXT,SearchType._2STRING));
		this.put("company_desc".toUpperCase(), new LabelInfo("��˾����", FormType._101TEXT,SearchType._2STRING));
		this.put("company_name".toUpperCase(), new LabelInfo("��˾����", FormType._101TEXT,SearchType._2STRING));
		this.put("company_add".toUpperCase(), new LabelInfo("��˾��ַ", FormType._101TEXT,SearchType._2STRING));
		this.put("company_phone".toUpperCase(), new LabelInfo("��˾�绰", FormType._101TEXT,SearchType._2STRING));
		this.put("data_resource".toUpperCase(), new LabelInfo("������Դ", FormType._101TEXT,SearchType._2STRING));
		this.put("search_time".toUpperCase(), new LabelInfo("�˶�ʱ��", FormType._104DATE,SearchType._3DATE));
	}};
	*/
	Config config = new Config();
	String tableName   ="NET_DEVICE_IPV4_REPORT";
	Map createrInfos = new HashMap<String,LabelInfo>(){{
		this.put("search_seqid".toUpperCase(),  new LabelInfo("��¼ID" ,FormType._201SELECT   ,	SearchType._1SELECT));
		this.put("record_seqid".toUpperCase(),  new LabelInfo("��¼ID" ,FormType._201SELECT   ,	SearchType._1SELECT));
		this.put("device_id".toUpperCase(), new LabelInfo("�豸ID", FormType._101TEXT,		SearchType._2STRING));
		this.put("device_network_type".toUpperCase(), new LabelInfo("�豸����", FormType._201SELECT,SearchType._1SELECT));
		this.put("device_ip".toUpperCase(), new LabelInfo("�豸IP", FormType._101TEXT,SearchType._2STRING));
		this.put("yyyymmddhh".toUpperCase(), new LabelInfo("���ʱ��", FormType._101TEXT,SearchType._2STRING));
		this.put("check_time".toUpperCase(), new LabelInfo("����ʱ��", FormType._104DATE,SearchType._3DATE));
		this.put("check_result".toUpperCase(), new LabelInfo("�����", FormType._201SELECT,SearchType._1SELECT));
		this.put("note".toUpperCase(), new LabelInfo("��ע��Ϣ", FormType._101TEXT,SearchType._2STRING));
	}};
	

	DBHelperDao dao;
	@Before
	public  void zinitField(){
		System.out.println("===============���ɿ�ʼ===============");
		String tableName   ="NET_USER";
		
			//this.createrInfos = createrInfos;
			ApplicationContext context = new ClassPathXmlApplicationContext(
			"com/nvm/rock/zpring/application*.xml");
			this.dao = (DBHelperDao)context.getBean("dbHelperDao");

	}
	/**
	 * 
	 * �����������ṩ����Ϣ.���������ļ�(���ɺ�����hibernate.cfg.xml �����MODEL���ӳ��)
	 * @author : zhk@zhk.com
	 * @version: 2014-8-20 ����11:48:33
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
	 * ��������������JAVA��˵���(MODEL ACTION SERVICE DA0 SPRING XML)
	 * ���ɺ�����hibernate.cfg.xml �����MODEL���ӳ��
	 * 
	 * @author : zhk@zhk.com
	 * @version: 2014-8-20 ����11:49:33
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
	 * ����������JSP������ҳ��.����INDEX.JSP����. ���赥������INPUT �� DATA ��Ҫ �������ļ����� <%@include
	 * file="/import/include/resource.jsp" %>
	 * 
	 * @author : zhk@zhk.com
	 * @version: 2014-8-20 ����11:50:44
	 * @return :
	 */
	@Test
	public void createFileJspAll() throws Exception {

		new JspUtil().inits(createrInfos ,config, dao).setType(Type.all).executeTable(
				 tableName);

	}

	/**
	 * ���赥������INPUT��Ҫ ����ļ����� <%@include file="/import/include/resource.jsp" %>
	 * 
	 * @author : zhk@zhk.com
	 * @version: 2014-8-20 ����11:52:06
	 * @return :
	 */
	@Test
	public void createFileJspInputFile() throws Exception {

		new JspUtil().inits(createrInfos ,config, dao).setType(Type.jsp_input)
				.executeTable( tableName);

	}

	/**
	 * ���赥������INPUT��Ҫ ����ļ����� <%@include file="/import/include/resource.jsp" %>
	 * ������
	 * 
	 * @author : zhk@zhk.com
	 * @version: 2014-8-20 ����11:52:28
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
		System.out.println("===============�������===============");
		DoBakFile.bakAll();
	}
}
