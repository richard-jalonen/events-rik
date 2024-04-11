<template>
  <div>
    <WelcomeBanner />
    <div class="my-4 grid d-flex gap-4">
      <EventsOverview
        id="upcoming-events"
        class="col"
        :events="upcomingEvents"
        :translations="translations.ee.home.upcomingEvents"
        can-delete-events
        @event-deleted="initialize"
      />
      <EventsOverview
        id="past-events"
        :events="pastEvents"
        class="col"
        :translations="translations.ee.home.pastEvents"
        @event-deleted="initialize"
      />
    </div>
  </div>
</template>

<script lang="ts">
import EventsOverview from '@/components/home/EventsOverview.vue'
import WelcomeBanner from '@/components/home/WelcomeBanner.vue'
import translationsEe from '@/assets/messages.ee.json'
import { useEventStore } from '@/stores/event'
import { defineComponent } from 'vue'

export default defineComponent({
  components: { EventsOverview, WelcomeBanner },
  data() {
    return {
      translations: {
        ee: translationsEe
      }
    }
  },
  computed: {
    upcomingEvents() {
      return useEventStore().getFutureEvents
    },
    pastEvents() {
      return useEventStore().getPastEvents
    }
  },
  created() {
    this.initialize()
  },
  methods: {
    async initialize() {
      await useEventStore().initialize()
    }
  }
})
</script>
