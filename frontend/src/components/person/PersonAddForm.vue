<template>
  <div>
    <h4 class="text-blue fw-light mb-4">{{ $t('personAdd.header') }}</h4>
    <div class="d-flex offset-4">
      <div class="form-check me-4 mb-3 ms-2">
        <input
          class="form-check-input"
          type="radio"
          name="flexRadioDefault"
          id="private"
          v-model="type"
          value="PRIVATE"
        />
        <label class="form-check-label" for="private">{{ $t('personAdd.buttons.private') }} </label>
      </div>
      <div class="form-check">
        <input
          class="form-check-input"
          type="radio"
          name="flexRadioDefault"
          id="legal"
          v-model="type"
          value="LEGAL"
        />
        <label class="form-check-label" for="legal">
          {{ $t('personAdd.buttons.legal') }}
        </label>
      </div>
    </div>
    <AddPrivatePersonForm v-if="type === 'PRIVATE'" :event-uuid="eventUuid" />
    <AddLegalPersonForm v-else :event-uuid="eventUuid" />
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import AddPrivatePersonForm from '@/components/person/AddPrivatePersonForm.vue'
import AddLegalPersonForm from '@/components/person/AddLegalPersonForm.vue'

export default defineComponent({
  components: { AddPrivatePersonForm, AddLegalPersonForm },
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
