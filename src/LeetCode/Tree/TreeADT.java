package LeetCode.Tree;

import java.util.Iterator;

enum TreeTraverseType {
    PRE_ORDER,
    IN_ORDER,
    POST_ORDER,
    LEVEL_ORDER
}

public interface TreeADT<T extends Comparable<T>> {       // abstract data type
    boolean isEmpty();

    int size();

    int height();

    boolean contains(T elem);

    boolean add(T elem);

    boolean remove(T elem);

    Iterator<T> traverse(TreeTraverseType type);

    Iterable<T> traversalIterable(TreeTraverseType type);
}
