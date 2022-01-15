<template>
  <h1 :class="$tt('headline1')">New transaction</h1>
  <ui-textfield
    helper-text-id="my-text-field-helper-text"
  >
    Email Address
  </ui-textfield>
</template>

<script lang="ts">
import { Options, Vue } from "vue-class-component";

@Options({
  components: {},
})
export default class NewTransaction extends Vue {}
</script>
