import { Filters } from './services/filters';

/* eslint-disable */
declare module '@vue/runtime-core' {
  interface ComponentCustomProperties {
    $filter: Filters;
  }
}

/* eslint-disable */
declare module '*.vue' {
  import Vue from "vue";
  export default Vue;
}
