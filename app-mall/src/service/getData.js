import {post,fetch,patch,put} from '../config/http'

export const test = function () {
  return post('test/test/',{test:test},{});
}
