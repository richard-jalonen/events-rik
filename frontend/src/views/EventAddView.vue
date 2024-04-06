<template>
  <div class="bg-light">
    <EventAddBanner />
    <EventAddForm />
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import EventAddBanner from '@/components/event/EventAddBanner.vue'
import EventAddForm from '@/components/event/EventAddForm.vue'

export default defineComponent({
  components: { EventAddForm, EventAddBanner },
  data() {
    return {}
  }
})
</script>
