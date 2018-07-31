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
