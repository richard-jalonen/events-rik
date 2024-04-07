<template>
  <SubPage :banner-header="$t('eventEdit.header')">
    <EventParticipants />
    <PersonAddForm class="my-5" :event-uuid="$route.params.id.toString()" />
  </SubPage>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import EventParticipants from '@/components/event/EventParticipants.vue'
import PersonAddForm from '@/components/person/PersonAddForm.vue'
import SubPage from '@/components/common/SubPage.vue'

export default defineComponent({
  components: { SubPage, PersonAddForm, EventParticipants },
  data() {
    return {}
  }
})
</script>
