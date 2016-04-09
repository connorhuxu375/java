package com.zhk.core.db;

import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.zhk.core.util.TableUtil;
@XmlAccessorType(XmlAccessType.FIELD) 
@XmlType(propOrder={
		"column",
		"label",
		"javaType",
		"must",
		"export",
		"field",
		"getMethod",
		"setMethod",
		"G"
})
public class Colunm {
	@XmlElement(name = "generation" )  
	private Generation G=new Generation();
	
	@XmlAttribute(name = "text" )  
	private String text = "text";
	@XmlAttribute(name = "value" )  
	private String value ="value";
	@XmlElement(name = "field" )  
	private String field;
	@XmlElement(name = "setMethod" )  
	private String setMethod;
	@XmlElement(name = "getMethod" )  
	private String getMethod;
	
	
	
	@XmlAttribute(name = "example" )  
	private String example ="";
	
	@XmlAttribute(name = "max" )  
	private int max = 500; 
	@XmlAttribute(name = "min" )  
	private int min  =0;
	@XmlElement(name = "must" )  
	private int must =1 ;
	@XmlAttribute(name = "header" )  
	private int header = 1;

	
	
	@XmlElement(name = "export" )  
	private int export =1;
	@XmlAttribute(name = "format" )  
	private String format = "text";
	@XmlAttribute(name = "checkType" )  
	private String checkType ="text";
	@XmlElement(name = "javaType" )  
	private String javaType;
	@XmlElement(name = "column" )  
	private String column;
	@XmlElement(name = "label" )  
	private String label;
	
	public void parseColumnName(){
		this.field = TableUtil.columnNameToFieldName( column);
		this.getMethod = TableUtil.columnNameToGetMethodName(column);
		this.setMethod =  TableUtil.columnNameToSetMethodName(column);
	}
	
	public int getHeader() {
		return header;
	}
	public void setHeader(int header) {
		this.header = header;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMust() {
		return must;
	}
	public void setMust(int must) {
		this.must = must;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getExport() {
		return export;
	}
	public void setExport(int export) {
		this.export = export;
	}
	 
	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
		parseColumnName();
	}
	public String getJavaType() {
		return javaType;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getSetMethod() {
		return setMethod;
	}
	public void setSetMethod(String setMethod) {
		this.setMethod = setMethod;
	}
	public String getGetMethod() {
		return getMethod;
	}
	public void setGetMethod(String getMethod) {
		this.getMethod = getMethod;
	}
	public String getExample() {
		return example;
	}
	public void setExample(String example) {
		this.example = example;
	}
	
	

	public Generation getG() {
		return G;
	}
	public void setG(Generation g) {
		G = g;
	}
	@XmlAccessorType(XmlAccessType.FIELD) 
	public static class Generation{
		/**
		 * 生成POJO字段
		 */
		@XmlAttribute(name = "pojo" )  
		private boolean PojoField = true;
		/**
		 * 生成JSP详情字段
		 */
		@XmlAttribute(name = "detail" )
		private boolean FormDetail = true;
		/**
		 * 生成JSP添加字段
		 */
		@XmlAttribute(name = "input" )
		private boolean FormInput = true ; 
		/**
		 * 生成JSP列表字段
		 */
		@XmlAttribute(name = "list" )
		private boolean ListField = true ;
		/**
		 * 生成JSP/DAO排序字段
		 */
		@XmlAttribute(name = "sort" )
		private boolean SortField = true ;
		/**
		 * 生成JSP/DAO查询字段
		 */
		@XmlAttribute(name = "search" )
		private boolean SearchField = true ;
		public boolean isPojoField() {
			return PojoField;
		}
		public void setPojoField(boolean pojoField) {
			PojoField = pojoField;
		}
		public boolean isFormDetail() {
			return FormDetail;
		}
		public void setFormDetail(boolean formDetail) {
			FormDetail = formDetail;
		}
		public boolean isFormInput() {
			return FormInput;
		}
		public void setFormInput(boolean formInput) {
			FormInput = formInput;
		}
		public boolean isListField() {
			return ListField;
		}
		public void setListField(boolean listField) {
			ListField = listField;
		}
		public boolean isSortField() {
			return SortField;
		}
		public void setSortField(boolean sortField) {
			SortField = sortField;
		}
		public boolean isSearchField() {
			return SearchField;
		}
		public void setSearchField(boolean searchField) {
			SearchField = searchField;
		}
	}
	public void check() {
		if(this.column == null || this.column.equals("")){
			throw new RuntimeException("column 不能为空");
		}
		if(this.checkType == null || this.checkType.equals("")){
			throw new RuntimeException("checkType 不能为空");
		}
		if(this.format == null || this.format.equals("")){
			throw new RuntimeException("format 不能为空");
		}
		if(this.label == null || this.label.equals("")){
			throw new RuntimeException("label 不能为空");
		}
		if(this.javaType == null || this.javaType.equals("")){
			throw new RuntimeException("javaType 不能为空");
		}
	}
}
