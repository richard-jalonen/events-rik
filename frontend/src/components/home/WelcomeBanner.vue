<template>
  <div
    class="d-flex bg-blue text-light align-items-center overflow-hidden"
    :style="{ maxHeight: maxHeight + 'px' }"
  >
    <div
      class="flex-fill custom-text-lg mx-5 my-auto fw-lighter"
      v-html="$t('home.bannerText')"
    ></div>
    <img :src="welcomeBannerImg" alt="banner" />
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import welcomeBannerImg from '@/assets/images/main-page-banner.jpg'

export default defineComponent({
  data() {
    return {
      welcomeBannerImg,
      maxHeight: 0
    }
  },
  mounted() {
    let img = new Image()
    img.src = welcomeBannerImg
    this.maxHeight = img.naturalHeight
  }
})
</script>
