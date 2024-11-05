const main = uni.requireNativePlugin("main");
const http = uni.requireNativePlugin("http");

/**
 * 初始化插件
 * @param opts
 */
export function initPlugin(opts = {}) {
  main.init(opts)
}

export function showActivity(opts = {}) {
  return new Promise(resolve => {
    main.showActivity(opts, res => {
      resolve(res);
    })
  })
}

/**
 * http请求 - get
 * @param {{
 *   url: string,
 *   params?: Object
 * }} opts
 * @returns {Promise<{
 *   code: number,
 *   msg: string,
 *   data: Object
 * }>}
 */
export function get(opts) {
  return new Promise(resolve => {
    http.get(opts, res => {
      resolve(res);
    })
  })
}

/**
 * http请求 - get
 * @param {{
 *   url: string,
 *   body?: Object
 * }} opts
 * @returns {Promise<{
 *   code: number,
 *   msg: string,
 *   data: Object
 * }>}
 */
export function post(opts) {
  return new Promise(resolve => {
    http.post(opts, res => {
      resolve(res);
    })
  })
}