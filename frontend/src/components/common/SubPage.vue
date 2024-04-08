<template>
  <div>
    <SubPageBanner :header="bannerHeader" />
    <BOverlay :show="isLoading">
      <div class="container py-5 bg-light">
        <div class="row justify-content-center">
          <div class="col-md-6">
            <slot></slot>
          </div>
        </div>
      </div>
    </BOverlay>
  </div>
</template>

<script lang="ts">
import SubPageBanner from '@/components/common/SubPageBanner.vue'
import { useGlobalStore } from '@/stores/global'
import { BOverlay } from 'bootstrap-vue-next'
import { defineComponent } from 'vue'

export default defineComponent({
  components: { SubPageBanner, BOverlay },
  props: {
    bannerHeader: {
      type: String,
      required: true
    }
  },
  computed: {
    isLoading() {
      return useGlobalStore().getLoading
    }
  }
})
</script>
