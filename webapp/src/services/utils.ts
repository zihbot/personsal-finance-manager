
class Utils {
  formatMoney(amount: number) {
    const realAmount = amount / 100;
    return realAmount.toFixed(2) + " Ft";
  }
}

export default new Utils();