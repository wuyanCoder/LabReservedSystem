package com.sean.tools;
/** 
* @ClassName: StringDeal 
* @Description: TODO(�������е��ַ����ı�ת����HTML�����ı� ʹ��ʾ�� �� ${wghfn:shiftEnter(param.content)} 
* @author Sean
* @date 2018��1��17�� ����7:45:19 
*  
*/
public class StringDeal {
	public static String shiftEnter(String str){
		String newStr=str.replaceAll("\r\n", "<br>");
		newStr=newStr.replaceAll(" ","&nbsp" );
		return newStr;
	}
}