import {helpers} from 'vuelidate/lib/validators'

//身份证正则校验
export const idCardVali = helpers.regex('idCardVali', /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/);
//居民户口蒲 9位数
export const householdVali = helpers.regex('householdVali', /^\d{9}$/);
//出生证
export const birthVali = helpers.regex('birthVali', /[A-Z]{1}\d{9}/);
//港澳通行证
export const hkmcPassVali = helpers.regex('hkmcPassVali', /^[HMhm]{1}([0-9]{10}|[0-9]{8})$/);
// 台湾通行证
export const taiwanPassVali = helpers.regex('taiwanPassVali', /^[0-9]{8}$/);
//护照
export const passportVali = helpers.regex('passportVali', /^(P\d{7}|G\d{7,8}|TH\d{7,8}|S\d{7,8}|A\d{7,8}|L\d{7,8}|\d{9}|D\d+|1[4,5]\d{7})$/);
//外国人永久居留证
export const permanentResidenceVali = helpers.regex('permanentResidenceVali', /^[a-zA-Z]{3}\d{12}$/);
//小数点后两位校验
export const int = helpers.regex('int', /^[1-9]\d*$/);
//固定电话校验
export const fixedTel = helpers.regex('fixedTel', /([0-9]{3,4}-)?[0-9]{7,8}/);
//手机号码校验
export const mobile = helpers.regex('mobile', /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/);
//邮箱校验
export const mail = helpers.regex('mail', /^([A-Za-z0-9_\-\.\u4e00-\u9fa5])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,8})$/);
