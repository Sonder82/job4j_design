package ru.job4j.question;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Класс описывает работу по определению разницы между начальным и измененным состояниями множества.
 * Задача должна решаться за линейное время O(n).
 * @author Aleksey Novoselov
 * @version 1.0
 */
public class Analize {
    /**
     * Метод принимает на вход множество начальное(previous) и множество измененное(current)
     * Поместим с помощью цикла содержимое current в HashMap.
     * В HashMap ключ будет Id,value будет User.
     * Далее с помощью цикла сравним содержимое HashMap по ключу с множеством previous.
     * Делаем вывод что изменилось(add, change, delete).
     * @param previous множество начальное
     * @param current множество измененное
     * @return объект Info, в полях которого записываются количество изменений.
     * Соответственно при(добавлении, изменении, удалении).
     */
    public static Info diff(Set<User> previous, Set<User> current) {
        int add;
        int change = 0;
        int delete = 0;
        Map<Integer, User> map = new HashMap<>();
        for (User userCurrent : current) {
            map.put(userCurrent.getId(), userCurrent);
        }
        for (User userPrevious : previous) {
            if (map.containsKey(userPrevious.getId())) {
                if (!userPrevious.equals(map.get(userPrevious.getId()))) {
                   change++;
                }
            } else {
                delete++;
            }
        }
        add = current.size() - (previous.size() - delete);
        return new Info(add, change, delete);
    }
}
