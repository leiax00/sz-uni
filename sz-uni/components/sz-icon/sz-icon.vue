<template>
  <view class="icon-main inline-block align-middle" @click="onClick">
    <image v-if="isImg" :src="icon" :mode="picMode" class="w-full h-full" />
    <uni-icons
      v-else
      :custom-prefix="prefix"
      :type="icon"
      size="inherit"
      color="inherit"
    >
    </uni-icons>
  </view>
</template>

<script>

import {isEmptyStr, strInList} from "@/common/utils";

export default {
  name: "SzIcon",
  props: {
    iconClass: String,
    uniIcon: {
      type: Boolean,
      default: false
    },
    icon: {
      type: String,
      required: true
    },
    picMode: {
      type: String,
      default: 'aspectFit'
    }
  },
  computed: {
    prefix: function () {
      return this.uniIcon ? undefined : "appIcons";
    },
    isImg: function () {
      const items = isEmptyStr(this.icon) ? [this.icon] : this.icon.split('.')
      return items.length > 1 && strInList(items[items.length - 1], ['png', 'gif', 'jpg'])
    }
  },
  methods: {
    onClick() {
      this.$emit('click')
    },
  }
}
</script>

<style scoped lang="scss">
</style>
