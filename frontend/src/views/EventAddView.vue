<template>
  <div class="bg-light">
    <SubPageBanner :header="$t('eventAdd.header')" />
    <EventAddForm />
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import EventAddForm from '@/components/event/EventAddForm.vue'
import SubPageBanner from '@/components/common/SubPageBanner.vue'

export default defineComponent({
  components: { SubPageBanner, EventAddForm },
  data() {
    return {}
  }
})
</script>
