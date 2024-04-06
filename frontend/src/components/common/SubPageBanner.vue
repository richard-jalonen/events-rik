<template>
  <div id="event-banner" class="bg-blue d-flex overflow-hidden align-items-center">
    <div class="col-3">
      <h4 class="text-light text-center fw-lighter">{{ header }}</h4>
    </div>
    <div class="col d-flex justify-content-end align-items-center">
      <img class="my-auto" :src="eventBanner" alt="banner" @load="true" />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import eventBanner from '@/assets/images/grass.jpg'

export default defineComponent({
  props: {
    header: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      eventBanner
    }
  }
})
</script>
