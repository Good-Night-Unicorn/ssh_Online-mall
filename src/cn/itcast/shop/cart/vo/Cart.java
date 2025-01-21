package cn.itcast.shop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车对象
 * @author Kang
 *
 */
public class Cart implements Serializable{
	//需要有一个购物项的集合，因为要对数据进行相关操作
	//所以选取map键值对形式存在的集合
	//而在显示的时候单链查询更简单，map里有方法map.values()
	private Map<String,CartItem> map=new LinkedHashMap<String, CartItem>();
	
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	
	
	//购物总计
	private double total;
	
	public double getTotal() {
		return total;
	}


	


	//购物车的功能（方法）
	//添加，删除，清空
	public void addCart(CartItem cartItem){
		//判断购物项是否已经存在
		String pid=cartItem.getProduct().getPid();
		//map中有一个方法是用来判断是否包含某个key
		if(map.containsKey(pid)){
			CartItem _cartItem=map.get(pid);//获得原有的购物项
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else{
			map.put(pid, cartItem);
		}
		//无论购物项是否存在，总计都要增加，故放到判断语句之外
		total+=cartItem.getSubtotal();
	}
	
	
	public void removeCart(String pid){
		//将购物项移除购物车
		//总计要减去移除的小计
		CartItem cartItem=map.remove(pid);
		total-=cartItem.getSubtotal();
		
	}
	
	public void clearCart(){
		//清空所有购物项 map集合清空
		map.clear();
		//总计设置为0
		total=0;
	}
}
