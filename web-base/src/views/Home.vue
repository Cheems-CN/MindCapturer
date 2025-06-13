<template>
  <div class="home-page">
    <NavBar />

    <!-- 主体内容 -->
    <div class="layout">
      <!-- 侧边栏 -->
      <div class="sidebar">
        <h3>项目导航</h3>
        <ul>
          <li><a href="#intro">项目简介</a></li>
          <li><a href="#goal">研究目的</a></li>
          <li><a href="#tech">技术路线</a></li>
          <li><a href="#innovation">项目特色</a></li>
        </ul>
      </div>

      <!-- 主内容 -->
      <div class="main-content">
        <section id="intro">
          <h2>项目简介</h2>
          <p>
            “心灵捕手”项目聚焦少通道脑电信号情绪识别，通过无毛发区域采集少通道 EEG，借助图卷积网络、迁移学习等算法，实现情绪精准分类。项目融合大语言模型与记忆增强机制，打造具备实时心理干预能力的交互平台，适用于心理健康、情绪调节等多个领域。
          </p>
        </section>

        <section id="goal">
          <h2>研究目的</h2>
          <p>
            本项目旨在降低传统 EEG 情绪识别的成本与复杂性，通过前额、耳后等少通道采集方式提升便携性，同时构建智能对话系统，弥补现有系统情绪识别不精准、交互无个性、疗效不可持续等短板，推动其在日常心理健康管理中的应用。
          </p>
        </section>

        <section id="tech">
          <h2>技术路线</h2>
          <p>
            项目使用 GCN+NeXtVLAD 构建少通道 EEG 情绪识别模型，采用跨设备迁移学习策略进行优化。配合智能交互平台，集成大模型、强化学习、知识图谱，实现实时情绪响应、记忆增强、自适应决策等功能。
          </p>
        </section>

        <section id="innovation">
          <h2>创新点</h2>
          <ul>
            <li>基于无毛发区域的 EEG 采集与便捷识别模型</li>
            <li>引入迁移学习提升跨设备泛化能力</li>
            <li>大模型+知识图谱驱动的心理疗愈对话系统</li>
            <li>多模态记忆增强机制实现个性化交互</li>
            <li>构建具有伦理安全保障的交互环境</li>
          </ul>
        </section>
      </div>
    </div>

    <!-- 页脚 -->
    <div class="footer">
      &copy; 2025 MindCapturer. All rights reserved.
    </div>
  </div>
</template>

<script>
import NavBar from "../components/Navbar.vue";

export default {
  name: "Home",
  components: { NavBar },
};
</script>

<style scoped>
/* 页面整体背景与导航栏已经由 NavBar 控制；这里只对 Home 特有的布局做样式 */
.home-page {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  background: url("/bc.jpg") no-repeat center center fixed;
  background-size: cover;
  padding-top: 60px; /* 给导航留出空间 */
  color: #333;
}

.layout {
  display: flex;
  padding: 40px 20px;
  max-width: 1200px;
  margin: 0 auto;
  gap: 30px;
}

.sidebar {
  width: 220px;
  background: #eaf4ff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.05);
  height: fit-content;
}

.sidebar h3 {
  font-size: 18px;
  margin-bottom: 12px;
  border-bottom: 1px solid #ccc;
  padding-bottom: 6px;
}

.sidebar ul {
  list-style: none;
}

.sidebar ul li {
  margin: 10px 0;
}

.sidebar ul li a {
  color: #0077b6;
  text-decoration: none;
  font-size: 15px;
}

.sidebar ul li a:hover {
  text-decoration: underline;
}

.main-content {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 30px;
}

.main-content section {
  background: white;
  padding: 25px 30px;
  border-radius: 10px;
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.06);
}

.main-content h2 {
  font-size: 20px;
  color: #003366;
  margin-bottom: 10px;
  border-left: 4px solid #0077b6;
  padding-left: 10px;
}

.main-content p,
.main-content ul {
  font-size: 15px;
  line-height: 1.6;
  color: #444;
}

.main-content ul {
  margin-top: 10px;
  padding-left: 20px;
  list-style-type: disc;
}

.footer {
  margin-top: 80px;
  padding: 20px;
  background: #cceeff;
  text-align: center;
  color: #333;
  font-size: 14px;
}

/* 响应式 */
@media (max-width: 768px) {
  .layout {
    flex-direction: column;
    padding: 20px;
  }

  .sidebar {
    width: 100%;
  }
}
</style>
