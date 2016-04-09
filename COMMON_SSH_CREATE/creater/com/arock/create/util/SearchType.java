package com.arock.create.util;
public interface SearchType {
	int _0NO = 0;//不生成查询 .不显示
	
	int _0NSEARCH = 10;//不生成查询 .有显示
	
	int _1SELECT = 100;
	int _2STRING = 200;
	int _3DATE = 300;
	
	//判断生成规则
	//%100/10
}