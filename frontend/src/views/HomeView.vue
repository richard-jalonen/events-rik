<template>
  <div>
    <WelcomeBanner />
    <div class="my-4 grid d-flex gap-4">
      <EventsOverview class="col" :translations="translations.ee.home.upcomingEvents" />
      <EventsOverview class="col" :translations="translations.ee.home.pastEvents" />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import WelcomeBanner from '@/components/home/WelcomeBanner.vue'
import EventsOverview from '@/components/home/EventsOverview.vue'
import translationsEe from '@/assets/messages.ee.json'

export default defineComponent({
  components: { EventsOverview, WelcomeBanner },
  data() {
    return {
      translations: {
        ee: translationsEe
      }
    }
  }
})
</script>
