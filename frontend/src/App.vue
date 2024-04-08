<template>
  <div class="container">
    <AppHeader />
    <RouterView class="my-4" />
    <AppFooter />
  </div>
</template>

<script lang="ts">
import AppHeader from '@/components/common/header/AppHeader.vue'
import AppFooter from '@/components/common/footer/AppFooter.vue'
import { usePaymentStore } from '@/stores/payment'
import { RouterView } from 'vue-router'
import { defineComponent } from 'vue'

export default defineComponent({
  components: { AppHeader, AppFooter, RouterView },
  created() {
    this.initialize()
  },
  methods: {
    async initialize() {
      await usePaymentStore().initialize()
    }
  }
})
</script>
