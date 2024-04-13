import { flushPromises, mount } from '@vue/test-utils'
import { createPinia, setActivePinia } from 'pinia'
import { beforeEach, describe, expect, it, vi } from 'vitest'
import ApiClient from '../../../../src/client/api.client'
import LegalParticipantForm from '../../../../src/components/participant/LegalParticipantForm.vue'

vi.mock('@/stores/payment', () => ({
  usePaymentStore: vi.fn(() => ({
    getPaymentOptions: ['cash', 'card'] // Mocked payment options
  }))
}))

describe('LegalParticipantForm', () => {
  beforeEach(() => {
    setActivePinia(createPinia())
  })

  it('validates form inputs correctly', async () => {
    const mockRouter = {
      go: vi.fn()
    }
    const createParticipantMock = (ApiClient.createParticipant = vi.fn())

    const wrapper = mount(LegalParticipantForm, {
      global: {
        mocks: {
          $router: mockRouter
        }
      }
    })

    const nameInput = wrapper.find('#name')
    const codeInput = wrapper.find('#code')
    const count = wrapper.find('#count')
    const paymentType = wrapper.find('#payment-type')
    const additionalInfo = wrapper.find('#additional-info')
    const addButton = wrapper.find('#add-participant-btn')
    await addButton.trigger('click')
    expect(wrapper.vm.v$.form.$error).toBe(true)

    await nameInput.setValue('Tiit Teet OY')
    await codeInput.setValue('12345678')
    await count.setValue(55)
    await paymentType.setValue('cash')
    await additionalInfo.setValue('additional info')
    await addButton.trigger('click')

    await flushPromises()
    expect(wrapper.vm.v$.form.$error).toBe(false)
    expect(createParticipantMock.mock.calls.length).toBe(1)
  })
})
