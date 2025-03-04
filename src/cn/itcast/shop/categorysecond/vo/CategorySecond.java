package cn.itcast.shop.categorysecond.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.product.vo.Product;

/**
 * 二级分类的实体类
 * @author Kang
 *
 */
public class CategorySecond implements Serializable{
	private String csid;
	private String csname;
	//涉及外键，存储一级分类的对象即可
	private Category category;
	//配置商品的集合
	private Set<Product> products=new HashSet<Product>();
	public String getCsid() {
		return csid;
	}
	public void setCsid(String csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
		
	
	
}
