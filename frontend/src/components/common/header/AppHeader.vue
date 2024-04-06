<template>
  <div class="d-flex px-4 bg-light">
    <div class="my-auto">
      <img class="align-self-center" :src="logoSrc" alt="logo" />
    </div>
    <div class="d-flex align-items-stretch flex-fill px-5">
      <router-link
        class="align-content-center px-3"
        :class="{ 'bg-blue': getRouterPath == '/', 'text-dark-gray': getRouterPath != '/' }"
        to="/"
      >
        <span>{{ $t('header.homePage') }}</span>
      </router-link>
      <router-link
        class="align-content-center px-3"
        :class="{
          'bg-blue': getRouterPath == '/event',
          'text-dark-gray': getRouterPath !== '/event'
        }"
        to="/event"
      >
        <span>{{ $t('header.addEvent') }}</span>
      </router-link>
    </div>
    <div class="my-2 d-flex">
      <img :src="symbolSrc" alt="symbol" />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import logoSrc from '@/assets/images/logo.svg'
import symbolSrc from '@/assets/images/symbol.svg'

export default defineComponent({
  data() {
    return {
      logoSrc,
      symbolSrc,
      isActive: true,
      hasError: false
    }
  },
  computed: {
    getRouterPath() {
      return this.$router.currentRoute.value.path
    }
  }
})
</script>
