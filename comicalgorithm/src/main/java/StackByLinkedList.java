/**
 * @author mrdonkey
 * @create on 2020/7/18 17:03
 * @description 通过链表来实现栈
 */
public class StackByLinkedList {
    private Node top;//栈顶
    private Node bottom;//栈底
    private int size;

    /**
     * 入栈
     *
     * @param data
     */
    public void push(int data) {
        Node node = new Node(data);
        if (size == 0) {
            bottom = node;
        } else {
            top.next = node;
        }
        top = node;
        size++;
    }

    /**
     * 出栈
     */
    public Node pop() {
        if (size == 0) {
            return null;
        }
        Node temp = top;
        Node prevNode;
        if (size == 1) {
            prevNode = get(size - 1);
        } else {
            prevNode = get(size - 2);
        }
        prevNode.next = null;
        top = prevNode;
        size--;
        return temp;
    }

    private Node get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node temp = bottom;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }


    /**
     * 打印输出
     */
    public void output() {
        Node temp = bottom;
        for (int i = 0; i < size; i++) {
            System.out.print(temp.data + "-");
            temp = temp.next;
        }
    }

    private class Node {
        public Node(int data) {
            this.data = data;
        }

        private Node next;
        private int data;

        @Override
        public String toString() {
            return "Node{" +
                    "next=" + next +
                    ", data=" + data +
                    '}';
        }
    }

    public static void main(String... arg) {
        StackByLinkedList stackByLinkedList = new StackByLinkedList();
        stackByLinkedList.push(0);
        stackByLinkedList.push(1);
        stackByLinkedList.push(2);
        stackByLinkedList.push(3);
        stackByLinkedList.output();
        System.out.println();
        Node pop = stackByLinkedList.pop();
        System.out.println(pop);
        Node pop1 = stackByLinkedList.pop();
        System.out.println(pop1);
        Node pop2 = stackByLinkedList.pop();
        System.out.println(pop2);
        Node pop3 = stackByLinkedList.pop();
        System.out.println(pop3);
        stackByLinkedList.output();

    }
}
