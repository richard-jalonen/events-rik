<template>
  <div>
    <div class="grid">
      <div class="row mb-2">
        <div class="col-4">{{ $t('personAdd.firstName') }}</div>
        <div class="col-4">
          <input
            class="w-100"
            v-model="form.firstName"
            type="text"
            :class="{
              error: v$.form.firstName.$error
            }"
          />
        </div>
      </div>
      <div class="row mb-2">
        <div class="col-4">{{ $t('personAdd.lastName') }}</div>
        <div class="col-4">
          <input
            class="w-100"
            v-model="form.lastName"
            type="text"
            :class="{
              error: v$.form.lastName.$error
            }"
          />
        </div>
      </div>
      <div class="row mb-2">
        <div class="col-4">{{ $t('personAdd.personCode') }}</div>
        <div class="col-4">
          <input
            class="w-100"
            v-model="form.personCode"
            type="text"
            :class="{
              error: v$.form.personCode.$error
            }"
          />
        </div>
      </div>
      <div class="row mb-2">
        <div class="col-4">{{ $t('personAdd.paymentType') }}</div>
        <div class="col-4">
          <select
            class="w-100"
            v-model="form.paymentType"
            :class="{
              error: v$.form.paymentType.$error
            }"
          >
            <option v-for="(option, index) of paymentOptions" :key="index">{{ option }}</option>
          </select>
        </div>
      </div>
      <div class="row mb-2">
        <div class="col-4">{{ $t('personAdd.additionalInfo') }}</div>
        <div class="col-4">
          <textarea class="w-100" v-model="form.additionalInfo" type="text" />
        </div>
      </div>
    </div>
    <div class="d-flex gap-2 mt-4">
      <button type="button" class="btn btn-secondary btn-sm" @click="router.go(-1)">
        {{ $t('personAdd.buttons.back') }}
      </button>
      <button type="button" class="btn btn-primary btn-sm" @click="addPerson">
        {{ $t('personAdd.buttons.save') }}
      </button>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import router from '@/router'
import useVuelidate from '@vuelidate/core'
import { required } from '@vuelidate/validators'
import ApiClient from '@/client/api.client'
import { usePaymentStore } from '@/stores/payment'

export default defineComponent({
  setup() {
    return { v$: useVuelidate() }
  },
  props: {
    eventUuid: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      router,
      form: {
        firstName: null,
        lastName: null,
        personCode: null,
        paymentType: null,
        additionalInfo: ''
      }
    }
  },
  validations() {
    return {
      form: {
        firstName: { required },
        lastName: { required },
        personCode: { required },
        paymentType: { required }
      }
    }
  },
  methods: {
    async addPerson() {
      const isValid = await this.v$.$validate()
      if (isValid) {
        await ApiClient.createPerson(this.eventUuid, {
          type: 'PRIVATE',
          firstName: this.form.firstName!,
          lastName: this.form.lastName!,
          personCode: this.form.personCode!,
          paymentType: this.form.paymentType!,
          additionalInfo: this.form.additionalInfo
        })
        router.go(0)
      }
    }
  },
  computed: {
    paymentOptions() {
      return usePaymentStore().getPaymentOptions
    }
  }
})
</script>
