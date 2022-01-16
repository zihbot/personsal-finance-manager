<template>
  <h1 :class="$tt('page-name')">New transaction</h1>
  <p :class="$tt('page-name')"
    helper-text-id="my-text-field-helper-text"
  >
    0
  </p>
</template>

<script lang="ts">
import { Options, Vue } from "vue-class-component";

@Options({
  components: {},
})
export default class NewTransaction extends Vue {}
</script>
