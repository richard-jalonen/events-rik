<template>
  <SubPage :banner-header="$t('eventEdit.header')">
    <EventParticipants />
    <ParticipantAddForm class="my-5" :event-uuid="$route.params.id.toString()" />
  </SubPage>
</template>

<script lang="ts">
import ParticipantAddForm from '@/components/participant/ParticipantAddForm.vue'
import EventParticipants from '@/components/event/EventParticipants.vue'
import SubPage from '@/components/common/SubPage.vue'
import { defineComponent } from 'vue'

export default defineComponent({
  components: { ParticipantAddForm, SubPage, EventParticipants },
  data() {
    return {}
  }
})
</script>
