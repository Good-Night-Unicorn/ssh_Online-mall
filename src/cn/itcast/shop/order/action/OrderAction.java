package cn.itcast.shop.order.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.cart.vo.Cart;
import cn.itcast.shop.cart.vo.CartItem;
import cn.itcast.shop.order.service.OrderService;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.utils.PageBean;
import cn.itcast.shop.utils.PaymentUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 订单管理的action
 * 
 * @author Kang
 * 
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {
	// 模型驱动使用的对象
	private Order order = new Order();
	// 注入OrderService
	private OrderService orderService;
	// 接收page这个参数
	private Integer page;
	//接收支付通道编码：
	private String pd_FrpId;
	//接收付款后的响应信息
	private String r6_Order;
	private String r3_Amt;
	

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public Order getModel() {

		return order;
	}

	// 生成订单的方法
	public String save() {
		// 保存订单到数据库
		// 订单数据的补全操作
		order.setOrdertime(new Date());
		order.setState(1);// 1：未付款 2：已付款，未发货 3：发货，未收货 4：交易完成
		// 总计中的数据要从购物车中提取
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		if (cart == null) {
			this.addActionError("未购物，无法完成订单提交，请先购物！！！");
			return "msg";
		}
		order.setTotal(cart.getTotal());

		// 设置订单中的订单项 订单项中的数据需要从购物项中取值
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);

			order.getOrderItems().add(orderItem);

		}
		// 订单所属的用户
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if (existUser == null) {
			this.addActionError("未登录，请先去登录！！！");
			return "login";
		}
		order.setUser(existUser);
		orderService.save(order);
		// 将订单显示到页面中去
		// 通过值栈的方式进行显示：因为order显示的对象就是模型驱动使用的对象
		//订单保存完成以后，清空购物车
		cart.clearCart();
		return "saveSuccess";
	}

	// 订单查询
	public String findByUid() {
		// 根据用户的id查询
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		// service
		PageBean<Order> pageBean = orderService.findByPageUid(user.getUid(),
				page);
		//分页数据显示到页面上，通过值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}
	//根据订单ID查询订单
	public String findByOid(){
		order=orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}
	//为订单付款方法
	public String payOrder() throws IOException{
		//补全订单
		Order currOrder=orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		orderService.update(currOrder);
		//为订单付款
		String p0_Cmd="Buy";//业务类型
		String p1_MerId="10001126856";//商户编号
		String p2_Order=order.getOid().toString();//订单编号
		String p3_Amt="0.01";//支付金额
		String p4_Cur="CNY";//交易币种
		String p5_Pid="";//商品名称
		String p6_Pcat="";//商品种类
		String p7_Pdesc="";//商品描述
		String p8_Url="http://localhost:8080/shop/order_callBack.action";//商户接收支付成功数据的地址
		String p9_SAF="";//送货地址
		String pa_MP="";//商户扩展信息
		String pd_FrpId=this.pd_FrpId;// 支付通道编码
		String pr_NeedResponse ="1";//应答机制
		String keyValue="69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";//秘钥
		String hmac=PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);//hmac码
		
		//向易宝传递参数
		StringBuffer stringBuffer=new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		stringBuffer.append("p0_Cmd=").append(p0_Cmd).append("&");
		stringBuffer.append("p1_MerId=").append(p1_MerId).append("&");
		stringBuffer.append("p2_Order=").append(p2_Order).append("&");
		stringBuffer.append("p3_Amt=").append(p3_Amt).append("&");
		stringBuffer.append("p4_Cur=").append(p4_Cur).append("&");
		stringBuffer.append("p5_Pid=").append(p5_Pid).append("&");
		stringBuffer.append("p6_Pcat=").append(p6_Pcat).append("&");
		stringBuffer.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		stringBuffer.append("p8_Url=").append(p8_Url).append("&");
		stringBuffer.append("p9_SAF=").append(p9_SAF).append("&");
		stringBuffer.append("pa_MP=").append(pa_MP).append("&");
		stringBuffer.append("pd_FrpId=").append(pd_FrpId).append("&");
		stringBuffer.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		stringBuffer.append("keyValue=").append(keyValue).append("&");
		stringBuffer.append("hmac=").append(hmac);
		
		//重定向到易宝
		ServletActionContext.getResponse().sendRedirect(stringBuffer.toString());
		return NONE;
	}
	public String callBack(){
		//修改订单状态 已经付款
		Order currOrder =orderService.findByOid(r6_Order);
		currOrder.setState(2);
		orderService.update(currOrder);
		this.addActionMessage("订单付款成功："+r6_Order+"付款金额为："+r3_Amt);
		return "msg";
	}
	public String updateState(){
		
		Order currOrder=orderService.findByOid(order.getOid());
		currOrder.setState(4);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
}
