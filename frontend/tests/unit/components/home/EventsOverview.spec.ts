import { mount } from '@vue/test-utils'
import { randomUUID } from 'node:crypto'
import { describe, expect, it } from 'vitest'
import EventsOverview from '../../../../src/components/home/EventsOverview.vue'
import { Event } from '../../../../src/models/Event'

describe('EventsOverview', () => {
  const mockEventData: Event[] = [
    {
      uuid: randomUUID(),
      name: 'Test Event',
      time: '2024-04-13T12:00:00',
      location: 'Test Location',
      participants: []
    },
    {
      uuid: randomUUID(),
      name: 'Test Event 2',
      time: '2024-06-22T12:00:00',
      location: 'Test Location 2',
      participants: []
    }
  ]

  it('displays event list', async () => {
    const wrapper = mount(EventsOverview, {
      props: {
        translations: {},
        events: mockEventData,
        canDeleteEvents: true
      },
      global: {
        stubs: {
          BModal: true
        }
      }
    })

    const values = wrapper.findAll('.row .col').map((value) => value.text())
    const deleteSvgs = wrapper.findAll('img')

    expect(values).toEqual(['Test Event', '4/13/2024', '', 'Test Event 2', '6/22/2024', ''])
    expect(deleteSvgs.length).toBe(2)
  })
})
