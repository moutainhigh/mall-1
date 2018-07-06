import fetch from '../config/fetch'
import {wipeArray} from '../config/mUtils'
import storage from '../store/storage'

/**
 * 获取首页默认地址
 */

export const getVaildData = phoneNumber => fetch('/noAuth/sendMobileValidCode/ORDER_CONFIRM/'+phoneNumber,{},'POST');

export const submitOrder = function (code) {
  let insured = wipeArray(storage.fetch("insured"));
  let holder = wipeArray(storage.fetch("holder"));
  let insuranceOrder = wipeArray(storage.fetch("order"));
  let matters = storage.fetch("matters");
  insuranceOrder.insuranceOrderPolicyholderBank = wipeArray(insuranceOrder.insuranceOrderPolicyholderBank );
  let beneficiaries = storage.fetch('beneficiaries');
  insuranceOrder.insuranceOrderPolicyholder = holder;
  insuranceOrder.insuranceOrderInsured = insured;
  insuranceOrder.insuranceOrderBeneficiarys = beneficiaries;
  insuranceOrder.insuranceOrderInformedMatters = matters;
  return fetch('/insurance/order/saveOrder?code='+ code, insuranceOrder,"POST");
};

export const uploadImage = function (base64) {
  return fetch('/common/file/uploadBase64/ PAPERWORK', base64, 'POST','fetch');
};
