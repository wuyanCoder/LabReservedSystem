package com.sean.tools;
import java.util.ArrayList;
import java.util.List;
/** 
* @ClassName: MyPagination 
* @Description: TODO(分页代码，进行分页显示) 
* @author sean 
* @email:364672554@qq.com 
* @date 2017年12月6日 下午7:39:28 
*  
*/
public class MyPagination<T> {
	public ArrayList<T> list=null;
	private int recordCount=0;//保存记录总数的变量
	private int pagesize=0;//保存每页显示的记录数的变量
	private int maxPage=0;//保存最大页数的变量	
	/** 
	* @Title: getInitPage 
	* @Description: TODO(初始化分页信息) 
	* @param list
	* @param Page 从1开始
	* @param pagesize
	* @return List<Object>    返回类型 
	* @throws 
	*/
	public List<T> getInitPage(ArrayList<T>list,int Page,int pagesize){
		ArrayList<T> newList=new ArrayList<T>();
		this.list=list;
		recordCount=list.size();//获取list集合的元素个数
		this.pagesize=pagesize;
		this.maxPage=getMaxPage();
		try{
			for(int i=(Page-1)*pagesize;i<=Page*pagesize-1;i++){
				try {
					if(i>=recordCount){
						break;
					}
				} catch (Exception e) {}
				newList.add((T)list.get(i)); //强制转换			
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
		return newList;
	}
	/** 
	* @Title: getAppointPage 
	* @Description: TODO(指定显示的页数) 
	* @param Page
	* @return List<Object>    返回类型 
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
					newList.add((T)list.get(i)); //强制转换			
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return newList;	
	}	
	/** 
	* @Title: getMaxPage 
	* @Description: TODO(获得最大记录数) 
	* @return int    返回类型 
	* @throws 
	*/
	public int getMaxPage() {
		// TODO Auto-generated method stub
		int maxPage=(recordCount%pagesize==0)?(recordCount/pagesize):(recordCount/pagesize+1);
		return maxPage;
	}
	/** 
	* @Title: getRecordSize 
	* @Description: TODO(获得总记录数) 
	* @return int    返回类型 
	* @throws 
	*/
	public int getRecordSize() {
		return recordCount;
	}
	/** 
	* @Title: getPage 
	* @Description: TODO(获取当前页数) 
	* @param str 指定页面页数
	* @return int    返回类型 
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
			if(((Page-1)*pagesize+1)>recordCount){ 			//当页数大于最大页数的时候，让其等于最大页数
				Page=maxPage; 
			}
		}
		return Page;
	}
	/** 
	* @Title: printCtrl 
	* @Description: TODO(输出记录导航字符串) 
	* @param Page 当前页数
	* @param url URL地址
	* @param para 要传递的参数
	* @return String  导航字符串 
	* @throws 
	*/
	public String printCtrl(int Page,String url,String para){
		String strHtml="<table width='100%' border='0' cellspacing='0' cellpadding='0'><tr>"
				+ "<td height='24' align='right'>当前页数【"+Page+"/"+maxPage+"】&nbsp;";
		try {
			if(Page>1){
				strHtml=strHtml+"<a href='"+url+"&Page=1"+para+"'>第一页</a>  ";
				strHtml=strHtml+"<a href='"+url+"&Page="+(Page-1)+para+"'>上一页</a>";
			}
			if(Page<maxPage){
				strHtml=strHtml+"<a href='"+url+"&Page="+(Page+1)+para+"'>下一页</a>  "
						+ "<a href='"+url+"&Page="+maxPage+para+"'>最后一页&nbsp;</a>";
						
			}
			strHtml=strHtml+"</td></tr>     </table>";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return strHtml;
	}
}