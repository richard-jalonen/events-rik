<template>
  <AppHeader />
  <RouterView />
  <AppFooter />
</template>

<script setup lang="ts">
import { RouterView } from 'vue-router'
import AppHeader from './components/AppHeader.vue'
import AppFooter from './components/AppFooter.vue'
import { usePaymentStore } from '@/stores/payment'

initialize()

async function initialize() {
  await usePaymentStore().initialize()
}
</script>
