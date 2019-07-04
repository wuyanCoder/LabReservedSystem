/**
* @Title: IPv6Test
* @Package com.sean.tools
* @Description: TODO(获取访问用户的IPv6地址)
* @author wsl
* @date 2018.9.15
* @version V1.0
*/
package com.sean.tools;
import javax.servlet.http.HttpServletRequest;
public class IPv6Test {	
	public static String getIpAddr(HttpServletRequest request) {  
	       String ip = request.getHeader("x-forwarded-for");  
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	           ip = request.getHeader("Proxy-Client-IP");  
	       }  
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	           ip = request.getHeader("WL-Proxy-Client-IP");  
	       }  
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	           ip = request.getRemoteAddr();  
	       }  
	       return ip;
	   }   
}