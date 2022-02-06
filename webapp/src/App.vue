<template>
  <div class="mdc-layout-grid">
  <div class="mdc-layout-grid__inner">
    <div class="mdc-layout-grid__cell--span-12">
      <div id="nav">
        <router-link to="/">Home</router-link>
      </div>
    </div>
    <div class="mdc-layout-grid__cell--span-12">
      <router-view />
    </div>
    <router-link to="/newTransaction">
      <div class="mdc-fab" id="newTransactionButton">
        <div class="mdc-fab__ripple"></div>
        <i class="fas fa-plus fa-lg"></i>
      </div>
    </router-link>
  </div>
  </div>
</template>

<script>
export default {
  name: "App",
  components: {},
};
</script>

<style lang="scss">
@import "styles/main.scss";

#app {
  font-family: Roboto, Helvetica, Arial, sans-serif;

  a {
    text-decoration: none;
  }

  #newTransactionButton {
    position: absolute;
    bottom: 100px;
    right: 100px;
  }
}
</style>
