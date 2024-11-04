<script>
	export default {
		onLaunch: function() {
			this.$logger.info('App Launch')
		},
		onShow: function() {
      this.$logger.info('App Show')
		},
		onHide: function() {
      this.$logger.info('App Hide')
		}
	}
</script>

<style lang="scss">
	/*每个页面公共css */
  @import "@/styles/index.scss";
</style>
