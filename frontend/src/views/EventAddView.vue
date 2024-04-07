<template>
  <SubPage :banner-header="$t('eventAdd.header')">
    <EventAddForm />
  </SubPage>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import EventAddForm from '@/components/event/EventAddForm.vue'
import SubPage from '@/components/common/SubPage.vue'

export default defineComponent({
  components: { SubPage, EventAddForm },
  data() {
    return {}
  }
})
</script>
