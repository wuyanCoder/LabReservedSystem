/**
* @Title: LoginFilter.java
* @Package com.sean.filter
* @Description: TODO(��½����)
* @author sean
* @date 2018.9.1
* @version V1.0
*/
package com.sean.filter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class LoginFilter
 */
public class LoginFilter implements Filter {
	
	private String sessionKey;
	private String rediretUrl;
	private String uncheckedUrls;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub		
	}
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,FilterChain filterChain) throws IOException, ServletException  {
		// TODO Auto-generated method stub
		HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse) servletResponse;
        
        //1���õ��û�����·��
        String servletPath=httpServletRequest.getServletPath();
        //2�����в���Ҫ���˵�ҳ�棬���¼����֮���
        List<String> urls=Arrays.asList(uncheckedUrls.split(","));
        if(urls.contains(servletPath)){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        //3����session�л�ȡ�û����ж��û��Ƿ��¼�������û�е�¼�����ض��򵽵�¼����
        HttpSession session = httpServletRequest.getSession();
        Object sessionKey= session.getAttribute("sessionKey");
        if(session==null|| sessionKey==null){
        	 //����ж��� AJAX ����,ֱ������Ϊsession��ʱ
            if( httpServletRequest.getHeader("x-requested-with") != null && httpServletRequest.getHeader("x-requested-with").equals("XMLHttpRequest") ) {
            	httpServletResponse.setHeader("sessionstatus", "timeout"); 
            } 
            else {
            	String   msg = "��δ��¼��";  
                String  url = "../ErrorServlet?action=login";  
                httpServletRequest.setAttribute("err", msg);  
                 RequestDispatcher dis = httpServletRequest.getRequestDispatcher(url);  
                 dis.forward(httpServletRequest,httpServletResponse);  	
           // httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+rediretUrl) ;}
            return;
            }
        }
        
        //4���������
        filterChain.doFilter(httpServletRequest, httpServletResponse);
}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		 ServletContext servletContext=filterConfig.getServletContext();
	        sessionKey=servletContext.getInitParameter("userSessonKey");
	        rediretUrl=servletContext.getInitParameter("rediretUrl");
	        uncheckedUrls=servletContext.getInitParameter("uncheckedUrls");
	}
}
