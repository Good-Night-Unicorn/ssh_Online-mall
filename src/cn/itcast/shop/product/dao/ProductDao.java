package cn.itcast.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageHibernateCallback;

public class ProductDao extends HibernateDaoSupport {

	public List<Product> finHot() {
		// 首页热门查询：离线条件查询，execute方法
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.add(Restrictions.eq("is_hot", 1));
		// 倒叙输出
		criteria.addOrder(Order.desc("pdate"));
		List<Product> list = this.getHibernateTemplate().findByCriteria(
				criteria, 0, 10);
		return list;
	}

	public List<Product> findNew() {
		// 最新商品查询（根据date进行排序）
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		criteria.addOrder(Order.desc("pdate"));
		List<Product> list=this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		
		return list;
	}

	public Product finByPid(String pid) {
		// 根据商品的ID进行查询
		return this.getHibernateTemplate().get(Product.class, pid);
	}
	//根据分类的id查询商品的个数
	public int findCountCid(String cid) {
		//hql两表查询select * from CategorySecond cs join cs.category;
		String hql="select count(*) from Product p where p.categorySecond.category.cid=?";
		List<Long>list=this.getHibernateTemplate().find(hql,cid);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//分类id查询商品的集合
	public List<Product> findByPageCid(String cid, int begin, int limit) {
		String hql="select p from Product p join p.categorySecond cs join cs.category c where c.cid=?";
		//不能用离线的分页了
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if(list!=null&&list.size()>0){
			
			return list;
		}
		return null;
	}
	//根据二级分类查询商品个数
	public int findCountCsid(String csid) {
		String hql="select count(*) from Product p where p.categorySecond.csid=?";
		List<Long> list=this.getHibernateTemplate().find(hql, csid);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//根据二级分类查询商品信息
	public List<Product> findByPageCsid(String csid, int begin, int limit) {
		String hql="select p from Product p join p.categorySecond cs where cs.csid=?";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if(list!=null&&list.size()>0){
					
					return list;
				}
				return null;
	}

	public int findCount() {
		String hql="select count(*) from Product";
		List<Long> list	=this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Product> findByPage(int begin, int limit) {
		String hql="from Product order by pdate desc";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null, begin, limit));
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}

	public void save(Product product) {
		this.getHibernateTemplate().save(product);
		
	}

	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
		
	}

	public void update(Product product) {
		this.getHibernateTemplate().update(product);
		
	}


}
