import { describe, it, expect } from 'vitest'

import AppFooter from '../common/footer/AppFooter.vue'
import { mount } from '@vue/test-utils'

describe('AppFooter', () => {
  it('shows required content', () => {
    const wrapper = mount(AppFooter)
    expect(wrapper.text()).toContain('Footer')
  })
})
