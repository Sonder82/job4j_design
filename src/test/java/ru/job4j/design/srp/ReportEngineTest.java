package ru.job4j.design.srp;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportEngineTest {

    @Test
    void whenOldGenerated() {
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
    void generateForAccounting() {
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
    void generateForHR() {
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
    void generateForIT() {
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
    void generateForJSON() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportForJSON(store);
        String expect = String.format("[{\"name\":\"%s", worker.getName())
                .concat(String.format("\",\"hired\":{\"year\":%s", now.get(Calendar.YEAR)))
                .concat(String.format(",\"month\":%s", now.get(Calendar.MONTH)))
                .concat(String.format(",\"dayOfMonth\":%s", now.get(Calendar.DAY_OF_MONTH)))
                .concat(String.format(",\"hourOfDay\":%s", now.get(Calendar.HOUR_OF_DAY)))
                .concat(String.format(",\"minute\":%s", now.get(Calendar.MINUTE)))
                .concat(String.format(",\"second\":%s", now.get(Calendar.SECOND)))
                .concat(String.format("},\"fired\":{\"year\":%s", now.get(Calendar.YEAR)))
                .concat(String.format(",\"month\":%s", now.get(Calendar.MONTH)))
                .concat(String.format(",\"dayOfMonth\":%s", now.get(Calendar.DAY_OF_MONTH)))
                .concat(String.format(",\"hourOfDay\":%s", now.get(Calendar.HOUR_OF_DAY)))
                .concat(String.format(",\"minute\":%s", now.get(Calendar.MINUTE)))
                .concat(String.format(",\"second\":%s", now.get(Calendar.SECOND)))
                .concat(String.format("},\"salary\":%s", worker.getSalary()))
                .concat("}]");
        assertThat(engine.generate(employee -> true)).isEqualTo(expect);
    }

    @Test
    void generateForXML() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        store.add(worker);
        Report xmlEngine = new ReportForXML(store);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                .append("<employees>\n")
                .append("    <employee>\n")
                .append("        <fired>").append(formater.format(worker.getFired()
                        .getTime())).append("</fired>\n")
                .append("        <hired>").append(formater.format(worker.getHired()
                        .getTime())).append("</hired>\n")
                .append("        <name>").append(worker.getName()).append("</name>\n")
                .append("        <salary>").append(worker.getSalary()).append("</salary>\n")
                .append("    </employee>\n")
                .append("</employees>\n");
        assertThat(xmlEngine.generate(em -> true)).isEqualTo(expect.toString());
    }
}