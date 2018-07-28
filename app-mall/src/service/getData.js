import {post,get,patch,put} from '../config/http'

//首页获取数据
export const getIndex = function () {
  return get('/v1/mall/index/getIndex.do');
};

//获取收藏夹
export const getCustomerFavorite = function (query) {
  return post('/v1/mall/favorite/getCustomerFavorite.do',JSON.stringify(query));
}

