package ru.job4j.gc.prof;

public class InsertSortAction implements UserAction {

    private final Output out;

    public InsertSortAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "InsertSort";
    }

    @Override
    public boolean execute(Input input, Data data) {
        if (data != null) {
            new InsertSort().sort(data);
        } else {
            out.println("There is no data. Nothing to sort");
        }
        return true;
    }
}
