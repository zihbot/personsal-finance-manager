import utils from "./utils";

export class Filters {
  money(value: number) {
    return utils.formatMoney(value);
  }
}

export default new Filters();