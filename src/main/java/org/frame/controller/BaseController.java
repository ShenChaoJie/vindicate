package org.frame.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.frame.common.utils.MyUtil;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;




public abstract class BaseController extends
		org.springframework.web.servlet.mvc.AbstractController {
	private Log log = LogFactory.getLog(BaseController.class);
	ThreadLocal<Map<String, Object>> tl = new ThreadLocal<Map<String, Object>>();

	public HttpServletRequest getRequest() {
		return (HttpServletRequest) tl.get().get("request");
	}

	public HttpServletResponse getResponse() {
		return (HttpServletResponse) tl.get().get("response");
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest _request,HttpServletResponse _response) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		rsMap.put("request", _request);
		rsMap.put("response", _response);
		tl.set(rsMap);
		return excute();
	}

	public Map<String, String> getPara() throws Exception {
		Map<String, String> paraMap = new HashMap<String, String>();
		java.util.Enumeration<String> pns = this.getRequest().getParameterNames();
		String key = "";
		char a = 6;
		MyUtil mu = MyUtil.getInstance();
		while (pns.hasMoreElements()) {
			key = pns.nextElement();
			String[] vs = this.getRequest().getParameterValues(key);
			String value = "";
			if (vs.length == 1)
				value = vs[0].trim();
			else {
				for (String v : vs)
					value += v.trim() + a;
				value = value.substring(0, value.length() - 1);
			}
			//value = mu.htmEncode(value);// //特殊字符转换
			if (!paraMap.containsKey(key)) {
				paraMap.put(key, value);
			} else {
				paraMap.put(key, paraMap.get(key).toString() + a + value);
			}
		}
		return paraMap;
	}

	public Map<String, String> getAjaxPara() throws Exception {
		return getPara();
	}

	public ModelAndView setJsonResult(Object obj) throws Exception {
		this.getResponse().setContentType("text/plain;charset=utf-8");
		PrintWriter responseStream = this.getResponse().getWriter();
		responseStream.println(JSONArray.fromObject(obj));
		responseStream.close();
		return null;
	}
	
	public ModelAndView errorAjax(Exception ex) {
		try {
			if (log.isErrorEnabled())
				log.error("web exception:", ex);
			this.getResponse().setContentType("text/plain;charset=utf-8");
			PrintWriter responseStream = this.getResponse().getWriter();
			Map<String, String> rmap = new HashMap<String, String>();
			if (ex instanceof java.sql.SQLException) {
				rmap.put("info", "sqlEx");
			} else if (ex instanceof java.io.IOException) {
				rmap.put("info", "fileEx");
			} else {
				rmap.put("info", "otherEx");
			}
			responseStream.println(JSONArray.fromObject(rmap));
			responseStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ModelAndView error(Exception ex) {
		if (log.isErrorEnabled())
			log.error("web exception:", ex);
		Map<String, String> rmap = new HashMap<String, String>();
		if (ex instanceof java.sql.SQLException) {
			rmap.put("info", "sqlEx");
		} else if (ex instanceof java.io.IOException) {
			rmap.put("info", "fileEx");
		} else if (ex instanceof org.apache.commons.fileupload.FileUploadException) {
			rmap.put("info", "fileUploadEx");
		} else {
			rmap.put("info", "otherEx");
		}
		return new ModelAndView("/error.jsp", rmap);
	}
	
	public String jsp;

	public void setJsp(String jsp) {
		this.jsp = jsp;
	}

	public String getJsp() {
		return this.jsp;
	}

	public Object getBean(String beaname) {
		WebApplicationContext ctx = getWebApplicationContext();
		return ctx.getBean(beaname);
	}

	public abstract ModelAndView excute();

}
