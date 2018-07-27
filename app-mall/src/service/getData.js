import {post,get,patch,put} from '../config/http'

export const getIndex = function () {
  return get('/v1/mall/index/getIndex.do');
};


