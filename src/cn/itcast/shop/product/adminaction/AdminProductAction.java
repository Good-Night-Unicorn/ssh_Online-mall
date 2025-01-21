package cn.itcast.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.categorysecond.service.CategorySecondService;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminProductAction extends ActionSupport implements
		ModelDriven<Product> {
	private Product product = new Product();

	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}

	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	// 注入二级分类service
	private CategorySecondService categorySecondService;

	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	// 分页查询，需接收page
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	// 文件上传所需要的参数
	private File upload;// 上传的文件
	private String uploadFileName;// 接收上传文件名
	private String uploadContextType;// 接收文件上传的文件的MIME的类型MIME(Multipurpose
										// Internet Mail Extensions)多用途互联网邮件扩展类型

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}

	// 执行的方法
	public String findAll() {

		PageBean<Product> pageBean = productService.findByPage(page);
		// 通过值栈将数据传送到页面上
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);

		return "findAll";

	}

	// 添加商品页面的跳转
	public String addPage() {
		// 查询所有二级分类的集合
		List<CategorySecond> csList = categorySecondService.findAll();
		// 通过值栈保存数据
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPageSuccess";
	}

	// 保存商品
	public String save() throws IOException {
		product.setPdate(new Date());
		// 图片上传路径
		if (upload != null) {
			// 获得文件上传的磁盘绝对路径
			String realPath = ServletActionContext.getServletContext()
					.getRealPath("/products");
			// 创建一个文件，代表磁盘上的一个文件
			File diskFile = new File(realPath + "//" + uploadFileName);
			// 上传文件
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/" + uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}

	public String delete() {

		product = productService.findByPid(product.getPid());
		String path = product.getImage();
		if (path != null) {

			String realPath = ServletActionContext.getServletContext()
					.getRealPath("/" + path);

			File file = new File(realPath);
			file.delete();
		}
		productService.delete(product);
		return "deleteSuccess";
	}

	public String edit() {
		product = productService.findByPid(product.getPid());
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editSuccess";
	}
	public String update() throws IOException{
		
		product.setPdate(new Date());
		if(upload!=null){
			String path=product.getImage();
			File file=new File(ServletActionContext.getServletContext()
					.getRealPath("/")+path);
			file.delete();
			// 获得文件上传的磁盘绝对路径
						String realPath = ServletActionContext.getServletContext()
								.getRealPath("/products");
						// 创建一个文件，代表磁盘上的一个文件
						File diskFile = new File(realPath + "//" + uploadFileName);
						// 上传文件
						FileUtils.copyFile(upload, diskFile);
						product.setImage("products/" + uploadFileName);
		}
		productService.update(product);
		return "updateSuccess";
		
		
	}

}
