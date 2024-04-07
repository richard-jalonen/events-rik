<template>
  <div>
    <div class="grid">
      <div class="row mb-2">
        <div class="col-4">{{ $t('personAdd.firstName') }}</div>
        <div class="col">
          <input
            v-model="form.firstName"
            class="w-100"
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
            v-model="form.lastName"
            class="w-100"
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
            v-model="form.personCode"
            class="w-100"
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
            v-model="form.paymentType"
            class="w-100"
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
          <textarea
            v-model="form.additionalInfo"
            :maxlength="maxAdditionalInfoLength"
            class="w-100"
            type="text"
          />
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
import { maxLength, minLength, required } from '@vuelidate/validators'
import type { Person, PersonCreateRequest } from '@/models/Person'
import { usePaymentStore } from '@/stores/payment'
import ApiClient from '@/client/api.client'
import useVuelidate from '@vuelidate/core'
import { defineComponent } from 'vue'
import router from '@/router'

export default defineComponent({
  props: {
    eventUuid: {
      type: String,
      required: false,
      default: null
    },
    person: {
      type: Object as () => Person,
      required: false,
      default: null
    }
  },
  setup() {
    return { v$: useVuelidate() }
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
      },
      maxAdditionalInfoLength: 1500
    }
  },
  validations() {
    return {
      form: {
        firstName: { required },
        lastName: { required },
        personCode: {
          required,
          min: minLength(11),
          max: maxLength(11)
        },
        paymentType: { required },
        additionalInfo: { maxLength: maxLength(this.maxAdditionalInfoLength) }
      }
    }
  },
  computed: {
    paymentOptions() {
      return usePaymentStore().getPaymentOptions
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
          await ApiClient.createPerson(this.eventUuid!, request)
          router.go(0)
        } else {
          await ApiClient.updatePerson({ ...request, uuid: this.person.uuid })
          router.go(-1)
        }
      }
    }
  }
})
</script>
