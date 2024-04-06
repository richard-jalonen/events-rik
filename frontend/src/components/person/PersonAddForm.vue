<template>
  <div>
    <h4 class="text-blue fw-light mb-4">{{ $t('personAdd.header') }}</h4>
    <AddPrivatePersonForm :event-uuid="eventUuid" />
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import AddPrivatePersonForm from '@/components/person/AddPrivatePersonForm.vue'

export default defineComponent({
  components: { AddPrivatePersonForm },
  props: {
    eventUuid: {
      type: String,
      required: true
    }
  },
  data() {
    return {}
  }
})
</script>
