<template>
  <div class="register-page">
    <NavBar />

    <!-- 注册表单 -->
    <div class="register-container">
      <h2>用户注册</h2>
      <form @submit.prevent="handleRegister">
        <label for="username">用户名</label>
        <input type="text" id="username" v-model="form.username" required />

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

        <label for="confirm">确认密码</label>
        <input
            type="password"
            id="confirm"
            v-model="form.confirm"
            required
        />

        <button type="submit">注册</button>
      </form>
    </div>

    <!-- 页脚 -->
    <div class="footer">
      &copy; 2025 MindCapturer. All rights reserved.
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import { reactive } from "vue";

export default {
  name: "Register",
  components: { NavBar },
  setup() {
    const form = reactive({
      username: "",
      email: "",
      phone: "",
      password: "",
      confirm: "",
    });

    function handleRegister() {
      if (form.password !== form.confirm) {
        alert("两次输入的密码不一致！");
        return;
      }
      // TODO: 对接后端注册接口
      console.log("注册信息：", form);
      alert(`尝试注册：${form.username}`);
    }

    return { form, handleRegister };
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
