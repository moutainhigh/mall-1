/**
 * 配置编译环境和线上环境之间的切换
 *
 * baseUrl: 域名地址
 * imgBaseUrl: 图片所在域名地址
 *
 */

// let baseUrl = 'http://119.23.59.102:8158/api';
let baseUrl = 'http://192.168.0.43:8158/api';
let imgBaseUrl = '';
let setBase = function (url) {
  baseUrl = url;
}

export {
	baseUrl,
	imgBaseUrl,
  setBase
}
