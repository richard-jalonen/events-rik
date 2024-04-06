<template>
  <div class="container">
    <AppHeader />
    <RouterView class="my-4" />
    <AppFooter />
  </div>
</template>

<script setup lang="ts">
import { RouterView } from 'vue-router'
import AppHeader from '@/components/common/header/AppHeader.vue'
import AppFooter from '@/components/common/footer/AppFooter.vue'
import { usePaymentStore } from '@/stores/payment'

initialize()

async function initialize() {
  await usePaymentStore().initialize()
}
</script>
