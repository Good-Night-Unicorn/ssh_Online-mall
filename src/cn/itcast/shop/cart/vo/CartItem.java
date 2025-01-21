package cn.itcast.shop.cart.vo;

import cn.itcast.shop.product.vo.Product;

/**
 * 购物项对象
 * @author Kang
 *
 */
public class CartItem {
	private Product product;//购买商品的信息
	private int count;//数量
	private double subtotal;//小计
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	//小计实现自动计算
	public double getSubtotal() {
		return count*product.getShop_price();
	}
	
	
}
