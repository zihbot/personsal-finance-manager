<template>
  <div class="mdc-layout-grid full-height">
  <div class="mdc-layout-grid__inner full-height" style="grid-template-rows: max-content">
    <div class="mdc-layout-grid__cell--span-12">
      <div id="nav">
        <router-link to="/">Home</router-link>
      </div>
    </div>
    <div id="mainContainer" class="mdc-layout-grid__cell--span-12 full-height">
      <router-view />
    </div>
    <router-link to="/newTransaction" v-if="notOnNewTransaction">
      <div class="mdc-fab" id="newTransactionButton">
        <div class="mdc-fab__ripple"></div>
        <i class="fas fa-plus fa-lg"></i>
      </div>
    </router-link>
  </div>
  </div>
</template>

<script lang="ts">
export default {
  name: "App",
  components: {},
  computed: {
    notOnNewTransaction(): boolean {
      return this.$route.name !== 'NewTransaction';
    }
  }
};
</script>

<style lang="scss">
@import "styles/main.scss";

#app {
  font-family: Roboto, Helvetica, Arial, sans-serif;

  a {
    text-decoration: none;
  }
}

#newTransactionButton {
  position: absolute;
  bottom: 10px;
  right: 10px;

  @include desktop {
    bottom: 100px;
    right: 100px;
  }
}

#mainContainer {
  width: 100%;

  @include desktop {
    width: $bp-medium;
    margin: 0 auto;
  }
}
</style>
