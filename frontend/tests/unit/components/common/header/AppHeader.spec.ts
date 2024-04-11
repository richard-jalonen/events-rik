import { describe, expect, it } from 'vitest'

import { mount } from '@vue/test-utils'
import AppHeader from '../../../../../src/components/common/header/AppHeader.vue'

describe('AppHeader', () => {
  it('contains images', () => {
    const wrapper = mount(AppHeader)
    const images = wrapper.findAll('img')
    expect(images.length).toBe(2)
  })

  it('contains links', () => {
    const wrapper = mount(AppHeader)
    const links = wrapper.findAll('router-link-stub')
    expect(links.length).toBe(2)
  })
})
