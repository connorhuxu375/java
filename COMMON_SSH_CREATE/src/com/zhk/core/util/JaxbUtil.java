package com.zhk.core.util;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;
import com.zhk.core.db.Colunm;
import com.zhk.core.db.Table;
/**
 * 描述 
 * @author	: zhk@zhk.com
 * @version : 2014-11-12 下午03:42:35
 */
public class JaxbUtil {  
  
    /** 
     * JavaBean转换成xml 
     * 默认编码UTF-8 
     * @param obj 
     * @param writer 
     * @return  
     */  
    public static String vo2xml(Object obj) {  
        return vo2xml(obj, "GBK");  
    }  
  
    /** 
     * JavaBean转换成xml 
     * @param obj 
     * @param encoding  
     * @return  
     */  
 static String vo2xml(Object obj, String encoding) {  
        String result = null;  
        try {  
            JAXBContext context = JAXBContext.newInstance(obj.getClass());  
            Marshaller marshaller = context.createMarshaller();  
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
            
            marshaller.setProperty("com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler",
                    new CharacterEscapeHandler () {
                public void escape(char[] ch, int start,int length, boolean isAttVal,
                        Writer writer) throws IOException {
                    writer.write(ch, start, length);
                }
            });
            
            
            StringWriter writer = new StringWriter();  
            marshaller.marshal(obj, writer);  
            result = writer.toString();  
        } catch (Exception e) {  
        	 throw new RuntimeException(e);
        }  
  
        return result;  
    }  
     
  
    /** 
     * xml转换成JavaBean 
     * @param xml 
     * @param c 
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    public static <T> T xml2vo(String xml, Class<T> c) {  
        T t = null;  
        try {  
            JAXBContext context = JAXBContext.newInstance(c);  
            Unmarshaller unmarshaller = context.createUnmarshaller();  
            t = (T) unmarshaller.unmarshal(new StringReader(xml));  
        } catch (Exception e) {  
            throw new RuntimeException(e);
        }  
  
        return t;  
    }  
    
    
    
    public static void main(String[] args) {
    	
    	Table t = new Table();
		t.setClassName("SysUser");
		t.setPackageName("com.zhk.core.sys");
		t.setObjectName("sysUser");
		t.setTableName("SYS_USER");
		t.setPrefix("sys");
		
		Colunm c1 = new Colunm();
		c1.setCheckType("text");
		c1.setColumn("ACCOUNT");
		c1.setField("account");
		c1.setFormat("text");
		c1.setGetMethod("getAccount");
		c1.setSetMethod("setAccount");
		c1.setJavaType("String");
		c1.setLabel("用户帐号");
		c1.setMax(50);
		c1.setMin(2);
		c1.setExport(1);
		c1.setMust(1);
		t.getColunms().add(c1);
		
		Colunm c2 = new Colunm();
		c2.setCheckType("password");
		c2.setColumn("PASSWORD");
		c2.setField("password");
		c2.setFormat("password");
		c2.setGetMethod("getPassword");
		c2.setSetMethod("setPassword");
		c2.setJavaType("String");
		c2.setLabel("帐号密码");
		c2.setMax(50);
		c2.setMin(2);
		c2.setExport(1);
		c2.setMust(1);
		t.getColunms().add(c2);
		
		
		Colunm c3 = new Colunm();
		c3.setCheckType("select");
		c3.setColumn("STATUS");
		c3.setField("status");
		c3.setFormat("select");
		c3.setGetMethod("getStatus");
		c3.setSetMethod("setStatus");
		c3.setJavaType("Integer");
		c3.setLabel("用户状态");
		//c3.getG().setPojoField(false);
		//c3.getG().setSearchField(false);
		c3.setExport(1);
		c3.setMust(1);
		t.getColunms().add(c3);
		
		
		Colunm c4 = new Colunm();
		c4.setCheckType("text");
		c4.setColumn("REG_DATE");
	
		c4.setFormat("datetime");
		c4.setField("regDate");
		
		c4.setGetMethod("getRegDate");
		c4.setSetMethod("setRegDate");
		c4.setJavaType("Date");
		c4.setLabel("注册时间");
		c4.setExport(1);
		c4.setMust(1);
		t.getColunms().add(c4);
		
		
		for (Iterator iterator = t.getColunms().iterator(); iterator.hasNext();) {
			Colunm c = (Colunm) iterator.next();
			c.parseColumnName();
		}
		
		String xml = JaxbUtil.vo2xml(t);
		  
		  
		  
		  System.out.println(xml);
           /* String str = 
            	"" +  
                "<ProvBOSS>" +  
                "    <OrigDomain>aaa</OrigDomain>" +  
                "    <Response>" +
                "    	<RspType></RspType>" +  
                "	 </Response>" +  
                "</ProvBOSS>";  
              
            XmlBase book = JaxbUtil.xml2vo(str, XmlBase.class);  
            System.out.println(book);  
            
            book.getResponse().setRspDesc("OrigDomain  ");
            System.out.println(book);
            
            XmlResponse rsp = new XmlResponse();
            rsp.setMessage("test");
            rsp.setStatus(200);
            
            
            book.setSvcCont(JaxbUtil.vo2xml(rsp));
            
            String xml = JaxbUtil.vo2xml(book);
            System.out.println(xml); 
            
            XmlBase book2  = new XmlBase();
            book2.getResponse().setRspCode("aaaaaaa");
            System.out.println(book2);*/
	}
    
    public static class CDataAdapter extends XmlAdapter<String, String> {
        @Override
        public String marshal(String str) throws Exception {
            return "<![CDATA[" + str+ "]]>";
        }
        @Override
        public String unmarshal(String str) throws Exception {
            return str;
        }
    }
    public static class SvcContXmlAdapter extends XmlAdapter<String, String> {
        @Override
        public String marshal(String str) throws Exception {
        	str = str.replaceFirst("^<.*?\\?>", "");
            return "<![CDATA[" + str+ "]]>";
        }
        
        @Override
        public String unmarshal(String str) throws Exception {
            return str;
        }
    }
}   