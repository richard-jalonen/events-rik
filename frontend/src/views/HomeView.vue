<template>
  <div>
    <WelcomeBanner />
    <UpcomingEvents />
    <PastEvents />
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import UpcomingEvents from '@/components/event/UpcomingEvents.vue'
import PastEvents from '@/components/event/PastEvents.vue'
import WelcomeBanner from '@/components/home/WelcomeBanner.vue'

export default defineComponent({
  components: { WelcomeBanner, PastEvents, UpcomingEvents },
  data() {
    return {}
  }
})
</script>
