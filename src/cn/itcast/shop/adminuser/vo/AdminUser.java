package cn.itcast.shop.adminuser.vo;

/**
 * 管理员实体类
 * 
 * @author Kang
 * 
 */
public class AdminUser {
	private String adminid;
	private String username;
	private String password;
	public String getAdminid() {
		return adminid;
	}
	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
