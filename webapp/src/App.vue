<template>
  <MDBContainer>
    <div id="nav">
      <router-link to="/">Home</router-link>
    </div>
    <router-view />
    <MDBBtn color="primary" floating>
      <MDBIcon icon="download"></MDBIcon>
    </MDBBtn>
  </MDBContainer>
</template>

<script>
import { MDBContainer, MDBBtn, MDBIcon } from "mdb-vue-ui-kit";

export default {
  name: "App",
  components: {
    MDBContainer,
    MDBBtn,
    MDBIcon,
  },
};
</script>

<style lang="scss">
@import "~@/../mdb/scss/index.free.scss";

#app {
  font-family: Roboto, Helvetica, Arial, sans-serif;
}
</style>
