<template>
  <div>
    <WelcomeBanner />
    <div class="my-4 grid d-flex gap-4">
      <EventsOverview
        class="col"
        :events="upcomingEvents"
        :translations="translations.ee.home.upcomingEvents"
        can-delete-events
      />
      <EventsOverview
        :events="pastEvents"
        class="col"
        :translations="translations.ee.home.pastEvents"
      />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import WelcomeBanner from '@/components/home/WelcomeBanner.vue'
import EventsOverview from '@/components/home/EventsOverview.vue'
import translationsEe from '@/assets/messages.ee.json'
import { useEventStore } from '@/stores/event'

export default defineComponent({
  components: { EventsOverview, WelcomeBanner },
  data() {
    return {
      translations: {
        ee: translationsEe
      }
    }
  },
  beforeMount() {
    useEventStore().initialize()
  },
  computed: {
    upcomingEvents() {
      return useEventStore().getFutureEvents
    },
    pastEvents() {
      return useEventStore().getPastEvents
    }
  }
})
</script>
