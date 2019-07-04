package com.sean.tools;
/** 
* @ClassName: StringDeal 
* @Description: TODO(将程序中的字符串文本转换成HTML语言文本 使用示例 ： ${wghfn:shiftEnter(param.content)} 
* @author Sean
* @date 2018年1月17日 下午7:45:19 
*  
*/
public class StringDeal {
	public static String shiftEnter(String str){
		String newStr=str.replaceAll("\r\n", "<br>");
		newStr=newStr.replaceAll(" ","&nbsp" );
		return newStr;
	}
}