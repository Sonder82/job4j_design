package ru.job4j.ood.dip.first.incorrect;

/**
 * Project модуль верхнего уровня и зависит от модулей нижнего уровня.
 * Это модули BackEndDeveloper и FrontEndDeveloper.
 * Это нарушает принцип инверсии зависимостей.
 * В поле зависимость от реализации.
 */

public class Project {
    private BackEndDeveloper backEndDeveloper = new BackEndDeveloper();
    private FrontEndDeveloper frontEndDeveloper = new FrontEndDeveloper();

    public void implement() {
        backEndDeveloper.writeJava();
        frontEndDeveloper.writeJavaScript();
    }
}
