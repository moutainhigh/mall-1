import {helpers} from 'vuelidate/lib/validators'

//身份证正则校验
export const idCardVali = helpers.regex('idCardVali', /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/);
//小数点后两位校验
export const int = helpers.regex('int', /^[1-9]\d*$/);
//固定电话校验
export const fixedTel = helpers.regex('fixedTel', /([0-9]{3,4}-)?[0-9]{7,8}/);
//手机号码校验
export const mobile = helpers.regex('mobile', /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/);
