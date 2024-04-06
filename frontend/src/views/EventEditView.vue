<template>
  <div class="bg-light">
    <SubPageBanner :header="$t('eventEdit.header')" />
    <div class="container py-5">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <EventParticipants />
          <PersonAddForm class="my-5" :event-uuid="$route.params.id.toString()" />
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import EventParticipants from '@/components/event/EventParticipants.vue'
import SubPageBanner from '@/components/common/SubPageBanner.vue'
import PersonAddForm from '@/components/person/PersonAddForm.vue'

export default defineComponent({
  components: { PersonAddForm, SubPageBanner, EventParticipants },
  data() {
    return {}
  }
})
</script>
