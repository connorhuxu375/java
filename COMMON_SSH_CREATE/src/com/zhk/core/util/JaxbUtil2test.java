package com.zhk.core.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;

public class JaxbUtil2test {
	public static String vo2xml(Object obj){
		return vo2xml(obj,"GBK");
	}
	public static String vo2xml(Object obj,String encoding){
		String result=null;
		try {
			JAXBContext context=JAXBContext.newInstance(obj.getClass());
		    Marshaller marshaller=context.createMarshaller();
		    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
		    marshaller.setProperty("com.sun.xml.internal.bin.marshaller.CharacterEscapeHandler", new CharacterEscapeHandler(){

				@Override
				public void escape(char[] ch, int start, int length,
						boolean isAttVal, Writer writer) throws IOException {
                 writer.write(ch,start,length);
					
				}
		    	
		    	
		    });
		    StringWriter strwriter=new StringWriter();
		    marshaller.marshal(obj, strwriter);
		    result=strwriter.toString();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
	}

}
