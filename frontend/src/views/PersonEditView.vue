<template>
  <div class="bg-light">
    <SubPageBanner :header="$t('personEdit.header')" />
    <div class="container py-5">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <h4 class="text-blue fw-light mb-4">{{ $t('personEdit.header') }}</h4>
          <PrivatePersonForm v-if="person && person.type === 'PRIVATE'" :person="person" />
          <LegalPersonForm v-if="person && person.type === 'LEGAL'" :person="person" />
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import SubPageBanner from '@/components/common/SubPageBanner.vue'
import ApiClient from '@/client/api.client'
import type { Person } from '@/models/Person'
import PrivatePersonForm from '@/components/person/PrivatePersonForm.vue'
import LegalPersonForm from '@/components/person/LegalPersonForm.vue'

export default defineComponent({
  components: { SubPageBanner, PrivatePersonForm, LegalPersonForm },
  data() {
    return {
      person: null as Person | null
    }
  },
  created() {
    this.fetchPersonData()
  },
  methods: {
    async fetchPersonData() {
      try {
        const response = await ApiClient.getPerson(this.$route.params.id.toString())
        this.person = response.data
      } catch (error) {
        console.error('Error fetching person data:', error)
      }
    }
  }
})
</script>
