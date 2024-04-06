<template>
  <div class="bg-light">
    <h4 class="bg-blue text-light text-center fw-lighter py-3">{{ translations.header }}</h4>
    <div class="p-3">
      <div>Events</div>
      <div class="mt-4">
        <router-link to="/event"
          ><span class="text-dark-gray">{{ translations.addEvent }}</span></router-link
        >
      </div>
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
