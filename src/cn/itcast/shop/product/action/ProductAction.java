package cn.itcast.shop.product.action;

import java.util.List;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 只在页面显示一下就不用存到session中，存到值栈中就行
 * @author Kang
 *
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	//用于接收数据的模型驱动
	private Product product=new Product();
	//注入商品的Service
	private ProductService productService;
	//接收分类cid
	private String cid;
	//接收二级分类的id
	private String csid;
	
	public String getCsid() {
		return csid;
	}
	public void setCsid(String csid) {
		this.csid = csid;
	}
	//因为在一级分类中已经进行了对一级分类的查询，而二级分类的查询依赖于一级分类
	//所以，注入一级分类的service
	private CategoryService categoryService;
	//接收当前页数
	private int page;
	
	
	public void setPage(int page) {
		this.page = page;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	
	public String getCid() {
		return cid;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	@Override
	public Product getModel() {
		// 用于接收数据的模型驱动
		return product;
	}
	//根据商品ID查询商品具体信息
	public String findByPid(){
		//调用service的方法完成查询
		product=productService.findByPid(product.getPid());
		return "findByPid";
	}
	//根据分类的ID查询方法
	public String findByCid(){
		//List<Category>clist=categoryService.findAll();//查询所有的一级分类
		PageBean<Product> pageBean=productService.findByPageCid(cid,page);
		//将pageBean放到值栈中去
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	//根据二级分类id查询商品
	public String findByCsid(){
		//根据二级分类查询商品
		PageBean<Product>pageBean=productService.findByPageCsid(csid,page);
		//将pageBean放到值栈中去
				ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
	
}
