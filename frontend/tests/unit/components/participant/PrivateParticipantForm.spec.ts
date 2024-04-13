import { flushPromises, mount } from '@vue/test-utils'
import { createPinia, setActivePinia } from 'pinia'
import { beforeEach, describe, expect, it, vi } from 'vitest'
import ApiClient from '../../../../src/client/api.client'
import PrivateParticipantForm from '../../../../src/components/participant/PrivateParticipantForm.vue'

vi.mock('@/stores/payment', () => ({
  usePaymentStore: vi.fn(() => ({
    getPaymentOptions: ['cash', 'card'] // Mocked payment options
  }))
}))

describe('PrivateParticipantForm', () => {
  beforeEach(() => {
    setActivePinia(createPinia())
  })

  it('validates form inputs correctly', async () => {
    const mockRouter = {
      go: vi.fn()
    }
    const createParticipantMock = (ApiClient.createParticipant = vi.fn())

    const wrapper = mount(PrivateParticipantForm, {
      global: {
        mocks: {
          $router: mockRouter
        }
      }
    })

    const firstNameInput = wrapper.find('#first-name')
    const lastNameInput = wrapper.find('#last-name')
    const codeInput = wrapper.find('#code')
    const paymentType = wrapper.find('#payment-type')
    const additionalInfo = wrapper.find('#additional-info')
    const addButton = wrapper.find('#add-participant-btn')
    await addButton.trigger('click')
    expect(wrapper.vm.v$.form.$error).toBe(true)

    await firstNameInput.setValue('Chuck')
    await lastNameInput.setValue('Norris')
    await codeInput.setValue('39209070000')
    await paymentType.setValue('card')
    await additionalInfo.setValue('additional info')
    await addButton.trigger('click')
    await flushPromises()
    expect(wrapper.vm.v$.form.$error).toBe(false)
    expect(createParticipantMock.mock.calls.length).toBe(1)
  })
})
