package org.frame.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**
 * 
 * @author CJSHEN
 *
 */
public class AnyRolesFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {

		Subject subject = getSubject(request, response);
		String[] rolesArray = (String[]) mappedValue;

		for (String role : rolesArray) {
			if (subject.hasRole(role)) {
				return true;
			}
		}

		return false;
	}

}
