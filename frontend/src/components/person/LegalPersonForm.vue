<template>
  <div>
    <div class="grid">
      <div class="row mb-2">
        <div class="col-4">{{ $t('personAdd.companyName') }}</div>
        <div class="col">
          <input
            v-model="form.companyName"
            class="w-100"
            type="text"
            :class="{
              error: v$.form.companyName.$error
            }"
          />
        </div>
      </div>
      <div class="row mb-2">
        <div class="col-4">{{ $t('personAdd.companyCode') }}</div>
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
      <div class="row mb-2">
        <div class="col-4">{{ $t('personAdd.participantCount') }}</div>
        <div class="col">
          <input
            v-model="form.participantCount"
            class="w-100"
            type="number"
            :class="{
              error: v$.form.participantCount.$error
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
        companyName: null as string | null,
        personCode: null as string | null,
        participantCount: null as number | null,
        paymentType: null as string | null,
        additionalInfo: ''
      },
      maxAdditionalInfoLength: 5000
    }
  },
  validations() {
    return {
      form: {
        companyName: { required },
        personCode: {
          required,
          min: minLength(8),
          max: maxLength(8)
        },
        participantCount: { required },
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
      this.form.companyName = this.person.firstName
      this.form.personCode = this.person.personCode
      this.form.participantCount = this.person.participantCount
      this.form.paymentType = this.person.paymentType
      this.form.additionalInfo = this.person.additionalInfo
    }
  },
  methods: {
    async addPerson() {
      const isValid = await this.v$.$validate()
      if (isValid) {
        const request: PersonCreateRequest = {
          type: 'LEGAL',
          firstName: this.form.companyName!,
          personCode: this.form.personCode!,
          participantCount: this.form.participantCount!,
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
