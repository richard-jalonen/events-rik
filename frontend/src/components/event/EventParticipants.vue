<template>
  <div>
    <div class="grid">
      <div class="row">
        <h4 class="text-blue fw-light mb-4">{{ $t('eventEdit.header') }}</h4>
        <div class="col-4">
          <div>{{ $t('eventEdit.name') }}</div>
          <div>{{ $t('eventEdit.time') }}</div>
          <div>{{ $t('eventEdit.location') }}</div>
          <div>{{ $t('eventEdit.participants') }}</div>
        </div>
        <div class="col">
          <div>{{ event?.name }}</div>
          <div>{{ FormatUtil.formatDate(event?.time) }}</div>
          <div>{{ event?.location }}</div>
          <div>{{ event?.participants }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import ApiClient from '@/client/api.client'
import type { Event } from '@/models/Event'
import FormatUtil from '@/util/format.util'

export default defineComponent({
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
