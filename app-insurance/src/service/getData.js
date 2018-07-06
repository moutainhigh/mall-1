import fetch from '../config/fetch'
import {getStore} from '../config/mUtils'

/**
 * 获取首页默认地址
 */

export const getVaildData = phoneNumber => fetch('/noAuth/sendMobileValidCode', {
  sendType : 'BANK_VALID',
  mobile : 13670150690
},'POST');

export const sendMobile = (sendData, captcha_code, type, password) => fetch('/test', {
  captcha_code,
  [type]: sendData,
  way: type,
  password,
}, 'POST');
