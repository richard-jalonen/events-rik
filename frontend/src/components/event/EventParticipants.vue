<template>
  <div>
    <div class="grid">
      <div class="row">
        <h4 class="text-blue fw-light mb-4">{{ $t('eventEdit.header') }}</h4>
        <div class="col-4">
          <div class="mb-2">{{ $t('eventEdit.name') }}</div>
          <div class="mb-2">{{ $t('eventEdit.time') }}</div>
          <div class="mb-2">{{ $t('eventEdit.location') }}</div>
          <div>{{ $t('eventEdit.participants') }}</div>
        </div>
        <div class="col">
          <div class="mb-2">{{ event?.name }}</div>
          <div class="mb-2">{{ FormatUtil.formatDate(event?.time) }}</div>
          <div class="mb-2">{{ event?.location }}</div>
          <div>
            <EventParticipantsList
              :participants="event?.participants || []"
              @participant-deleted="fetchEventData"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import EventParticipantsList from '@/components/event/EventParticipantsList.vue'
import ApiClient from '@/client/api.client'
import FormatUtil from '@/util/format.util'
import type { Event } from '@/models/Event'
import { defineComponent } from 'vue'

export default defineComponent({
  components: { EventParticipantsList },
  data() {
    return {
      event: null as Event | null,
      FormatUtil
    }
  },
  created() {
    this.fetchEventData()
  },
  methods: {
    async fetchEventData() {
      try {
        const response = await ApiClient.getEvent(this.$route.params.id.toString())
        this.event = response.data
      } catch (error) {
        console.error('Error fetching event data:', error)
      }
    }
  }
})
</script>
