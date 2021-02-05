package NB17;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListRekursiv <E> implements Iterable<E> {


    public static void main(String[] args) {

        LinkedListRekursiv<String> list = new LinkedListRekursiv<>();
        //Test a basic list and see if the tail is following
        for (int i = 0; i <= 10; i++) {//O(n^2)
            list.add("Sträng" + i);
            for (String s : list) { //O(n)
                System.out.println(s);
            }
        }
        System.out.println("Tail: " + list.tail.data + "\n" + "Size: " + list.size);

        System.out.println(list.get(2));

        System.out.println(list.getSize());

        System.out.println(list.getNode(2).data);



    }

    public void printDataHeadTailSize() {
        printDataHeadTailSize("Blank test");
    }

    public void printDataHeadTailSize(String test) {
        System.out.println("\n\n");
        System.out.println(test);
        if (head == null) {
            System.out.println("Head: null");
        } else {
            System.out.println("Head: " + head.data);

            for (E string :
                    this) {
                System.out.println(string.toString());
            }
        }
        if(tail == null){
            System.out.println("Tail: null");
        }else{
            System.out.println("Tail: " + tail.data);
        }
        System.out.println("Size: " + size);

    }

    private Node<E> head;
    private int size;
    private Node<E> tail;

    public LinkedListRekursiv() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean add(E item) {
        add(size, item);
        return true;
    }

    public void add(int index, E item) {
        checkIndex(index);
        if (index == 0) {
            addFirst(item);
        } else if (index == size ) {
            addAfter(tail, item);
        } else {
            Node<E> node = getNode(index - 1);
            addAfter(node, item);
        }
    }

    public E get(int index) {
        checkIndex(index);
        Node<E> node = getNode(index);
        return node.data;
    }


    public E remove(int index) {
        checkIndex(index);
        E returnValue;
        if (index == 0) {
            returnValue = head.data;
            head = head.next;
            if (size == 1)
                tail = null;
        } else if (index == (size - 1)) {
            returnValue = tail.data;
            tail = getNode(index - 1);
            tail.next = null; // Kill link
        } else {
            Node<E> before = getNode(index - 1);
            returnValue = before.next.data;
            before.next = before.next.next;
        }
        size--;
        return returnValue;
    }

    public Iterator<E> iterator() {
        return new Itr(head);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> p = head;
        if (p != null) {
            while (p.next != null) {
                sb.append(p.data.toString());
                sb.append("==>");
                p = p.next;
            }
            sb.append(p.data.toString());
        }
        sb.append("]");
        return sb.toString();
    }

    private void addAfter(Node<E> node, E item) {
        if (node.next == null) {
            node.next = new Node<>(item, null);
            tail = node.next;
        } else {
            node.next = new Node<>(item, node.next);
        }
        size++;
    }

    private void addFirst(E item) {
        head = new Node<>(item, head);
        if (size == 0) {
            tail = head;
        }
        size++;
    }

    private Node<E> getNode(int index) {
        return getNodeRek(head, index);
    }

    private Node<E> getNodeRek(Node<E> current ,int index) {
        if(index == 0)
            return current;
        return getNodeRek(current.next, index -1);
    }

    public int getSize() {
        System.out.println("Size in class " + size);
        return Size(head);
    }

    private int Size(Node<E> current) {
        if(current.next == null)
            return 1;
        return 1 + Size(current.next);
    }

    // TODO: beror index på olika variabler?
    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
    }

    private static class Node<E> {
        private E data;
        private Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private class Itr implements Iterator<E> {

        private Node<E> current;

        public Itr(Node<E> start) {
            this.current = start;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            E returnValue = current.data;
            current = current.next;
            return returnValue;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
            //
        }
    }
}

