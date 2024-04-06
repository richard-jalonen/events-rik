<template>
  <div class="p-5 d-flex justify-content-center align-items-center">
    <div>
      <h4 class="text-blue fw-light">{{ $t('eventAdd.header') }}</h4>

      <div class="grid my-5">
        <div class="row mb-2">
          <div class="col">{{ $t('eventAdd.form.eventName') }}</div>
          <input class="col" />
        </div>
        <div class="row mb-2">
          <div class="col">{{ $t('eventAdd.form.eventTime') }}</div>
          <input class="col" type="datetime-local" />
        </div>
        <div class="row mb-2">
          <div class="col">{{ $t('eventAdd.form.location') }}</div>
          <input class="col" />
        </div>
        <div class="row">
          <div class="col">{{ $t('eventAdd.form.additionalInfo') }}</div>
          <textarea class="col" />
        </div>
      </div>
      <div class="d-flex gap-2">
        <button type="button" class="btn btn-secondary btn-sm">
          {{ $t('eventAdd.form.buttons.back') }}
        </button>
        <button type="button" class="btn btn-primary btn-sm">
          {{ $t('eventAdd.form.buttons.add') }}
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  data() {
    return {}
  }
})
</script>
