import { flushPromises, mount } from '@vue/test-utils'
import { describe, expect, it, vi } from 'vitest'
import ApiClient from '../../../../src/client/api.client'
import EventParticipants from '../../../../src/components/event/EventParticipants.vue'

describe('EventParticipants', () => {
  const mockEventUuid = '07e91a11-fa90-4498-85eb-b92d43534db8'
  const mockRoute = {
    params: {
      id: mockEventUuid
    }
  }
  const mockEventData = {
    name: 'Test Event',
    time: '2024-04-13T12:00:00',
    location: 'Test Location'
  }

  it('displays event data', async () => {
    ApiClient.getEvent = vi.fn().mockResolvedValue({ data: mockEventData })

    const wrapper = mount(EventParticipants, {
      global: {
        stubs: {
          EventParticipantsList: true
        },
        mocks: {
          $route: mockRoute
        }
      }
    })

    await flushPromises()
    expect(ApiClient.getEvent).toHaveBeenCalledWith(mockEventUuid)
    expect(wrapper.text()).toContain('Test Event')
    expect(wrapper.text()).toContain('4/13/2024')
    expect(wrapper.text()).toContain('Test Location')
  })
})
