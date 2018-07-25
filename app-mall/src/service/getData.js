import {post,get,patch,put} from '../config/http'

export const getIndex = function () {
  return get('/mall/index/getIndex.do');
};


