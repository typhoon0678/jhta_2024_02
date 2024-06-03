package com.typhoon0678.mybatis.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class BasicFilter implements Filter {

	FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		System.out.println("basicFilter :::" + filterConfig.getFilterName() + "::: init");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
		IOException,
		ServletException {
		String filterInitParam = filterConfig.getInitParameter("FILTER_INIT_PARAM");

		System.out.println("basicFilter :::" + filterInitParam + "::: filterInitParameter");

		filterChain.doFilter(servletRequest, servletResponse);

		System.out.println("basicFilter :::" + filterConfig.getFilterName() + "::: doFilter");
	}

	@Override
	public void destroy() {
		System.out.println("basicFilter :::" + filterConfig.getFilterName() + "::: destroy");
	}
}