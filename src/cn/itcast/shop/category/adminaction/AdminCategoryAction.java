package cn.itcast.shop.category.adminaction;

import java.util.List;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台一级分类管理的action
 * 
 * @author Kang
 * 
 */
public class AdminCategoryAction extends ActionSupport implements
		ModelDriven<Category> {

	private Category category = new Category();
	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}

	public String findAll() {
		//通过service的find方法查询，返回一个list集合
		List<Category> cList = categoryService.findAll();
		//通过值栈将数据返回到页面上去
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	public String save(){
		categoryService.save(category);
		return "saveSuccess";
	}
	public String delete(){
		category=categoryService.findByCid(category.getCid());
		categoryService.delete(category);
		return "deleteSuccess";
	}
	public String edit(){
		category=categoryService.findByCid(category.getCid());
		return "editSuccess";
	}
	public String update(){
		categoryService.update(category);
		return "updateSuccess";
	}
	
}
