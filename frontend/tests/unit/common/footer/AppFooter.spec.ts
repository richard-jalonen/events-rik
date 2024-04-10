import { describe, expect, it } from 'vitest'

import { shallowMount } from '@vue/test-utils'
import AppFooter from '../../../../src/components/common/footer/AppFooter.vue'

describe('AppFooter', () => {
  it('contains sub-components', () => {
    const wrapper = shallowMount(AppFooter)

    const generalInfoComponents = wrapper.findAll('general-info-stub')
    const contactInfoComponents = wrapper.findAll('contact-info-stub')

    expect(generalInfoComponents.length).toBe(2)
    expect(contactInfoComponents.length).toBe(1)
  })
})
