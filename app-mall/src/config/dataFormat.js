//汽车商品规格
export const goodsSpec = function (spec) {
  if (spec.length > 0){
    let result = '';
    let specList = spec.split('&');
    specList.forEach(spec=>{
      result += spec.substring(spec.indexOf('：') + 1,spec.length) + " ";
    });
    return result;
  } else {
    return spec;
  }

};

/**
 * 元转万元
 * @param price
 * @param showZore 是否显示0
 * @returns {string}
 */
export const tranPrice = (price,showZore = false) => {
  let dbPrice = parseInt(price) + '';
  let result = '';
  if (dbPrice.length <= 4) {
    result = "0." + dbPrice.substring(0,dbPrice.length-2);
  }else {
    result =  dbPrice.substring(0,dbPrice.length-4) + '.' + dbPrice.substring(dbPrice.length-4,dbPrice.length-2);
  }
  if (!showZore){
    if (result.indexOf('.00')) {
      result = result.split(".00")[0];
    }else if (result.lastIndexOf('0') == result.length - 1) {
      result = result.substring(0,result.length-1);
    }

    if (result.lastIndexOf('.') == result.length - 1) {
      result = result.substring(0,result.length-1);
    }
  }
  return result;
};

/**
 * 万元转元
 * @param price
 * @returns {string}
 */
export const tranThouOfPrice = (price) => {
  let result = '';
  let splice = price.split(".");
  if (splice[1]){
    let length = splice[1].length;
    if (length >= 4) {
      splice[1] = splice[1].substring(0,4);
    } else {
      for (let i = length;i<4;i++){
        splice[1] = splice[1]+'0';
      }
    }
    result = splice[0] + splice[1];
  }else {
    result = splice[0] + '0000';
  }
  return result;
};

//订单状态转换
export const orderState = state => {
  switch (state) {
    case "PENDING_PAYMENT":
      return "待付款";
    case "PAID_PAYMENT":
      return "已付款";
    case "OUT_STOCK":
      return "待提车";
    case "RECEIVED":
      return "已提车";
    case "REFUSE":
      return "拒签收";
    case "RETURN_GOODS":
      return "退货";
    case "CHANGE_GOODS":
      return "换货";
    case "CANCELED":
      return "已取消";
    case "TIMEOUT":
      return "超时";
    case "WAIT_EVALUATE":
      return "待评价";
    case "EVALUATED":
      return "已评价";
    case "SUCCESS":
      return "交易成功";
  }
};

//支付方式转换
export const payType = type => {
  switch (type) {
    case "FULL_SECTION":
      return "全款购车";
    case "LOAN":
      return "贷款购车";
    case "ALIPAY":
      return "支付宝";
    case "TENPAY":
      return "财付通";
    case "UNIONPAY":
      return "银联";
    case "AFTERREVICED":
      return "货到付款";
  }
};
