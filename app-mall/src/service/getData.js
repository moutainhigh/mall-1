import {post,get,patch,put} from '../config/http'
import fetch from '../config/fetch'

//首页获取数据
export const getIndex = function () {
  return get('/v1/mall/index/getIndex.do');
};

//获取收藏夹
export const getCustomerFavorite = function (query) {
  return post('/v1/mall/favorite/getCustomerFavorite.do',{},query);
};

//分类搜索
export const categorySearch = function (searchVo, page, size) {
  return post('/mall/search/categorySearch/'+page + '/' + size,searchVo)
}

