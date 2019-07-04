/**
* @Title: ErrorServlet.java
* @Package com.sean.servlet
* @Description: TODO(非法操作跳转Servlet)
* @author wsl
* @date 2018.9.1
* @version V1.0
*/
package com.sean.servlet;
import java.awt.Window;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class ErrorServlet
 */
public class ErrorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ErrorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void login_error(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
                response.setContentType("text/html;charset=UTF-8");  
                PrintWriter out = response.getWriter();    
                try {  
                    out.println("<html>");  
                    out.println("<head>");  
                    out.println("<title>Servlet Error</title>");    
                    out.println("<script>");
                    out.println("setTimeout(function(){window.top.location.href=\"../index.jsp\";},3000);");
                    out.println("</script>");    
                    out.println("</head>");  
                    out.println("<body>");  
                    out.println("<h2 align=center>" + request.getAttribute("err") + "</h2>");           
                    response.setContentType("text/html;charset=UTF-8");  
                    response.getWriter().write("请登录！页面将在3秒后自动跳转，如果没有，请点击<a href='../index.jsp'>回到主页</a>");  
                  
                    out.println("</body>");  
                    out.println("</html>");  

                } finally {  
                    out.close();  
                }  
            }  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if ("login".equals(action)) {
			login_error(request, response); 
		} 
	}
}
