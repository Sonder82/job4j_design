package ru.job4j.gc.prof;

/**
 * Данный класс описывает действие,
 * которое выполняет пузырьковую
 * сортировку.
 *
 */

public class BubbleSortAction implements UserAction {
    private final Output out;

    public BubbleSortAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Bubble sort";
    }

    @Override
    public boolean execute(Input input, Data data) {
        if (data != null) {
            new BubbleSort().sort(data);
        } else {
            out.println("There is no data. Nothing to sort.");
        }
        return true;
    }
}
