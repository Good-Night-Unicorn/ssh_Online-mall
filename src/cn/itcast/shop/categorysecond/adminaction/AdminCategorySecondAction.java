package cn.itcast.shop.categorysecond.adminaction;

import java.util.List;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.categorysecond.service.CategorySecondService;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategorySecondAction extends ActionSupport implements
		ModelDriven<CategorySecond> {
	private CategorySecond categorySecond = new CategorySecond();

	@Override
	public CategorySecond getModel() {
		// TODO Auto-generated method stub
		return categorySecond;
	}

	private CategorySecondService categorySecondService;

	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	// 要进行分页查询，就要接受page
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}
	//注入一级分类的service
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	// 查询二级分类的方法
	public String findAll() {
		PageBean<CategorySecond> pageBean = categorySecondService
				.findByPage(page);
		//将信息存到值栈中去
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);

		return "findAll";
	}
	public String addPage(){
		//二级分类依赖于一级分类，所以要先查询一级分类
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPageSuccess";
	}
	public String save(){
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	public String delete(){
		categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	//编辑二级分类的方法
	public String edit(){
		//根据二级分类id查询二级分类对象
		//查询所有一级分类，对二级分类对象进行匹配
		categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	public String update(){
		categorySecondService.update(categorySecond);
		
		return "updateSuccess";
	}
}
