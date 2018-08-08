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

//保险期间转换
export const insurePeriod = period => {
  switch (period) {
    case "TEN_YEAR":
      return "10年";
    case "TWENTY_YEAR":
      return "20年";
    case "LIFITIME":
      return "终生";
  }
};

//保险年限转换
export const protectionYear = year => {
  switch (year) {
    case "TEN_YEAR":
      return "10年";
    case "TWENTY_YEAR":
      return "20年";
    case "LIFITIME":
      return "终生";
  }
};
