<template>
	<div>
		<button type="primary" @click="testAsyncFunc">testAsyncFunc</button>
		<button type="primary" @click="testSyncFunc">testSyncFunc</button>
		<button type="primary" @click="gotoNativePage">跳转原生Activity</button>
		<button type="primary" @click="openActivity">打开Activity</button>
	</div>
</template>

<script>
	// 获取 module 
	import apis from "@/common/apis";
  import {isEmptyStr} from "@/common/utils";

  var testModule = uni.requireNativePlugin("TestModule")
	const modal = uni.requireNativePlugin('modal');
	export default {
		onLoad() {
			plus.globalEvent.addEventListener('TestEvent', function(e){
				modal.toast({
					message: "TestEvent收到："+e.msg,
					duration: 1.5
				});
			});
		},
		methods: {
			testAsyncFunc() {
				// 调用异步方法
				testModule.testAsyncFunc({
						'name': 'unimp',
						'age': 1
					},
					(ret) => {
						modal.toast({
							message: ret,
							duration: 1.5
						});
					})
			},
			testSyncFunc() {
				// 调用同步方法
				var ret = testModule.testSyncFunc({
					'name': 'unimp',
					'age': 1
				})
				modal.toast({
					message: ret,
					duration: 1.5
				});
			},
			gotoNativePage() {
				testModule.gotoNativePage();
			},
      openActivity() {
        apis.plugin.get({url: "https://test.leiax00.ac.cn/"}).then(({ code, data }) => {
          this.$logger.info(`request result: code=${code}, data=${JSON.stringify(data)}`);
          if (code === 200) {
            apis.plugin.showActivity({phoneNum: data});
          }
        });
			},
		}
	}
</script>
<style scoped>
@import "../common/nvue.css";
@import "../common/uni-nvue.css";
</style>