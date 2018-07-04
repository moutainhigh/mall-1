import fetch from '../config/fetch'
import {getStore} from '../config/mUtils'

/**
 * 获取首页默认地址
 */

export const testGet = () => fetch('/test', {
});

export const sendMobile = (sendData, captcha_code, type, password) => fetch('/test', {
  captcha_code,
  [type]: sendData,
  way: type,
  password,
}, 'POST');
