<template>
  <ui-grid class="main-grid">
    <ui-grid-cell class="main-cell" columns="12">
      <div id="nav">
        <router-link to="/">Home</router-link>
      </div>
    </ui-grid-cell>
    <ui-grid-cell class="main-cell" columns="12">
      <router-view />
    </ui-grid-cell>
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
@import 'styles/_custom_material.scss';

#app {
  font-family: Roboto, Helvetica, Arial, sans-serif;

  a {
    text-decoration: none;
  }
}
</style>
