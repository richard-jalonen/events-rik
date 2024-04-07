<template>
  <div>
    <SubPageBanner :header="bannerHeader" />
    <div class="container py-5 bg-light">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <slot></slot>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import SubPageBanner from '@/components/common/SubPageBanner.vue'

export default defineComponent({
  components: { SubPageBanner },
  props: {
    bannerHeader: {
      type: String,
      required: true
    }
  }
})
</script>
