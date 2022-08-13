package ru.job4j.gc.prof;

import java.util.List;
import java.util.Random;


public class StartProf {

    private final Output output;

    public StartProf(Output output) {
        this.output = output;
    }

    /**
     * Данный метод инициализирует работу
     * приложения.
     * Так как мы вводим информацию
     * в консоли, то на входе {@link Input}.
     * Мы будем работать с данными, любыми.
     * Поэтому использовали интерфейс {@link Data}.
     * Все действия были собраны в
     * одном интерфейсе {@link UserAction}.
     * @param input - интерфейс для ввода информации.
     * @param data - данные, которые будем
     *             сортировать.
     * @param actions - все возможные действия
     *                нашего приложения.
     */
    public void init(Input input, Data data, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 1 || select > actions.size()) {
                output.println("Wrong input, you can select : 1.. " + (actions.size()));
                continue;
            }
            UserAction action = actions.get(select - 1);
            run = action.execute(input, data);
        }
    }

    /**
     * Метод выводит меню
     */
    private void showMenu(List<UserAction> actions) {
        output.println("Меню: ");
        for (int i = 0; i < actions.size(); i++) {
            output.println((i + 1) + ". " + actions.get(i).name());
        }
    }

    public static void main(String[] args) {
        Output out = new ConsoleOutput();
        Input input = new ConsoleInput();
        Data data = new RandomArray(new Random());
        List<UserAction> actions = List.of(
                new CreateArray(out),
                new BubbleSortAction(out),
                new InsertSortAction(out),
                new MergeSortAction(out),
                new ExitAction(out)
        );
        new StartProf(out).init(input, data, actions);
    }
}
