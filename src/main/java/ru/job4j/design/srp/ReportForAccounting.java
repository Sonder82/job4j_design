package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportForAccounting implements Report {

    private Store store;

    public ReportForAccounting(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; SalaryGross; SalaryTotal")
                .append(Constants.LINE_SEPARATOR);
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(Constants.DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(Constants.DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(employee.getSalary() * Constants.SALARY_TAX).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
