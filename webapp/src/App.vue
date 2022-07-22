<template>
  <div id="root" class="full-height" :class="sizeClass">
    <div id="topbar" v-if="showTopbar">
      <div id="nav">
        <router-link to="/home">Home</router-link>
        <router-link to="/new-transaction">Add transaction</router-link>
        <router-link to="/accounts">Accounts</router-link>
      </div>
    </div>
    <div id="pageContainer" class="full-height">
      <router-view />
    </div>
    <!--router-link to="/newTransaction" v-if="showAddButton">
      <div class="mdc-fab" id="newTransactionButton">
        <div class="mdc-fab__ripple"></div>
        <i class="fas fa-plus fa-lg"></i>
      </div>
    </router-link-->
  </div>
</template>

<script lang="ts">
export default {
  name: "App",
  components: {},
  computed: {
    showAddButton(): boolean {
      switch(this.$route.name) {
        case 'Login':
        case 'NewTransaction':
          return false;
        default:
          return true;
      }
    },
    showTopbar(): boolean {
      switch(this.$route.name) {
        case 'Login':
          return false;
        default:
          return true;
      }
    },
    sizeClass(): string[] {
      const classes = [] as string[];
      const bpMobile = 720;
      const bpTablet = 1080;
      const width = window.innerWidth;
      if (width < bpMobile) {
        classes.push('mobile');
      }
      if (width >= bpMobile && width < bpTablet) {
        classes.push('tablet');
      }
      if (width >= bpTablet) {
        classes.push('desktop');
      }
      return classes;
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

#root {
  display: flex;
  flex-direction: column;
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

#pageContainer {
  width: 100%;
  max-width: $bp-medium;
  display: flex;
  flex-direction: column;
  background-color: $app-main-background;
  overflow: scroll;
}

#topbar {
  background-color: $app-primary;
  color: white;
  a {
    display: inline-block;
    padding: 1rem;
    &:hover {
      background-color: lighten($color: $app-primary, $amount: 3);
    }
  }
}
</style>
