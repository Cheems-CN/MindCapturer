import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "axios";
import request from "./utils/request";

// 设置axios默认值
axios.defaults.baseURL = '';

// 从localStorage获取token并设置默认请求头
const token = localStorage.getItem('token');
if (token) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
}

const app = createApp(App);

// 全局属性
app.config.globalProperties.$axios = axios;
app.config.globalProperties.$request = request;

// 挂载应用
app.use(router).mount("#app");
