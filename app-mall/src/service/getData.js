import {post, get, patch, put, del} from '../config/http'
import fetch from '../config/fetch'

//首页获取数据
export const getIndex = function () {
  return get('/v1/mall/index/getIndex.do');
};

//获取收藏夹
export const getCustomerFavorite = function (query) {
  return post('/v1/mall/favorite/getCustomerFavorite.do',query);
}

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

