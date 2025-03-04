<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>我的订单页面</title>
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/cart.css"
	rel="stylesheet" type="text/css" />

</head>
<body>

	<div class="container header">
		<div class="span5">
			<div class="logo">
				<a href="./网上商城/index.htm"> <img
					src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.jpg"
					alt="满天星" /> </a>
			</div>
		</div>
		<div class="span9">
			<div class="headerAd">
				<img src="${pageContext.request.contextPath}/image/header.jpg"
					width="320" height="50" alt="正品保障" title="正品保障" />
			</div>
		</div>
		<%@ include file="menu.jsp"%>




	</div>

	<div class="container cart">

		<div class="span24">

			<div class="step step1">
				<ul>

					<li class="current"></li>
					<li>我的订单</li>
				</ul>
			</div>


			<table>
				<tbody>
					<!-- 每一个订单的迭代 -->
					<s:iterator var="order" value="pageBean.list">
						<tr>
							<th colspan="5">订单编号：<s:property value="#order.oid" />&nbsp;&nbsp;&nbsp;
								订单状态： <s:if test="#order.state==1">
									<a href="${pageContext.request.contextPath }/order_findByOid.action?oid=<s:property value="#order.oid" />"><font color="red">未付款</font>
									</a>
								</s:if> <s:if test="#order.state==2">
							已付款
						</s:if> <s:if test="#order.state==3">
									<a href="${pageContext.request.contextPath }/order_updateState.action?oid=<s:property value="#order.oid" />"><font color="red">确认收货</font>
									</a>
								</s:if> <s:if test="#order.state==4">
							交易完成
						</s:if></th>

						</tr>
						<!-- 每一个订单项的迭代 -->
						
							<tr>
								<th>图片</th>
								<th>商品</th>
								<th>价格</th>
								<th>数量</th>
								<th>小计</th>
							</tr>
							<s:iterator var="orderItem" value="#order.orderItems">
							<tr>
								<td width="60"><input type="hidden" name="id" value="22" />
									<img
									src="${pageContext.request.contextPath }/<s:property value="#orderItem.product.image"/>" />
								</td>
								<td><a target="_blank"><s:property
											value="#orderItem.product.pname" />
								</a></td>
								<td><s:property value="#orderItem.product.shop_price" /></td>
								<td class="quantity" width="60"><s:property
										value="#orderItem.count" /></td>
								<td width="140"><span class="subtotal">￥<s:property
											value="#orderItem.subtotal" />
								</span></td>

							</tr>
						</s:iterator>
						</s:iterator>
						<tr>
							<td colspan="5">
								<div class="pagination">
									<span>第<s:property value="pageBean.page" />/<s:property
											value="pageBean.totalPage" />页</span>
										<s:if test="pageBean.page!=1">
											<a
												href="${pageContext.request.contextPath }/order_findByUid.action?page=1"
												class="firstPage">&nbsp;</a>
											<a
												href="${pageContext.request.contextPath }/order_findByUid.action?page=<s:property value="pageBean.page-1"/>"
												class="previousPage">&nbsp;</a>
										</s:if>
										<s:iterator var="i" begin="1" end="pageBean.totalPage">
											<s:if test="pageBean.page!=#i">
												<a
													href="${pageContext.request.contextPath }/order_findByUid.action?page=<s:property value="#i"/>"><s:property
														value="#i" />
												</a>
											</s:if>
											<s:else>
												<span class="currentPage"><s:property value="#i" />
												</span>
											</s:else>
										</s:iterator>
										<s:if test="pageBean.page!=pageBean.totalPage">
											<a class="nextPage"
												href="${pageContext.request.contextPath }/order_findByUid.action?page=<s:property value="pageBean.page+1"/>">&nbsp;</a>

											<a class="lastPage"
												href="${pageContext.request.contextPath }/order_findByUid.action?page=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
										</s:if>
									


									
									
									
								</div></td>

						</tr>
					
				</tbody>
			</table>

		</div>

	</div>
	<%@ include file="bottom.jsp" %> 
</body>
</html>