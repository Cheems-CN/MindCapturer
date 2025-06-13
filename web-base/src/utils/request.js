import axios from 'axios';
import router from '../router';

// 创建axios实例
const request = axios.create({
  baseURL: '', // 如果有基础URL，可以在这里设置
  timeout: 10000 // 请求超时时间
});

// 请求拦截器
request.interceptors.request.use(
  config => {
    // 从localStorage获取token
    const token = localStorage.getItem('token');
    if (token) {
      // 设置请求头Authorization字段
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
  },
  error => {
    console.error('请求错误:', error);
    return Promise.reject(error);
  }
);

// 响应拦截器
request.interceptors.response.use(
  response => {
    const res = response.data;
    
    // 如果返回的状态码不是0，说明接口出错了
    if (res.code !== 0) {
      console.error('接口错误:', res.message || '未知错误');
      
      // 如果是未认证或token过期，跳转到登录页
      if (res.code === 401) {
        // 清除本地token
        localStorage.removeItem('token');
        localStorage.removeItem('userInfo');
        
        // 跳转到登录页
        router.push('/login');
      }
      
      return Promise.reject(new Error(res.message || '未知错误'));
    }
    
    return res;
  },
  error => {
    console.error('响应错误:', error);
    
    // 如果是网络错误或服务器错误
    if (error.response) {
      // 如果是未认证或token过期
      if (error.response.status === 401) {
        // 清除本地token
        localStorage.removeItem('token');
        localStorage.removeItem('userInfo');
        
        // 跳转到登录页
        router.push('/login');
      }
    }
    
    return Promise.reject(error);
  }
);

export default request;