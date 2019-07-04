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
* @Description: TODO(网页字符编码过滤器) 
* @author sean 
* @email:364672554@qq.com 
* @date 2017年12月6日 下午9:33:46  
*/
public class CharacterEncodingFilter implements Filter {
	protected String encoding=null;//定义编码格式变量
	protected FilterConfig filterConfig=null;//定义过滤器配置对象
    @Override
	public void init(FilterConfig filterConfig)throws ServletException{
		this.filterConfig=filterConfig;//初始化过滤器配置对象
		this.encoding=filterConfig.getInitParameter("encoding");//获取配置文件中的指定的编码格式
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
			//设置响应对象的内容类型（包括编码格式）
			response.setContentType("text/html;charset="+encoding);
		}
		chain.doFilter(requset, response);//传递给下一个过滤器		
	}
}