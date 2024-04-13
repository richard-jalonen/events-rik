import { flushPromises, mount } from '@vue/test-utils'
import { describe, expect, it, vi } from 'vitest'
import ApiClient from '../../../../src/client/api.client'
import EventAddForm from '../../../../src/components/event/EventAddForm.vue'

describe('EventAddForm', () => {
  it('validates form inputs correctly', async () => {
    const createEventMock = (ApiClient.createEvent = vi.fn())

    const wrapper = mount(EventAddForm)

    const eventNameInput = wrapper.find('#event-name')
    const eventTimeInput = wrapper.find('#event-time')
    const eventLocationInput = wrapper.find('#event-location')
    const addButton = wrapper.find('#add-event-btn')
    await addButton.trigger('click')
    expect(wrapper.vm.v$.form.$error).toBe(true)

    await eventNameInput.setValue('Test Event')
    await eventTimeInput.setValue('3024-04-13T12:00')
    await eventLocationInput.setValue('Test Location')
    await addButton.trigger('click')
    await flushPromises()
    expect(wrapper.vm.v$.form.$error).toBe(false)
    expect(createEventMock.mock.calls.length).toBe(1)
  })
})
