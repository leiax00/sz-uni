import App from './App'
// import '//at.alicdn.com/t/c/font_2015893_bmc0z1jwlfk.js'
import logger from "@/common/plugins/logger";

// #ifndef VUE3
import Vue from 'vue'
import './uni.promisify.adaptor'
Vue.config.productionTip = false
App.mpType = 'app'
Vue.use(logger)

const app = new Vue({
  ...App
})
app.$mount()
// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
export function createApp() {
  const app = createSSRApp(App)
  app.use(logger)
  return {
    app
  }
}
// #endif