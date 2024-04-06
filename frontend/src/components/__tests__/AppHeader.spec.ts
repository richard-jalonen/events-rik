import { describe, it, expect } from 'vitest'

import { mount } from '@vue/test-utils'
import AppHeader from '../header/AppHeader.vue'

describe('AppHeader', () => {
  it('shows required content', () => {
    const wrapper = mount(AppHeader)
    expect(wrapper.text()).toContain('Header')
  })
})
