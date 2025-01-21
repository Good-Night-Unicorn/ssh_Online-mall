package cn.itcast.shop.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.adminuser.vo.AdminUser;

/**
 * 后台dao类
 * 
 * @author Kang
 * 
 */
public class AdminUserDao extends HibernateDaoSupport {

	public AdminUser login(AdminUser adminUser) {
		String hql = "from AdminUser where username =? and password=?";
		List<AdminUser> list = this.getHibernateTemplate().find(hql,
				adminUser.getUsername(), adminUser.getPassword());
		
		if(list!=null&& list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
