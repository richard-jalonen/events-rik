<template>
  <div class="container">
    <AppHeader />
    <RouterView class="my-4" />
    <AppFooter />
  </div>
</template>

<script setup lang="ts">
import AppHeader from '@/components/common/header/AppHeader.vue'
import AppFooter from '@/components/common/footer/AppFooter.vue'
import { usePaymentStore } from '@/stores/payment'
import { RouterView } from 'vue-router'

initialize()

async function initialize() {
  await usePaymentStore().initialize()
}
</script>
