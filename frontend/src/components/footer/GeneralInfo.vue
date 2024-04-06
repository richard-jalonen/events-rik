<template>
  <div class="grid">
    <div class="col">
      <h3>{{ translations.header }}</h3>
      <div>{{ translations.firstText }}</div>
      <div>{{ translations.secondText }}</div>
      <div>{{ translations.thirdText }}</div>
      <div>{{ translations.fourthText }}</div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  props: {
    translations: {
      type: Object,
      required: true
    }
  },
  data() {
    return {}
  }
})
</script>
