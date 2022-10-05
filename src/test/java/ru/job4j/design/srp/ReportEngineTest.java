package ru.job4j.design.srp;

import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportEngineTest {

    @Test
    void whenOldGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(Constants.LINE_SEPARATOR)
                .append(worker.getName()).append(";")
                .append(Constants.DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(Constants.DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(Constants.LINE_SEPARATOR);
        assertThat(engine.generate(employee -> true)).isEqualTo(expect.toString());

    }

    @Test
    void generateForAccounting() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportForAccounting(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; SalaryGross; SalaryTotal")
                .append(Constants.LINE_SEPARATOR)
                .append(worker.getName()).append(";")
                .append(Constants.DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(Constants.DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(worker.getSalary() * Constants.SALARY_TAX).append(";")
                .append(Constants.LINE_SEPARATOR);
        assertThat(engine.generate(employee -> worker.getSalary() > 80)).isEqualTo(expect.toString());
    }

    @Test
    void generateForHR() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee workerFirst = new Employee("Ivan", now, now, 100);
        Employee workerSecond = new Employee("Aleksey", now, now, 200);
        store.add(workerFirst);
        store.add(workerSecond);
        Report engine = new ReportForHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; SalaryGross")
                .append(Constants.LINE_SEPARATOR)
                .append(workerSecond.getName()).append(";")
                .append(workerSecond.getSalary()).append(";")
                .append(Constants.LINE_SEPARATOR)
                .append(workerFirst.getName()).append(";")
                .append(workerFirst.getSalary()).append(";")
                .append(Constants.LINE_SEPARATOR);
        assertThat(engine.generate(employee -> true)).isEqualTo(expect.toString());
    }

    @Test
    void generateForIT() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportForIT(store);
        StringBuilder expect = new StringBuilder()
                .append("<html>")
                .append(Constants.LINE_SEPARATOR)
                .append("<head>")
                .append(Constants.LINE_SEPARATOR)
                .append("<title>Name; Hired; Fired; Salary;</title>")
                .append(Constants.LINE_SEPARATOR)
                .append("</head>")
                .append(Constants.LINE_SEPARATOR)
                .append("<body>")
                .append(Constants.LINE_SEPARATOR)
                .append(worker.getName()).append(";")
                .append(Constants.DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(Constants.DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(Constants.LINE_SEPARATOR)
                .append("</body>")
                .append(Constants.LINE_SEPARATOR)
                .append("</html>")
                .append(Constants.LINE_SEPARATOR);
        assertThat(engine.generate(employee -> true)).isEqualTo(expect.toString());

    }

    @Test
    void generateForJSON() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportForJSON(store);
        StringBuilder expect = new StringBuilder()
                .append("{\"name\":\"")
                .append(worker.getName()).append("\",")
                .append("\"hired\":{")
                .append(Constants.DATE_FORMAT.format(worker.getHired().getTime())).append(",")
                .append("\"fired\":{")
                .append(worker.getFired()).append(",")
                .append("\"salary\":\"")
                .append(worker.getSalary()).append("}");
        assertThat(engine.generate(employee -> true)).isEqualTo(expect.toString());

    }
}