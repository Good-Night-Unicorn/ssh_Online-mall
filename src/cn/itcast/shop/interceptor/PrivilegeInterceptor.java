package cn.itcast.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PrivilegeInterceptor extends MethodFilterInterceptor{
	//判断后台中session是否存有用户的信息，作为后台校验的依据
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		AdminUser existAdminUser=(AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
		if(existAdminUser==null){
			
			ActionSupport actionSupport=(ActionSupport) actionInvocation.getAction();
			actionSupport.addActionError("请先登录");
			
					return "loginFail";
		}else{
			return actionInvocation.invoke();
		}
		
	}

}
