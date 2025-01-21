package cn.itcast.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.category.dao.CategoryDao;
import cn.itcast.shop.category.vo.Category;

/**
 * 业务层
 * @author Kang
 *
 */
@Transactional
public class CategoryService {
	//注入dao 提供相应的set方法
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
//业务层查询所有一级分类
	public List<Category> findAll() {
		
		return categoryDao.findAll();
	}
	public void save(Category category) {
		// TODO Auto-generated method stub
		categoryDao.save(category);
	}
	//业务层根据cid查询一级分类
	public Category findByCid(String cid) {
		// TODO Auto-generated method stub
		return categoryDao.findByCid(cid);
	}
	public void delete(Category category) {
		// TODO Auto-generated method stub
		categoryDao.delete(category);
	}
	public void update(Category category) {
		// TODO Auto-generated method stub
		categoryDao.update(category);
	}

	
	
	
}
