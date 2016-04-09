package com.arock.create.util;
public class LabelInfo {
	/**
	 * 列表与操作页面显示的中文字段
	 */
	public String label;
	/**
	 * 需生成的操作页面的表单类型  详见:FormType
	 */
	public int fromType;
	/**
	 * 需生成的查询类型 详见:SeachType
	 */
	public int searchType;
	public LabelInfo(String label,int fromType, int searchType) {
		this.label = label;
		this.fromType = fromType;
		this.searchType = searchType;
	}
}