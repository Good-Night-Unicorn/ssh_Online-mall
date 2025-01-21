package cn.itcast.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.utils.PageHibernateCallback;

/**
 * DAO层的订单模块代码
 * 
 * @author Kang
 * 
 */
public class OrderDao extends HibernateDaoSupport {

	public void save(Order order) {
		this.getHibernateTemplate().save(order);

	}

	// dao订单个数统计
	public Integer findByCountUid(String uid) {
		String hql = "select count(*) from Order o where o.user.uid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();

		}
		return null;
	}

	// 订单统计
	public List<Order> findByPageUid(String uid, Integer begin, Integer limit) {
		String hql = "from Order o where o.user.uid=? order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, new Object[] { uid },
						begin, limit));
		return list;
	}

	public Order findByOid(String oid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Order.class, oid);
	}
	//dao补全订单
	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
		
	}

	public int findByCount() {
		String hql="select count(*) from Order";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Order> findByPage(int begin, int limit) {
		String hql="from Order order by ordertime desc";
		List<Order> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, null, begin, limit));
		if(list!=null&&list.size()>0){
			return list;
		}
		
		return null;
	}

	public List<OrderItem> findOrderItem(String oid) {
		String hql="from OrderItem oi where oi.order.oid=?";
		List<OrderItem> list=this.getHibernateTemplate().find(hql,oid);
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}

//	public List<OrderItem> findOrderItem(String oid) {
//		String hql="from OrderItem oi where oi.order.oid=?";
//		List<OrderItem> list=this.getHibernateTemplate().find(hql,oid);
//		if(list!=null&&list.size()>0){
//			return list;
//		}
//		return null;
//	}

}
