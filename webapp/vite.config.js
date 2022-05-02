import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

const path = require("path");
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "./src"),
    },
  },
  css: {
    preprocessorOptions: {
      scss: {
        additionalData: `@import "@/styles/vars.scss";`
      },
    }
  },
  server: {
    proxy: {
      '/api': {
        target: 'http://zihbot.eu:35682',
        rewrite: (path) => path.replace(/^\/api/, '')
      }
    }
  }
});