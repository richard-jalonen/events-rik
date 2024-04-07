<template>
  <div>
    <div class="grid">
      <div class="row mb-2">
        <div class="col-4">{{ $t('personAdd.firstName') }}</div>
        <div class="col">
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
        <div class="col">
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
        <div class="col">
          <input
            class="w-100"
            v-model="form.personCode"
            type="number"
            :class="{
              error: v$.form.personCode.$error
            }"
          />
        </div>
      </div>
      <div class="row mb-3">
        <div class="col-4">{{ $t('personAdd.paymentType') }}</div>
        <div class="col">
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
        <div class="col">
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
import type { Person, PersonCreateRequest } from '@/models/Person'

export default defineComponent({
  setup() {
    return { v$: useVuelidate() }
  },
  props: {
    eventUuid: {
      type: String,
      required: false
    },
    person: {
      type: Object as () => Person,
      required: false
    }
  },
  data() {
    return {
      router,
      form: {
        firstName: null as string | null,
        lastName: null as string | null,
        personCode: null as string | null,
        paymentType: null as string | null,
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
  beforeMount() {
    if (this.person) {
      this.form.firstName = this.person.firstName
      this.form.lastName = this.person.lastName
      this.form.personCode = this.person.personCode
      this.form.paymentType = this.person.paymentType
      this.form.additionalInfo = this.person.additionalInfo
    }
  },
  methods: {
    async addPerson() {
      const isValid = await this.v$.$validate()
      if (isValid) {
        const request: PersonCreateRequest = {
          type: 'PRIVATE',
          firstName: this.form.firstName!,
          lastName: this.form.lastName!,
          personCode: this.form.personCode!,
          paymentType: this.form.paymentType!,
          additionalInfo: this.form.additionalInfo
        }
        if (!this.person) {
          await ApiClient.createPerson(this.eventUuid, request)
          router.go(0)
        } else {
          await ApiClient.updatePerson({ ...request, uuid: this.person.uuid })
          router.go(-1)
        }
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
