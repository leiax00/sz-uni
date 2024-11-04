<template>
	<div>
		<myText ref="telText" tel="12305" style="width:200px;height:100px" @onTel="onTel" @click="myTextClick"></myText>
	</div>
</template>

<script>  
    export default {  
        data() {  
            return {  
            }  
        },  
        onLoad() {  
        },  
        methods: {  
			onTel(e) {
				console.log("onTel="+e.detail.tel);
			},
			myTextClick(e) {
				this.$refs.telText.clearTel();
			}
        }  
    }  
</script>
<style scoped>
@import "../common/nvue.css";
@import "../common/uni-nvue.css";
</style>