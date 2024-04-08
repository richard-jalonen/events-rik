<template>
  <div v-if="participants.length > 0">
    <div v-for="(participant, index) in participants" :key="index" class="row">
      <div class="col-1">{{ index + 1 }}.</div>
      <div class="col-6">{{ participant.firstName }} {{ participant.lastName }}</div>
      <div class="col-4">{{ participant.personCode }}</div>
      <div class="col-1">
        <router-link :to="{ path: `/participant/${participant.uuid}` }">
          <span class="text-sm text-dark-gray me-2">{{ $t('eventEdit.buttons.view') }}</span>
        </router-link>
        <span class="text-sm text-dark-gray link" @click="deleteParticipant(participant.uuid)">{{
          $t('eventEdit.buttons.delete')
        }}</span>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import type { Participant } from '@/models/Participant'
import ApiClient from '@/client/api.client'
import { defineComponent } from 'vue'

export default defineComponent({
  props: {
    participants: {
      type: Array as () => Participant[],
      required: true
    }
  },
  data() {
    return {}
  },
  methods: {
    async deleteParticipant(participantUuid: string) {
      try {
        await ApiClient.deleteParticipant(participantUuid)
        this.$emit('participantDeleted')
      } catch (error) {
        console.error('Error deleting participant:', error)
      }
    }
  }
})
</script>
