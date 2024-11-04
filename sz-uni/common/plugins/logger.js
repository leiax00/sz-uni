import {logger} from "@/common/utils";

export default {
  install(app) {
    // #ifndef VUE3
    app.prototype.$logger = logger;
    // #endif
    // #ifdef VUE3
    app.config.globalProperties.$logger = logger;
    // #endif
  }
};
