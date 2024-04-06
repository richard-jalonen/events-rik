<template>
  <div>
    <UpcomingEvents />
    <PastEvents />
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import UpcomingEvents from '@/components/event/UpcomingEvents.vue'
import PastEvents from '@/components/event/PastEvents.vue'

export default defineComponent({
  components: { PastEvents, UpcomingEvents },
  data() {
    return {}
  }
})
</script>
