import {post, get, patch, put} from '../config/http'
import fetch from '../config/fetch'

export const getIndex = function () {
  return get('/v1/mall/index/getIndex.do');
};

//获取用户收货地址列表
export const getDeliveryAddress = function () {
  return get('/v1/mall/deliveryAddress/list.do');
};

export const saveDeliveryAddress = function (addressVo) {
  // return post('/v1/mall/deliveryAddress.do', addressVo);
  return fetch('/v1/mall/deliveryAddress.do', addressVo,"POST");
};

