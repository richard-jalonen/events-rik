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
        <span class="text-sm text-dark-gray link" @click="openModal(participant)">{{
          $t('eventEdit.buttons.delete')
        }}</span>
      </div>
    </div>
    <BModal v-model="showModal" :title="$t('eventEdit.modal.header')" @ok="deleteParticipant">
      {{ $t('eventEdit.modal.confirmation') }} {{ participantToDelete?.firstName }}
      {{ participantToDelete?.lastName }}
    </BModal>
  </div>
</template>

<script lang="ts">
import type { Participant } from '@/models/Participant'
import ApiClient from '@/client/api.client'
import { defineComponent } from 'vue'
import { BModal } from 'bootstrap-vue-next'

export default defineComponent({
  components: { BModal },
  props: {
    participants: {
      type: Array as () => Participant[],
      required: true
    }
  },
  data() {
    return {
      showModal: false,
      participantToDelete: null as Participant | null
    }
  },
  methods: {
    openModal(participant: Participant) {
      this.participantToDelete = participant
      this.showModal = true
    },
    async deleteParticipant() {
      try {
        await ApiClient.deleteParticipant(this.participantToDelete?.uuid!!)
        this.$emit('participantDeleted')
      } catch (error) {
        console.error('Error deleting participant:', error)
      }
    }
  }
})
</script>
