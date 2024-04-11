<template>
  <div>
    <h4 class="text-blue fw-light">{{ $t('eventAdd.header') }}</h4>
    <div class="grid my-5">
      <div class="row mb-2">
        <div class="col-4">{{ $t('eventAdd.form.eventName') }}</div>
        <input
          v-model="form.eventName"
          class="col"
          :class="{
            error: v$.form.eventName.$error
          }"
        />
      </div>
      <div class="row mb-2">
        <div class="col-4">{{ $t('eventAdd.form.eventTime') }}</div>
        <input
          v-model="form.eventTime"
          class="col"
          type="datetime-local"
          :class="{
            error: v$.form.eventTime.$error
          }"
        />
      </div>
      <div class="row mb-2">
        <div class="col-4">{{ $t('eventAdd.form.location') }}</div>
        <input
          v-model="form.eventLocation"
          class="col"
          :class="{
            error: v$.form.eventLocation.$error
          }"
        />
      </div>
      <div class="row">
        <div class="col-4">{{ $t('eventAdd.form.additionalInfo') }}</div>
        <textarea v-model="form.eventAdditionalInfo" class="col" />
      </div>
    </div>
    <div class="d-flex gap-2">
      <button type="button" class="btn btn-secondary btn-sm" @click="router.go(-1)">
        {{ $t('eventAdd.form.buttons.back') }}
      </button>
      <button type="button" class="btn btn-primary btn-sm" @click="addEvent">
        {{ $t('eventAdd.form.buttons.add') }}
      </button>
    </div>
  </div>
</template>

<script lang="ts">
import { required } from '@vuelidate/validators'
import ApiClient from '@/client/api.client'
import FormatUtil from '@/util/format.util'
import useVuelidate from '@vuelidate/core'
import { defineComponent } from 'vue'
import router from '@/router'

const isFutureTime = (value: string) => {
  return FormatUtil.isFutureTime(value)
}

export default defineComponent({
  data() {
    return {
      v$: useVuelidate(),
      isFutureTime,
      router,
      FormatUtil,
      form: {
        eventName: null,
        eventTime: null,
        eventLocation: null,
        eventAdditionalInfo: ''
      }
    }
  },
  validations() {
    return {
      form: {
        eventName: { required },
        eventTime: {
          required,
          isFutureTime
        },
        eventLocation: { required }
      }
    }
  },
  methods: {
    async addEvent() {
      const isValid = await this.v$.$validate()
      if (isValid) {
        await ApiClient.createEvent({
          name: this.form.eventName!,
          time: this.form.eventTime!,
          location: this.form.eventLocation!,
          additionalInfo: this.form.eventAdditionalInfo
        })
        await router.push({ name: 'Home' })
      }
    }
  }
})
</script>
