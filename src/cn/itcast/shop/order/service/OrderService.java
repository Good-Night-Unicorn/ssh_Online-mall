package cn.itcast.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.order.dao.OrderDao;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.utils.PageBean;

/**
 * 订单模块业务层
 * @author Kang
 *
 */
@Transactional
public class OrderService {
	//注入DAO
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void save(Order order) {
		orderDao.save(order);
		
	}
	//订单业务层代码
	public PageBean<Order> findByPageUid(String uid, Integer page) {
		PageBean<Order> pageBean=new PageBean<Order>();
		//设置当前页数
		pageBean.setPage(page);
		//每页记录数
		Integer limit=5;
		pageBean.setLimit(limit);
		//设置总记录数
		Integer totalCount=null;
		totalCount=orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		Integer totalPage=null;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}else{
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//每页显示数据的集合
		Integer begin=(page-1)*limit;
		List<Order> list=orderDao.findByPageUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//根据订单ID查询订单
	public Order findByOid(String integer) {
		
		return orderDao.findByOid(integer);
	}
	//业务层补全订单
	public void update(Order currOrder) {
		orderDao.update(currOrder);
		
	}

	public PageBean<Order> findByPage(Integer page) {
		PageBean<Order> pageBean=new PageBean<Order>();
		pageBean.setPage(page);
		int limit=10;
		pageBean.setLimit(limit);
		int totalCount=orderDao.findByCount();
		pageBean.setTotalCount(totalCount);
		int totalPage=0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}else{
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*limit;
		List<Order> list=orderDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public List<OrderItem> findOrderItem(String oid) {
		
		return orderDao.findOrderItem(oid);
	}
	
}
