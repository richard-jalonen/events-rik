<template>
  <div
    class="d-flex bg-blue text-light align-items-center overflow-hidden"
    :style="{ maxHeight: maxHeight + 'px' }"
  >
    <div
      class="flex-fill custom-text-lg mx-5 my-auto fw-lighter"
      v-html="$t('home.bannerText')"
    ></div>
    <img :src="welcomeBannerImg" alt="banner" @load="setHeight" />
  </div>
</template>

<script lang="ts">
import welcomeBannerImg from '@/assets/images/main-page-banner.jpg'
import { defineComponent } from 'vue'

export default defineComponent({
  data() {
    return {
      welcomeBannerImg,
      maxHeight: 0
    }
  },
  methods: {
    setHeight() {
      let img = new Image()
      img.src = this.welcomeBannerImg
      this.maxHeight = img.naturalHeight
    }
  }
})
</script>
