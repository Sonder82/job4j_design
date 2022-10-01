package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportForIT implements Report {

    private Store store;

    public ReportForIT(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<html>")
                .append(Constants.LINE_SEPARATOR)
                .append("<head>")
                .append(Constants.LINE_SEPARATOR)
                .append("<title>Name; Hired; Fired; Salary;</title>")
                .append(Constants.LINE_SEPARATOR)
                .append("</head>")
                .append(Constants.LINE_SEPARATOR)
                .append("<body>")
                .append(Constants.LINE_SEPARATOR);
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(Constants.DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(Constants.DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(Constants.LINE_SEPARATOR);
        }
        text.append("</body>")
                .append(Constants.LINE_SEPARATOR)
                .append("</html>")
                .append(Constants.LINE_SEPARATOR);
        return text.toString();
    }
}
