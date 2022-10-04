package ru.job4j.ood.ocp;

import java.util.ArrayList;

public class EmployeeBonus {

    /**
     * Нарушает принцип OCP, т.к. поле класса не является абстракцией, а имеет конкретную реализацию.
     *
     */
    private ArrayList<Employee> employees = new ArrayList<>();

    public EmployeeBonus(ArrayList<Employee> employees) {
        this.employees = employees;
    }


    /**
     *Нарушение OCP вызвано тем что передали в метод специфичный конкретный тип.
     * Вместо этого необходимо передать абстракцию.
     * Правильную реализацию попробовал сделать в примере ocp/third/right
     */
    public double countBonusPer(PermanentEmployeeBonus permanentEmployeeBonus) {
        return permanentEmployeeBonus.calculateBonus();
    }

    public double countBonusTemp(TemporaryEmployeeBonus temporaryEmployeeBonus) {
        return temporaryEmployeeBonus.calculateBonus();
    }

}
