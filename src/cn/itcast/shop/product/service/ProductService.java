package cn.itcast.shop.product.service;

/**
 * 如果仅仅是查询，可以不用写事务，但是如果有其他操作就要写事务
 */

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.product.dao.ProductDao;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;

@Transactional
public class ProductService {
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	// 首页热门商品的查询
	public List<Product> findHot() {

		return productDao.finHot();
	}

	public List<Product> findNew() {
		// 最新商品查询
		return productDao.findNew();
	}

	public Product findByPid(String pid) {
		// 根据商品ID查询
		return productDao.finByPid(pid);
	}

	public PageBean<Product> findByPageCid(String cid, int page) {
		// 根据一级分类带分页查询商品
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数；
		pageBean.setPage(page);
		// 每页记录数
		int limit = 8;
		pageBean.setLimit(limit);
		// 总记录数
		int totalCount = 0;
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		//向上取整Math.ceil(totalCount / limit);
		// 取余数
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//每页显示的数据集合
		//开始
		int begin=(page-1)*limit;
		List<Product> list=productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Product> findByPageCsid(String csid, int page) {
		// 根据二级分类查询商品信息
		// 根据一级分类带分页查询商品
				PageBean<Product> pageBean = new PageBean<Product>();
				// 设置当前页数；
				pageBean.setPage(page);
				// 每页记录数
				int limit = 8;
				pageBean.setLimit(limit);
				// 总记录数
				int totalCount = 0;
				totalCount = productDao.findCountCsid(csid);
				pageBean.setTotalCount(totalCount);
				// 设置总页数
				int totalPage = 0;
				//向上取整Math.ceil(totalCount / limit);
				// 取余数
				if (totalCount % limit == 0) {
					totalPage = totalCount / limit;
				} else {
					totalPage = totalCount / limit + 1;
				}
				pageBean.setTotalPage(totalPage);
				//每页显示的数据集合
				//开始
				int begin=(page-1)*limit;
				List<Product> list=productDao.findByPageCsid(csid,begin,limit);
				pageBean.setList(list);
				return pageBean;
	}

	public PageBean<Product> findByPage(Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数；
		pageBean.setPage(page);
		// 每页记录数
		int limit = 10;
		pageBean.setLimit(limit);
		// 总记录数
		int totalCount = 0;
		totalCount = productDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		//向上取整Math.ceil(totalCount / limit);
		// 取余数
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//每页显示的数据集合
		//开始
		int begin=(page-1)*limit;
		List<Product> list=productDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public void save(Product product) {
		productDao.save(product);
		
	}

	public void delete(Product product) {
		productDao.delete(product);
		
	}

	public void update(Product product) {
		productDao.update(product);
		
	}

}
