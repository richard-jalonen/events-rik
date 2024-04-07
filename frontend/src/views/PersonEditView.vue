<template>
  <SubPage :banner-header="$t('personEdit.header')">
    <h4 class="text-blue fw-light mb-4">{{ $t('personEdit.header') }}</h4>
    <PrivatePersonForm v-if="person && person.type === 'PRIVATE'" :person="person" />
    <LegalPersonForm v-if="person && person.type === 'LEGAL'" :person="person" />
  </SubPage>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import ApiClient from '@/client/api.client'
import type { Person } from '@/models/Person'
import PrivatePersonForm from '@/components/person/PrivatePersonForm.vue'
import LegalPersonForm from '@/components/person/LegalPersonForm.vue'
import SubPage from '@/components/common/SubPage.vue'

export default defineComponent({
  components: { SubPage, PrivatePersonForm, LegalPersonForm },
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
