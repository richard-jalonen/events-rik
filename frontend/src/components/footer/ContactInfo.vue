<template>
  <div class="grid">
    <div class="row">
      <h3>{{ $t('footer.contactInfo.header') }}</h3>
      <div class="col-md-auto">
        <div>{{ $t('footer.contactInfo.mainOffice') }} {{ mainOfficeExample.city }}</div>
        <div>{{ mainOfficeExample.address }}</div>
        <div>{{ $t('footer.contactInfo.phone') }} {{ mainOfficeExample.phone }}</div>
        <div>{{ $t('footer.contactInfo.fax') }} {{ mainOfficeExample.fax }}</div>
      </div>
      <div class="col-md-auto">
        <div>{{ $t('footer.contactInfo.secondaryOffice') }} {{ secondaryOfficeExample.city }}</div>
        <div>{{ secondaryOfficeExample.address }}</div>
        <div>{{ $t('footer.contactInfo.phone') }} {{ secondaryOfficeExample.phone }}</div>
        <div>{{ $t('footer.contactInfo.fax') }} {{ secondaryOfficeExample.fax }}</div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  data() {
    return {
      mainOfficeExample: {
        city: this.$t('footer.contactInfo.firstOfficeExample.city'),
        address: this.$t('footer.contactInfo.firstOfficeExample.address'),
        phone: this.$t('footer.contactInfo.firstOfficeExample.phone'),
        fax: this.$t('footer.contactInfo.firstOfficeExample.fax')
      },
      secondaryOfficeExample: {
        city: this.$t('footer.contactInfo.secondOfficeExample.city'),
        address: this.$t('footer.contactInfo.secondOfficeExample.address'),
        phone: this.$t('footer.contactInfo.secondOfficeExample.phone'),
        fax: this.$t('footer.contactInfo.secondOfficeExample.fax')
      }
    }
  }
})
</script>
