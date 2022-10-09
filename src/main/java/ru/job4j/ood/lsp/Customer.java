package ru.job4j.ood.lsp;

/**
 * Нарушение контракта LSP на примере, где постусловие было ослаблено в подклассе.
 * Обычный Customer не может выполнить обмен, если на остатках меньше 5.
 * Для CityCustomer забыли сделать необходимый минимум на остаток.
 * CityCustomer получил "послабление" которого не должно быть.-
 */

public class Customer {
    private double account;

    public Customer(double account) {
        this.account = account;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public double chargeMoney(double sum) {

        if (getAccount() - sum < 10 && sum <= 0) {
            throw new IllegalArgumentException("Недостаточно средств");
        }
        return sum * 0.95;
    }

    class CityCustomer extends Customer {

        public CityCustomer(double account) {
            super(account);
        }

        @Override
        public double chargeMoney(double sum) {

            if (getAccount() - sum < 0 && sum <= 0) {
                throw new IllegalArgumentException("Недостаточно средств");
            }
            return sum * 0.97;
        }
    }
}
