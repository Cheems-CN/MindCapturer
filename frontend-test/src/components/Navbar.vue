<template>
  <!-- 导航栏 -->
  <div class="navbar">
    <div class="center">
      <router-link to="/" class="nav-link">主页</router-link>
      <router-link to="/tech" class="nav-link">技术功能介绍</router-link>
      <!-- logo 放在中间 -->
      <router-link to="/" class="logo">MindCapturer</router-link>
      <router-link to="/login" class="nav-link">登陆</router-link>
      <router-link to="/register" class="nav-link">注册</router-link>
    </div>
    <a href="#about" class="about">关于我们</a>
  </div>
</template>

<script>
export default {
  name: "NavBar",
};
</script>

<style scoped>
/* 注意：下面样式沿用了原页面中的导航栏样式 */
.navbar {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 60px;
  /* 不同页面背景色略有差别，可统一或在各视图里调整 */
  background: #f0f8ff;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 40px;
  border-bottom: 1px solid #ccc;
  z-index: 1000;
}
.center {
  display: flex;
  gap: 20px;
  align-items: center;
  justify-content: center;
  flex: 1;
}
.nav-link {
  text-decoration: none;
  color: #003366;
  font-weight: 600;
  font-size: 15px;
  padding: 8px 12px;
  border-radius: 5px;
  transition: 0.2s ease-in-out;
}
.nav-link:hover:not(.logo) {
  background-color: rgba(0, 51, 102, 0.05);
}
.logo {
  display: inline-block;
  width: 180px;
  height: 60px;
  background-image: url("/logo.png");
  background-size: contain;
  background-repeat: no-repeat;
  background-position: center;
  text-indent: -9999px;
}
.about {
  background: #ccdeec;
  color: white;
  font-weight: 600;
  padding: 8px 12px;
  border-radius: 5px;
  text-decoration: none;
}
.about:hover {
  background: #005b9f;
}
</style>
