package com.arock.create.util;
public class LabelInfo {
	/**
	 * �б������ҳ����ʾ�������ֶ�
	 */
	public String label;
	/**
	 * �����ɵĲ���ҳ��ı�����  ���:FormType
	 */
	public int fromType;
	/**
	 * �����ɵĲ�ѯ���� ���:SeachType
	 */
	public int searchType;
	public LabelInfo(String label,int fromType, int searchType) {
		this.label = label;
		this.fromType = fromType;
		this.searchType = searchType;
	}
}