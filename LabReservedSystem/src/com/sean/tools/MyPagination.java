package com.sean.tools;
import java.util.ArrayList;
import java.util.List;
/** 
* @ClassName: MyPagination 
* @Description: TODO(��ҳ���룬���з�ҳ��ʾ) 
* @author sean 
* @email:364672554@qq.com 
* @date 2017��12��6�� ����7:39:28 
*  
*/
public class MyPagination<T> {
	public ArrayList<T> list=null;
	private int recordCount=0;//�����¼�����ı���
	private int pagesize=0;//����ÿҳ��ʾ�ļ�¼���ı���
	private int maxPage=0;//�������ҳ���ı���	
	/** 
	* @Title: getInitPage 
	* @Description: TODO(��ʼ����ҳ��Ϣ) 
	* @param list
	* @param Page ��1��ʼ
	* @param pagesize
	* @return List<Object>    �������� 
	* @throws 
	*/
	public List<T> getInitPage(ArrayList<T>list,int Page,int pagesize){
		ArrayList<T> newList=new ArrayList<T>();
		this.list=list;
		recordCount=list.size();//��ȡlist���ϵ�Ԫ�ظ���
		this.pagesize=pagesize;
		this.maxPage=getMaxPage();
		try{
			for(int i=(Page-1)*pagesize;i<=Page*pagesize-1;i++){
				try {
					if(i>=recordCount){
						break;
					}
				} catch (Exception e) {}
				newList.add((T)list.get(i)); //ǿ��ת��			
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
		return newList;
	}
	/** 
	* @Title: getAppointPage 
	* @Description: TODO(ָ����ʾ��ҳ��) 
	* @param Page
	* @return List<Object>    �������� 
	* @throws 
	*/
	public List<T> getAppointPage(int Page){
		ArrayList<T> newList=new ArrayList<T>();
		try {
			for(int i=(Page-1)*pagesize;i<=Page*pagesize-1;i++){
				try {
					if(i>=recordCount){
						break;
					}
				} catch (Exception e) {}
					newList.add((T)list.get(i)); //ǿ��ת��			
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return newList;	
	}	
	/** 
	* @Title: getMaxPage 
	* @Description: TODO(�������¼��) 
	* @return int    �������� 
	* @throws 
	*/
	public int getMaxPage() {
		// TODO Auto-generated method stub
		int maxPage=(recordCount%pagesize==0)?(recordCount/pagesize):(recordCount/pagesize+1);
		return maxPage;
	}
	/** 
	* @Title: getRecordSize 
	* @Description: TODO(����ܼ�¼��) 
	* @return int    �������� 
	* @throws 
	*/
	public int getRecordSize() {
		return recordCount;
	}
	/** 
	* @Title: getPage 
	* @Description: TODO(��ȡ��ǰҳ��) 
	* @param str ָ��ҳ��ҳ��
	* @return int    �������� 
	* @throws 
	*/
	public int getPage(String str) {
		if(str==null){
			str="0";
		}
		int Page=Integer.parseInt(str);
		if(Page<1){
			Page=1;
		}
		else{
			if(((Page-1)*pagesize+1)>recordCount){ 			//��ҳ���������ҳ����ʱ������������ҳ��
				Page=maxPage; 
			}
		}
		return Page;
	}
	/** 
	* @Title: printCtrl 
	* @Description: TODO(�����¼�����ַ���) 
	* @param Page ��ǰҳ��
	* @param url URL��ַ
	* @param para Ҫ���ݵĲ���
	* @return String  �����ַ��� 
	* @throws 
	*/
	public String printCtrl(int Page,String url,String para){
		String strHtml="<table width='100%' border='0' cellspacing='0' cellpadding='0'><tr>"
				+ "<td height='24' align='right'>��ǰҳ����"+Page+"/"+maxPage+"��&nbsp;";
		try {
			if(Page>1){
				strHtml=strHtml+"<a href='"+url+"&Page=1"+para+"'>��һҳ</a>  ";
				strHtml=strHtml+"<a href='"+url+"&Page="+(Page-1)+para+"'>��һҳ</a>";
			}
			if(Page<maxPage){
				strHtml=strHtml+"<a href='"+url+"&Page="+(Page+1)+para+"'>��һҳ</a>  "
						+ "<a href='"+url+"&Page="+maxPage+para+"'>���һҳ&nbsp;</a>";
						
			}
			strHtml=strHtml+"</td></tr>     </table>";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return strHtml;
	}
}