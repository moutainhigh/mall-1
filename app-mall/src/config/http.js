import axios from 'axios';
import {baseUrl} from "./env";
import storage from "../store/storage";

axios.defaults.timeout = 5000;
axios.defaults.baseURL ='';


//http request 拦截器
axios.interceptors.request.use(
  config => {
    // const token = getCookie('名称');注意使用的时候需要引入cookie方法，推荐js-cookie
    config.headers = {
      'Content-Type':'application/json',
      'Accept': 'application/json',
      'Authorization': 'Bearer ' + storage.fetchSession('token'),
    };
    config.data = JSON.stringify(config.data);
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);


//http response 拦截器
axios.interceptors.response.use(
  response => {
    var status = response.status;
    if (status == 502){

    }
    if (status == 400){

    }
    if (status == 401){

    }
    if (status == 500){

    }
    if(response.data.errCode ==2){
      // this.$router.push({
      //   path:"/login",
      //   query:{redirect:router.currentRoute.fullPath}//从哪个页面跳转
      // })
    }
    return response;
  },
  error => {
    return Promise.reject(error)
  }
)


/**
 * 封装get方法
 * @param url
 * @param params
 * @returns {Promise}
 */

export function get(url,params={}){
  url = baseUrl + url;
  return new Promise((resolve,reject) => {
    axios.get(url,{
      params:params
    })
      .then(response => {
        resolve(response.data);
      })
      .catch(err => {
        reject(err)
      })
  })
}


/**
 * 封装post请求
 * @param url
 * @param data
 * @param header
 * @returns {Promise}
 */

export function post(url,data = {},header = {}){
  url = baseUrl + url;
  return new Promise((resolve,reject) => {
    axios.post(url,data,header)
      .then(response => {
        resolve(response.data);
      },err => {
        reject(err)
      })
  })
}

/**
 * 封装patch请求
 * @param url
 * @param data
 * @returns {Promise}
 */

export function patch(url,data = {}){
  url = baseUrl + url;
  return new Promise((resolve,reject) => {
    axios.patch(url,data)
      .then(response => {
        resolve(response.data);
      },err => {
        reject(err)
      })
  })
}

/**
 * 封装put请求
 * @param url
 * @param data
 * @returns {Promise}
 */

export function put(url,data = {}){
  url = baseUrl + url;
  return new Promise((resolve,reject) => {
    axios.put(url,data)
      .then(response => {
        resolve(response.data);
      },err => {
        reject(err)
      })
  })
}

/**
 * 封装delete请求
 * @param url
 * @param data
 * @returns {Promise}
 */

export function del(url,data = {}){
  url = baseUrl + url;
  return new Promise((resolve,reject) => {
    axios.delete(url,data)
      .then(response => {
        resolve(response.data);
      },err => {
        reject(err)
      })
  })
}
