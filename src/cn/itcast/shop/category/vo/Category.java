package cn.itcast.shop.category.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import cn.itcast.shop.categorysecond.vo.CategorySecond;

/**
 * 一级分类的实体类
 * @author Kang
 *一级分类存到session中，让它实现序列化接口，避免序列化异常发生
 *session超时，非正常关闭
 *序列化与反序列化
 *序列化：将对象的状态信息转换为可以存储或传输的形式的过程
 *在序列化过程中，对象将其当前状态写入到临时或持久性存储区如硬盘
 *以后可以通过从存储区中读取或反序列化对象
 */
public class Category implements Serializable{
	private String cid;
	private String cname;
	//一级分类存放二级分类的集合
	private Set<CategorySecond> categorySeconds=new HashSet<CategorySecond>();
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	
}
