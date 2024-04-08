<template>
  <SubPage :banner-header="$t('participantEdit.header')">
    <h4 class="text-blue fw-light mb-4">{{ $t('participantEdit.header') }}</h4>
    <PrivateParticipantForm
      v-if="participant && participant.type === 'PRIVATE'"
      :participant="participant"
    />
    <LegalParticipantForm
      v-if="participant && participant.type === 'LEGAL'"
      :participant="participant"
    />
  </SubPage>
</template>

<script lang="ts">
import PrivateParticipantForm from '@/components/participant/PrivateParticipantForm.vue'
import LegalParticipantForm from '@/components/participant/LegalParticipantForm.vue'
import type { Participant } from '@/models/Participant'
import SubPage from '@/components/common/SubPage.vue'
import ApiClient from '@/client/api.client'
import { defineComponent } from 'vue'

export default defineComponent({
  components: {
    LegalParticipantForm,
    PrivateParticipantForm,
    SubPage
  },
  data() {
    return {
      participant: null as Participant | null
    }
  },
  created() {
    this.fetchParticipantData()
  },
  methods: {
    async fetchParticipantData() {
      try {
        const response = await ApiClient.getParticipant(this.$route.params.id.toString())
        this.participant = response.data
      } catch (error) {
        console.error('Error fetching participant data:', error)
      }
    }
  }
})
</script>
