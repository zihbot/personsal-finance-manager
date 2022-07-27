<template>
  <div class="category-icon">
    <i class="fas fa-question"></i>
  </div>
</template>

<script lang='ts'>
import { Vue } from 'vue-class-component'

export default class CategoryIcon extends Vue {

}
</script>

<style lang='scss' scoped>
.category-icon {
  width: 3rem;
  height: 3rem;
  font-size: 2rem;
  color: rgb(233, 233, 233);
  border-radius: 50%;
  background-color: rgb(76, 76, 190);
  margin: auto;
  display: flex;
  i {
    margin: auto;
  }
}
</style>