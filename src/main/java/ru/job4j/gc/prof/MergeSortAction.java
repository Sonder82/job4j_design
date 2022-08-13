package ru.job4j.gc.prof;

public class MergeSortAction implements UserAction {
    private final Output out;

    public MergeSortAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "MergeSort";
    }

    @Override
    public boolean execute(Input input, Data data) {
        if (data != null) {
            new MergeSort().sort(data);
        } else {
            out.println("There is no data. Nothing to sort.");
        }
        return true;
    }
}
