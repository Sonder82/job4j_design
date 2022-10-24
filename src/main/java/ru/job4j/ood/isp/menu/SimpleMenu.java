package ru.job4j.ood.isp.menu;

import java.util.*;

/**
 * ItemInfo. Он служит для того, чтобы скомпоновать пункт меню и номер пункта (1.1., 1.1.1. и т.д.).
 * SimpleMenuItem. Это реализация MenuItem для SimpleMenu.
 * DFSIterator. Это итератор, основанный на поиске в глубину.
 * Но немного модифицированный, поскольку нам удобно читать пункты меню сверху-вниз и слева-направо.
 */
public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean result = true;

        return result;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {

        return findItem(itemName)
                .map(itemInfo -> new MenuItemInfo(itemInfo.getMenuItem(), itemInfo.getNumber()));
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        Iterator<ItemInfo> iterator = new DFSIterator();
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return new MenuItemInfo(iterator.next().getMenuItem(), iterator.next().getNumber());
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        Optional<ItemInfo> result = Optional.empty();
        Iterator<ItemInfo> iterator = new DFSIterator();
        while (iterator().hasNext()) {
            ItemInfo itemInfo = iterator.next();
            if (itemInfo.getMenuItem().getName().equals(name)) {
                result = Optional.of(itemInfo);
                break;
            }
        }
        return result;
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }

        public MenuItem getMenuItem() {
            return menuItem;
        }

        public void setMenuItem(MenuItem menuItem) {
            this.menuItem = menuItem;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }

}
