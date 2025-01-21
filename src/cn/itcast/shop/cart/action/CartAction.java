package cn.itcast.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.cart.vo.Cart;
import cn.itcast.shop.cart.vo.CartItem;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 购物车的action
 * @author Kang
 *
 */
public class CartAction extends ActionSupport{
	//接收pid
	private String pid;
	//接收count
	private Integer count;
	//注入商品的Service
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	//将购物项添加到购物车中：执行的方法
	public String addCart(){
		//封装一个CartIItem对象
		CartItem cartItem=new CartItem();
		//设置数量
		
		cartItem.setCount(count);
		//根据pid进行查询商品
		Product product=productService.findByPid(pid);
		//设置商品
		cartItem.setProduct(product);
		//将购物项添加购物车
		//购物车存在session
		Cart cart=getCart();
		cart.addCart(cartItem);
		return "addCart";
	}
	//清空购物车
	public String clearCart(){
		//获得购物车对象，调用清空的方法
		Cart cart=getCart();
		cart.clearCart();
		return "clearCart";
	}
	//从购物车中移除购物项
	public String removeCart(){
		//获得购物车对象
		Cart cart=getCart();
		cart.removeCart(pid);
		return "removeCart";
	}
	//我的购物车：执行的方法
	public String myCart(){
		
		return "myCart";
	}
	/**
	 * 获取购物车，获得的方法，这个和执行的方法，有区别么？？？
	 * @return
	 */
	private Cart getCart() {
		// 从session中获得购物车
		Cart cart=(Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart==null){
			cart=new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
}
