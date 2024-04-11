import { browser } from 'nightwatch'

describe('Event tests', function () {
  before((browser) => {
    browser.init()
  })

  it('creates event', function () {
    browser.element.find('#add-event').click()
    browser.element.find('#event-name').sendKeys('test event')
    browser.element.find('#event-time').setValue(new Date())
    browser.element.find('#event-location').sendKeys('test location')
    browser.element.find('#event-info').sendKeys('test additional info')
    browser.element.find('#add-event-btn').click()

    browser.assert.textContains('#upcoming-events', 'test event')
  })

  after((browser) => browser.end())
})
