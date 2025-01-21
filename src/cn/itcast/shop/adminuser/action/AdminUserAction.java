package cn.itcast.shop.adminuser.action;

/**
 * 后台登录的action
 * @author Kang
 *
 */
import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.adminuser.service.AdminUserService;
import cn.itcast.shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{
	
	//模型驱动使用的对象
	private AdminUser adminUser=new AdminUser();
	public AdminUser getModel() {
		// TODO Auto-generated method stub
		return adminUser;
	}
	//注入service
	private AdminUserService adminUserService;
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	
	public String login(){
		
		AdminUser existAdminUser=adminUserService.login(adminUser);
		if(existAdminUser==null){
			this.addActionError("登录失败");
			return "loginFail";
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";
		}
		
		
	}
	
	
}
