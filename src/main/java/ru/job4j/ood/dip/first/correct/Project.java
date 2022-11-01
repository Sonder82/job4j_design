package ru.job4j.ood.dip.first.correct;

import java.util.List;

/**
 * Сейчас Project не зависит от модулей нижнего уровня BackEndDeveloper и FrontEndDeveloper.
 * Зависит от абстракции Developer.
 */

public class Project {
    private List<Developer> developers;

    public Project(List<Developer> developers) {
        this.developers = developers;
    }

    public void implement() {
        developers.forEach(developer -> developer.develop());
    }
}
