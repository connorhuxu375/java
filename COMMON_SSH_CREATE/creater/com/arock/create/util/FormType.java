package com.arock.create.util;
public interface FormType {
	int _0NO = 0; //FORM POJO 全部不生成.
	int _0NFORM = 10;//不生成FORM,生成POJO字段
	
	int _201SELECT = 201;
	int _202RADIO= 202;
	int _203CHECKBOX = 203;
	
	int _101TEXT = 101;
	int _102PASSWORD = 102;
	int _103TEXTAREA =103;
	int _104DATE = 104;
	
	//判断生成规则
	//%100/10
	
}