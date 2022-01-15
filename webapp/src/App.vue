<template>
  <ui-grid class="main-grid">
    <div id="nav">
      <router-link to="/">Home</router-link>
    </div>
    <router-view />
    <router-link to="/newTransaction">
      <ui-fab style="position: absolute; bottom: 100px; right: 100px">
        <i class="fas fa-plus fa-lg"></i>
      </ui-fab>
    </router-link>
  </ui-grid>
</template>

<script>
export default {
  name: "App",
  components: {},
};
</script>

<style lang="scss">
#app {
  font-family: Roboto, Helvetica, Arial, sans-serif;

  a {
    text-decoration: none;
  }
}
</style>
