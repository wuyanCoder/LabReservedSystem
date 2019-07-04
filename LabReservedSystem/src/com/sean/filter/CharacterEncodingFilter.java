package com.sean.filter;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/** 
* @ClassName: CharacterEncodingFilter 
* @Description: TODO(��ҳ�ַ����������) 
* @author sean 
* @email:364672554@qq.com 
* @date 2017��12��6�� ����9:33:46  
*/
public class CharacterEncodingFilter implements Filter {
	protected String encoding=null;//��������ʽ����
	protected FilterConfig filterConfig=null;//������������ö���
    @Override
	public void init(FilterConfig filterConfig)throws ServletException{
		this.filterConfig=filterConfig;//��ʼ�����������ö���
		this.encoding=filterConfig.getInitParameter("encoding");//��ȡ�����ļ��е�ָ���ı����ʽ
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		this.encoding=null;
		this.filterConfig=null;
	}
	@Override
	public void doFilter(ServletRequest requset, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		if(encoding!=null){
			requset.setCharacterEncoding(encoding);
			//������Ӧ������������ͣ����������ʽ��
			response.setContentType("text/html;charset="+encoding);
		}
		chain.doFilter(requset, response);//���ݸ���һ��������		
	}
}