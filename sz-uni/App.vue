<script>
	import apis from "@/common/apis";

  export default {
		onLaunch: function() {
      this.initPlugin()
      this.$logger.info('App Launch')
		},
		onShow: function() {
      this.$logger.info('App Show')
		},
		onHide: function() {
      this.$logger.info('App Hide')
		},
    methods: {
      initPlugin() {
        const self = this
        apis.plugin.initPlugin()
        plus.globalEvent.addEventListener('sz-uni-event', function ({action, data}) {
          self.$logger.info(`Received Event, Action: ${action}, Data: ${JSON.stringify(data)}`);
        })
      }
    }
	}
</script>

<style lang="scss">
	/*每个页面公共css */
  @import "@/styles/index.scss";
</style>
