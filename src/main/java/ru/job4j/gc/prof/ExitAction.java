package ru.job4j.gc.prof;

public class ExitAction  implements UserAction {
    private final Output out;

    public ExitAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Exit";
    }

    @Override
    public boolean execute(Input input, Data data) {
        out.println("EXITING PROGRAM...BYE");
        return false;
    }
}
