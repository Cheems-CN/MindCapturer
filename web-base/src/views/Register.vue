<template>
  <div class="register-page">
    <NavBar />

    <!-- 注册表单 -->
    <div class="register-container">
      <h2>用户注册</h2>
      <form @submit.prevent="handleRegister">
        <label for="username">用户名</label>
        <input type="text" id="username" v-model="form.username" required />

        <label for="nickname">昵称</label>
        <input type="text" id="nickname" v-model="form.nickname" required />

        <label for="email">邮箱</label>
        <input type="email" id="email" v-model="form.email" required />

        <label for="phone">手机号</label>
        <input
            type="tel"
            id="phone"
            v-model="form.phone"
            pattern="[0-9]{11}"
            required
            placeholder="例如：13812345678"
        />

        <label for="password">密码</label>
        <input
            type="password"
            id="password"
            v-model="form.password"
            required
        />

        <label for="confirmPassword">确认密码</label>
        <input
            type="password"
            id="confirmPassword"
            v-model="form.confirmPassword"
            required
        />

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

        <div v-if="!passwordsMatch" class="error-message">
          两次输入的密码不一致
        </div>

        <button type="submit" :disabled="!passwordsMatch">
          注册
        </button>
        <div class="login-link">
          <span>已有账号？</span>
          <router-link to="/login">立即登录</router-link>
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
import { reactive, computed, ref, onMounted, getCurrentInstance } from "vue";
import { useRouter } from "vue-router";

export default {
  name: "Register",
  components: { NavBar },
  setup() {
    const { proxy } = getCurrentInstance();
    const router = useRouter();
    const form = reactive({
      username: "",
      nickname: "",
      email: "",
      phone: "",
      password: "",
      confirmPassword: "",
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

    const passwordsMatch = computed(() => {
      return form.password === form.confirmPassword;
    });

    async function handleRegister() {
      if (!passwordsMatch.value) {
        alert("两次输入的密码不一致");
        return;
      }

      // 表单验证
      if (!form.username || !form.nickname || !form.email || !form.phone || 
          !form.password || !form.confirmPassword || !form.captcha) {
        alert("请填写完整的注册信息");
        return;
      }

      try {
        await proxy.$request.post('/api/auth/register', {
          username: form.username,
          nickname: form.nickname,
          email: form.email,
          phone: form.phone,
          password: form.password,
          confirmPassword: form.confirmPassword,
          captcha: form.captcha
        }, {
          params: {
            captchaId: captchaId.value
          }
        });

        alert("注册成功，请登录");
        router.push('/login');
      } catch (error) {
        console.error("注册错误:", error);
        if (error.message) {
          alert(error.message);
        } else {
          alert("注册失败，请稍后重试");
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
      passwordsMatch, 
      handleRegister, 
      captchaUrl, 
      refreshCaptcha 
    };
  },
};
</script>

<style scoped>
.register-page {
  padding-top: 60px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  background: linear-gradient(135deg, #e0f3ff, #f5f9ff);
  min-height: 100vh;
}

.register-container {
  max-width: 500px;
  margin: 120px auto 60px;
  background: #ffffff;
  padding: 40px 30px;
  border: 1px solid #d9e4ec;
  border-radius: 10px;
  box-shadow: 0 6px 18px rgba(0, 0, 0, 0.05);
}

.register-container h2 {
  text-align: center;
  color: #003366;
  font-size: 22px;
  font-weight: 700;
  margin-bottom: 30px;
}

.register-container label {
  font-size: 14px;
  font-weight: 600;
  margin: 12px 0 4px;
  display: block;
  color: #444;
}

.register-container input[type="text"],
.register-container input[type="email"],
.register-container input[type="tel"],
.register-container input[type="password"] {
  width: 100%;
  padding: 10px 8px;
  border: none;
  border-bottom: 2px solid #ccc;
  outline: none;
  font-size: 14px;
  background-color: #fafafa;
  transition: border-color 0.3s;
}

.register-container input:focus {
  border-bottom-color: #0071c5;
  background-color: #fff;
}

.register-container button {
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

.register-container button:hover {
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
