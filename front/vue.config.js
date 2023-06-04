const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    // 跨域代理
    proxy: {
      '/api': {
        target: 'http://8.140.52.224:8080/',
        changeOrigin: true,
        ws: true,
        pathRewrite: {
          '^/api': ''
        }
      },
    }
  },
  // 基本路径
  publicPath: './',
})
