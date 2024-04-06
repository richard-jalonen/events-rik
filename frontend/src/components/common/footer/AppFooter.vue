<template>
  <div class="bg-dark-gray text-light d-flex small p-5 grid">
    <GeneralInfo class="col" :translations="translations.ee.footer.generalInfo.firstComponent" />
    <GeneralInfo class="col" :translations="translations.ee.footer.generalInfo.secondComponent" />
    <ContactInfo class="col-6" />
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import GeneralInfo from '@/components/common/footer/GeneralInfo.vue'
import ContactInfo from '@/components/common/footer/ContactInfo.vue'
import translationsEe from '@/assets/messages.ee.json'

export default defineComponent({
  components: { ContactInfo, GeneralInfo },
  data() {
    return {
      translations: {
        ee: translationsEe
      }
    }
  }
})
</script>
