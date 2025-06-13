<template>
  <div class="login-page">
    <NavBar />

    <!-- 登录表单 -->
    <div class="login-container">
      <h2>用户登录</h2>
      <form @submit.prevent="handleLogin">
        <label for="username">用户名</label>
        <input type="text" id="username" v-model="form.username" required />

        <label for="password">密码</label>
        <input type="password" id="password" v-model="form.password" required />

        <div class="captcha-container">
          <label for="captcha">验证码</label>
          <div class="captcha-input-container">
            <input type="text" id="captcha" v-model="form.captcha" required />
            <img 
              :src="captchaUrl" 
              alt="验证码" 
              class="captcha-img" 
              @click="refreshCaptcha"
              title="点击刷新验证码"
            />
          </div>
        </div>

        <button type="submit">登录</button>
        <div class="register-link">
          <span>没有账号？</span>
          <router-link to="/register">立即注册</router-link>
        </div>
      </form>
    </div>

    <!-- 页脚 -->
    <div class="footer">
      &copy; 2025 MindCapturer. All rights reserved.
    </div>
  </div>
</template>

<script>
import NavBar from "../components/Navbar.vue";
import { reactive, ref, onMounted, getCurrentInstance } from "vue";
import { useRouter } from "vue-router";

export default {
  name: "Login",
  components: { NavBar },
  setup() {
    const { proxy } = getCurrentInstance();
    const router = useRouter();
    const form = reactive({
      username: "",
      password: "",
      captcha: ""
    });
    
    // 验证码相关
    const captchaId = ref('');
    const captchaUrl = ref('');
    
    // 刷新验证码
    function refreshCaptcha() {
      proxy.$request.get('/api/captcha/generate')
        .then(res => {
          captchaId.value = res.data.captchaId;
          captchaUrl.value = `/api/captcha/image?id=${captchaId.value}&t=${new Date().getTime()}`;
        })
        .catch(error => {
          console.error('获取验证码错误:', error);
          alert('获取验证码失败，请刷新页面重试');
        });
    }

    async function handleLogin() {
      try {
        if (!form.username || !form.password || !form.captcha) {
          alert('请填写完整的登录信息');
          return;
        }
        
        const res = await proxy.$request.post('/api/auth/login', {
          username: form.username,
          password: form.password,
          captcha: form.captcha
        }, {
          params: {
            captchaId: captchaId.value
          }
        });
        
        // 登录成功，保存token和用户信息
        const userData = res.data;
        localStorage.setItem('token', userData.token);
        localStorage.setItem('userInfo', JSON.stringify(userData));
        
        // 设置axios默认请求头
        proxy.$axios.defaults.headers.common['Authorization'] = `Bearer ${userData.token}`;
        
        // 跳转到首页
        router.push('/');
      } catch (error) {
        console.error('登录错误:', error);
        if (error.message) {
          alert(error.message);
        } else {
          alert('登录失败，请稍后重试');
        }
        refreshCaptcha(); // 刷新验证码
      }
    }
    
    // 组件挂载时获取验证码
    onMounted(() => {
      refreshCaptcha();
    });

    return { 
      form, 
      handleLogin, 
      captchaUrl, 
      refreshCaptcha 
    };
  },
};
</script>

<style scoped>
.login-page {
  padding-top: 60px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  background: linear-gradient(135deg, #e0f3ff, #f5f9ff);
  min-height: 100vh;
}

.login-container {
  max-width: 420px;
  margin: 120px auto 60px;
  background: #ffffff;
  padding: 40px 30px;
  border: 1px solid #d9e4ec;
  border-radius: 10px;
  box-shadow: 0 6px 18px rgba(0, 0, 0, 0.05);
}

.login-container h2 {
  text-align: center;
  color: #003366;
  font-size: 22px;
  font-weight: 700;
  margin-bottom: 30px;
}

.login-container label {
  font-size: 14px;
  font-weight: 600;
  display: block;
  margin-bottom: 6px;
}

.captcha-container {
  margin-bottom: 20px;
}

.captcha-input-container {
  display: flex;
  align-items: center;
  gap: 10px;
}

.captcha-img {
  height: 38px;
  border: 1px solid #d9e4ec;
  border-radius: 4px;
  cursor: pointer;
}

.register-link {
  text-align: center;
  margin-top: 15px;
  font-size: 14px;
}

.register-link a {
  color: #0066cc;
  text-decoration: none;
  margin-left: 5px;
}

.login-container input[type="text"],
.login-container input[type="password"] {
  width: 100%;
  padding: 10px 8px;
  border: none;
  border-bottom: 2px solid #ccc;
  outline: none;
  transition: border-color 0.3s;
  font-size: 14px;
  background-color: #fafafa;
}

.login-container input[type="text"]:focus,
.login-container input[type="password"]:focus {
  border-color: #0071c5;
  background-color: #fff;
}

.login-container button {
  width: 100%;
  margin-top: 25px;
  padding: 12px;
  background-color: transparent;
  border: 2px solid #0071c5;
  color: #0071c5;
  font-weight: bold;
  font-size: 15px;
  border-radius: 6px;
  cursor: pointer;
  transition: background-color 0.3s, color 0.3s;
}

.login-container button:hover {
  background-color: #0071c5;
  color: #fff;
}

.footer {
  margin-top: 60px;
  padding: 20px;
  background: #e6f2ff;
  text-align: center;
  font-size: 14px;
  color: #444;
}
</style>
