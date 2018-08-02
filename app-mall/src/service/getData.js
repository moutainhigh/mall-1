import {post,get,patch,put} from '../config/http'

//首页获取数据
export const getIndex = function () {
  return get('/v1/mall/index/getIndex.do');
};

//获取收藏夹
export const getCustomerFavorite = function (query) {
  return post('/v1/mall/favorite/getCustomerFavorite.do',{},query);
};

//分类搜索
export const categorySearch = function (searchVo) {
  return post('/v1/mall/search/categorySearch',searchVo)
}

//############## 地址接口 ##################//
//获取用户收货地址列表
export const getDeliveryAddress = function () {
  return get('/v1/mall/deliveryAddress/list.do');
};

export const saveDeliveryAddress = function (addressVo) {
  return post('/v1/mall/deliveryAddress.do', addressVo);
};

export const getDeliveryAddressByAdderssId = function (addressId) {
  return get('/v1/mall/deliveryAddress/' + addressId);
};

export const updateDeliveryAddress = function (addressVo) {
  return put('/v1/mall/deliveryAddress/' + addressVo.addressId, addressVo);
};

export const deleteDeliveryAddressByAdderssId = function (addressId) {
  return del('/v1/mall/deliveryAddress/' + addressId);
};

//################## 订单接口 ##########################//

//确认订单接口
export const getTempOrder = function (productId, paymentType) {
  return post('/v1/mall/order/tempOrder',{},{
    productId:productId,
    paymentType:paymentType
  });
};

//下单接口
export const addOrder = function (orderConfirmVO) {
  return post('/v1/mall/order',orderConfirmVO);
}

//获取用户商品订单列表
export const getCustomerOrder = function (query) {
  return post('/v1/mall/order/pageList',{},query);
};

//获取订单详情
export const getOrderDetailById = orderId =>{
  return get('/v1/mall/order/'+orderId);
};

//订单收货成功
export const confirmOrder = orderId =>{
  return put('/v1/mall/order/confirmOrder/'+orderId);
};

//订单取消
export const cancelOrder = orderId =>{
  return put('/v1/mall/order/cancelOrder/'+orderId);
};
