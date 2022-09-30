package ru.job4j.ood.ocp.third.right;

public class BonusEmployee {
    public void checkCount(Employee employee, Bonus bonus) {
        bonus.calculateBonus(employee);
    }
}
