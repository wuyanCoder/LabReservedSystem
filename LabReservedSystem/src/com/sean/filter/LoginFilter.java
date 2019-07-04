/**
* @Title: LoginFilter.java
* @Package com.sean.filter
* @Description: TODO(登陆操作)
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
        
        //1、得到用户请求路径
        String servletPath=httpServletRequest.getServletPath();
        //2、放行不需要过滤的页面，如登录界面之类的
        List<String> urls=Arrays.asList(uncheckedUrls.split(","));
        if(urls.contains(servletPath)){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        //3、从session中获取用户并判断用户是否登录过，如果没有登录过则重定向到登录界面
        HttpSession session = httpServletRequest.getSession();
        Object sessionKey= session.getAttribute("sessionKey");
        if(session==null|| sessionKey==null){
        	 //如果判断是 AJAX 请求,直接设置为session超时
            if( httpServletRequest.getHeader("x-requested-with") != null && httpServletRequest.getHeader("x-requested-with").equals("XMLHttpRequest") ) {
            	httpServletResponse.setHeader("sessionstatus", "timeout"); 
            } 
            else {
            	String   msg = "您未登录！";  
                String  url = "../ErrorServlet?action=login";  
                httpServletRequest.setAttribute("err", msg);  
                 RequestDispatcher dis = httpServletRequest.getRequestDispatcher(url);  
                 dis.forward(httpServletRequest,httpServletResponse);  	
           // httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+rediretUrl) ;}
            return;
            }
        }
        
        //4、否则放行
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
