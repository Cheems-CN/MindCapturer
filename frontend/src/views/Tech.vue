<template>
  <div class="tech-page">
    <NavBar />

    <!-- 主体内容 -->
    <div class="content">
      <div class="card">
        <h2>一、文档信息快速上传</h2>
        <p>
          系统支持用户上传结构化或非结构化的心理相关文档信息，如问卷结果、用户自述文本等，作为个性化评估与心理状态分析的重要数据源。
        </p>
      </div>

      <div class="card">
        <h2>二、AI模型与身份选择</h2>
        <p>
          用户可选择不同身份设定（如“倾诉者”、“聆听者”、“指导者”），系统自动匹配相应的心理疗愈大语言模型，实现内容风格、交互方式的定制化。
        </p>
      </div>

      <div class="card">
        <h2>三、个性化疗愈对话</h2>
        <p>
          基于大模型（LLM）与知识图谱融合，构建具备记忆和情感理解能力的对话系统，支持用户在对话中获得持续性情绪疏导与正向反馈。
        </p>
        <ul>
          <li>引入多轮上下文建模与历史记忆追踪</li>
          <li>支持文本 + 图表 + 音频多模态交互</li>
          <li>智能识别情绪状态并调整对话风格</li>
        </ul>
      </div>

      <div class="card">
        <h2>四、疗愈总结与智能建议</h2>
        <p>
          系统可生成结构化的情绪状态分析报告，并给出行为建议、冥想练习、情绪日志推荐等，实现后续的情绪跟踪与心理干预闭环。
        </p>
        <ul>
          <li>阶段性总结与趋势图表呈现</li>
          <li>结合用户档案定制个性化建议</li>
          <li>可导出 PDF 格式用于专业咨询对接</li>
        </ul>
      </div>
    </div>

    <!-- 页脚 -->
    <div class="footer">
      &copy; 2025 MindCapturer 心灵捕手项目组 ｜ 联系方式：info@mindcapturer.ai
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";

export default {
  name: "Tech",
  components: { NavBar },
};
</script>

<style scoped>
.tech-page {
  padding-top: 60px; /* 留出导航高度 */
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  background: url("/bc.jpg") no-repeat center center fixed;
  background-size: cover;
  color: #333;
}

.content {
  padding: 50px 20px;
  max-width: 1000px;
  margin: auto;
  display: flex;
  flex-direction: column;
  gap: 30px;
}

.card {
  background: white;
  padding: 30px;
  border-radius: 12px;
  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.05);
  transition: transform 0.2s ease;
}

.card:hover {
  transform: translateY(-2px);
}

.card h2 {
  font-size: 20px;
  margin-bottom: 15px;
  color: #1c4c69;
  border-left: 4px solid #0077b6;
  padding-left: 10px;
}

.card p,
.card ul {
  font-size: 15px;
  line-height: 1.6;
  color: #444;
}

.card ul {
  margin-top: 10px;
  padding-left: 20px;
  list-style-type: disc;
}

.footer {
  margin-top: 60px;
  padding: 20px;
  background: #cceeff;
  text-align: center;
  color: #444;
  font-size: 14px;
  border-top: 1px solid #bbb;
}

@media (max-width: 768px) {
  .navbar {
    flex-wrap: wrap;
    height: auto;
    padding: 10px 20px;
  }

  .content {
    padding: 30px 15px;
  }
}
</style>
