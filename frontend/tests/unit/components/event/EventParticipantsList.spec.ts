import { mount } from '@vue/test-utils'
import { describe, expect, it } from 'vitest'
import EventParticipantsList from '../../../../src/components/event/EventParticipantsList.vue'
import { Participant } from '../../../../src/models/Participant'

describe('EventParticipantsList', () => {
  const participants: Participant[] = [
    {
      uuid: 'c0433dfb-da86-476e-ab81-a313bbebadde',
      type: 'PRIVATE',
      paymentType: 'Payment Type 1',
      firstName: 'John',
      lastName: 'Doe',
      personCode: '39209070000',
      participantCount: 1,
      additionalInfo: 'Additional Info 1'
    },
    {
      uuid: '3210d51b-4aa1-4d32-9462-b11e87a11d05',
      type: 'PRIVATE',
      paymentType: 'Payment Type 2',
      firstName: 'Jane',
      lastName: 'Smith',
      personCode: '39109070000',
      participantCount: 2,
      additionalInfo: 'Additional Info 2'
    }
  ]

  it('displays participant list', async () => {
    const wrapper = mount(EventParticipantsList, {
      props: {
        participants: participants
      },
      global: {
        stubs: {
          BModal: true
        }
      }
    })

    const values = wrapper.findAll('.row div').map((value) => value.text())
    expect(values).toEqual([
      '1.',
      'John Doe',
      '39209070000',
      'eventEdit.buttons.delete',
      '2.',
      'Jane Smith',
      '39109070000',
      'eventEdit.buttons.delete'
    ])
  })
})
