import { describe, it, expect } from 'vitest'

import { mount } from '@vue/test-utils'
import AppFooter from '../AppFooter.vue'

describe('AppFooter', () => {
  it('shows required content', () => {
    const wrapper = mount(AppFooter)
    expect(wrapper.text()).toContain('Footer')
  })
})
