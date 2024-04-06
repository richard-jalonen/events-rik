<template>
  <div class="bg-light">
    <h4 class="bg-blue text-light text-center fw-lighter py-3">{{ translations.header }}</h4>
    <div class="p-3">
      <div>
        <div v-for="(event, index) of events" :key="event.uuid" class="row">
          <div class="col-1">
            {{ index + 1 }}
          </div>
          <div class="col">
            {{ event.name }}
          </div>
          <div class="col">
            {{ FormatUtil.formatDate(event.time) }}
          </div>
          <div class="col d-flex flex-row gap-2">
            <router-link :to="{ path: `/event/${event.uuid}` }"
              ><span class="text-dark-gray">{{ translations.participants }}</span></router-link
            >
            <img
              v-if="canDeleteEvents"
              :src="removeSvg"
              alt="remove"
              @load="true"
              style="max-width: 1em"
            />
          </div>
        </div>
      </div>
      <div class="mt-4">
        <router-link to="/event"
          ><span class="text-dark-gray">{{ translations.addEvent }}</span></router-link
        >
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import type { Event } from '@/models/Event'
import FormatUtil from '@/util/format.util'
import removeSvg from '@/assets/images/remove.svg'

export default defineComponent({
  props: {
    translations: {
      type: Object,
      required: true
    },
    events: {
      type: Array as () => Event[],
      required: true
    },
    canDeleteEvents: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      FormatUtil,
      removeSvg
    }
  }
})
</script>
