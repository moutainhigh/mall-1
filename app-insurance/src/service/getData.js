import fetch from '../config/fetch'
import {wipeArray} from '../config/mUtils'
import storage from '../store/storage'

/**
 * 获取首页默认地址
 */

export const getVaildData = phoneNumber => fetch('/noAuth/sendMobileValidCode/ORDER_CONFIRM/'+13670150690,{},'POST');

export const sendMobile = (sendData, captcha_code, type, password) => fetch('/test', {
  captcha_code,
  [type]: sendData,
  way: type,
  password,
}, 'POST');

export const submitOrder = function () {
  let insured = wipeArray(storage.fetch("insured"));
  let holder = wipeArray(storage.fetch("holder"));
  let insuranceOrder = wipeArray(storage.fetch("order"));
  insuranceOrder.insuranceOrderPolicyholderBank = wipeArray(insuranceOrder.insuranceOrderPolicyholderBank );
  let beneficiaries = storage.fetch('beneficiaries');
  insuranceOrder.insuranceOrderPolicyholder = holder;
  insuranceOrder.insuranceOrderInsured = insured;
  insuranceOrder.insuranceOrderBeneficiarys = beneficiaries;
  return fetch('/insurance/order/saveOrder',insuranceOrder,"POST");
};
