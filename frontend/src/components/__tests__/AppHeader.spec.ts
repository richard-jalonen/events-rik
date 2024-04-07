import { describe, it, expect } from 'vitest'

import AppHeader from '../common/header/AppHeader.vue'
import { mount } from '@vue/test-utils'

describe('AppHeader', () => {
  it('shows required content', () => {
    const wrapper = mount(AppHeader)
    expect(wrapper.text()).toContain('Header')
  })
})
