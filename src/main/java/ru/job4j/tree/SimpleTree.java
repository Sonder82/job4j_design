package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

/**
 * Класс описывает реализацию элементарной структуры дерева.
 * Каждый элемент дерева может быть сам деревом.
 * В дереве не могут храниться дубликаты.
 * @param <E> тип элемента в дереве.
 * @author Aleksey Novoselov
 * @version 1.0
 */
public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Метод должен находить узел по значению parent и добавлять в него дочерний узел со значением child.
     * В этом методе нужно проверить, что значения child еще нет в дереве а parent есть.
     *
     * @param parent узел родитель,к которому добавляется значение
     * @param child  узел ребенок, который добавляется
     * @return Если child уже есть, то метод должен вернуть false.
     */
    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> parentNode = findBy(parent);
        Optional<Node<E>> childNode = findBy(child);
        boolean rsl = (parentNode.isPresent() && childNode.isEmpty());
        if (rsl) {
            parentNode.get().children.add(new Node<>(child));
        }
        return rsl;
    }

    /**
     * Метод должен проверять количество дочерних элементов в дереве.
     * Если их > 2 - то дерево не бинарное
     *
     * @return возвращает false, если количество дочерних элементов в дереве >2.
     */
    public boolean isBinary() {
        return findByPredicate(eNode -> eNode.children.size() > 2).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(eNode -> eNode.value.equals(value));
    }
}
