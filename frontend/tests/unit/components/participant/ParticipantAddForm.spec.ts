import { flushPromises, mount } from '@vue/test-utils'
import { randomUUID } from 'node:crypto'
import { describe, expect, it } from 'vitest'
import ParticipantAddForm from '../../../../src/components/participant/ParticipantAddForm.vue'

describe('ParticipantAddForm', () => {
  it('changes child component depending on radio button', async () => {
    const wrapper = mount(ParticipantAddForm, {
      props: {
        eventUuid: randomUUID()
      },
      global: {
        stubs: {
          LegalParticipantForm: true,
          PrivateParticipantForm: true
        }
      }
    })

    const header = wrapper.find('h4')
    const legalRadio = wrapper.find('#legal')
    const privateParticipantForm = wrapper.find('private-participant-form-stub')
    const legalParticipantForm = wrapper.find('legal-participant-form-stub')
    expect(header.text()).toEqual('participantAdd.header')
    expect(privateParticipantForm.exists()).toBeTruthy
    expect(legalParticipantForm.exists()).toBeFalsy

    await legalRadio.setValue(true)
    await flushPromises()
    expect(privateParticipantForm.exists()).toBeFalsy
    expect(legalParticipantForm.exists()).toBeTruthy
  })
})
