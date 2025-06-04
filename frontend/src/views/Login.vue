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

        <button type="submit">登录</button>
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
import { reactive } from "vue";

export default {
  name: "Login",
  components: { NavBar },
  setup() {
    const form = reactive({
      username: "",
      password: "",
    });

    function handleLogin() {
      // TODO：在这里对接后端登陆请求，例如：
      // axios.post('/api/login', form).then(...)
      console.log("登录信息：", form);
      alert(`尝试登录：${form.username}`);
    }

    return { form, handleLogin };
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
  margin: 10px 0 4px;
  display: block;
  color: #444;
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
