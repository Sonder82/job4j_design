package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.function.Predicate;

public class ReportForJSON implements Report {
    private Store store;

    public ReportForJSON(Store store) {
        this.store = store;
    }

    final Gson gson = new GsonBuilder().create();

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();

        for (Employee employee : store.findBy(filter)) {
            text.append(gson.toJson(employee));
        }

        return text.toString();
    }
}
