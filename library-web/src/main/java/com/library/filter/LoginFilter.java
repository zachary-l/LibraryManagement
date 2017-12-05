package com.library.filter;

import com.library.model.Admin;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName="LoginFilter",urlPatterns= {"/index.html"})
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			HttpServletRequest req = (HttpServletRequest)request;
			HttpServletResponse res = (HttpServletResponse)response;
			HttpSession session = req.getSession();
			Admin admin = (Admin)session.getAttribute("admin");
			if(admin!= null && admin.getAdId()!=0){
				chain.doFilter(req, res);
			}else{
				res.sendRedirect("login.html");
			}
			
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
