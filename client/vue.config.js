const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      '^/api': {
        target: 'http://zihbot.eu:35690/api',
        changeOrigin: true
      }
    }
  }
})
