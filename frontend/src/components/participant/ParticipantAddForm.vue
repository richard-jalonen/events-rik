<template>
  <div>
    <h4 class="text-blue fw-light mb-4">{{ $t('participantAdd.header') }}</h4>
    <div class="d-flex offset-4">
      <div class="form-check me-4 mb-3 ms-2">
        <input
          id="private"
          v-model="type"
          class="form-check-input"
          type="radio"
          name="flexRadioDefault"
          value="PRIVATE"
        />
        <label class="form-check-label" for="private"
          >{{ $t('participantAdd.buttons.private') }}
        </label>
      </div>
      <div class="form-check">
        <input
          id="legal"
          v-model="type"
          class="form-check-input"
          type="radio"
          name="flexRadioDefault"
          value="LEGAL"
        />
        <label class="form-check-label" for="legal">
          {{ $t('participantAdd.buttons.legal') }}
        </label>
      </div>
    </div>
    <PrivateParticipantForm v-if="type === 'PRIVATE'" :event-uuid="eventUuid" />
    <LegalParticipantForm v-else :event-uuid="eventUuid" />
  </div>
</template>

<script lang="ts">
import PrivateParticipantForm from '@/components/participant/PrivateParticipantForm.vue'
import LegalParticipantForm from '@/components/participant/LegalParticipantForm.vue'
import { defineComponent } from 'vue'

export default defineComponent({
  components: { LegalParticipantForm, PrivateParticipantForm },
  props: {
    eventUuid: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      type: 'PRIVATE'
    }
  }
})
</script>
