/**
 * @author mrdonkey
 * @create on 2020/7/18 14:54
 * @description 单链表
 */
public class SingleLinkedList {

    private int size;//节点个数
    private Node head;//头结点指针
    private Node next;//尾节点指针

    /**
     * 插入节点
     *
     * @param data
     */
    public void insert(int data, int index) {
        if (index < 0 || size < index) {
            throw new IndexOutOfBoundsException("超出链表范围");
        }
        Node insertNode = new Node(data);
        if (size == 0) {//头部插入
            head = insertNode;
            next = insertNode;
        } else if (index == size) {//插入到尾部
            next.next = insertNode;
            next = insertNode;
        } else {//在中间插入
            Node prevNode = get(index - 1);//获取原来下标的前一个node
            Node nextNode = prevNode.next;//原来下标的node
            prevNode.next = insertNode;
            insertNode.next = nextNode;
        }
        size++;
    }

    /**
     * 移除指定下标的node
     *
     * @param index 下标
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("超出链表范围");
        }
        if (index == 0) {//删除头部
            head.next = null;
        } else if (index == size - 1) {//删除尾部
            Node prevNode = get(index - 1);
            prevNode.next = null;
            next = prevNode;
        } else {//中间删除
            Node prevNode = get(index - 1);
            Node removeNode = get(index);
            prevNode.next = prevNode.next.next;
            removeNode.next = null;
        }
        size--;
    }

    /**
     * 获取节点
     *
     * @param index 下标 从0开始
     * @return
     */
    public Node get(int index) {
        if (index < 0 && size <= index) {
            throw new IndexOutOfBoundsException("超出链表范围");
        }
        Node temp = head;//默认为头部，因为当index=0的时候,下面的循环不走
        for (int i = 0; i < index; i++) {//找到对应下标的节点
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 打印输出
     */
    public void output() {
        Node temp = head;
        for (int i = 0; i < size; i++) {
            System.out.print(temp.data + "-");
            temp = temp.next;
        }
    }

    /**
     * 链表的节点
     */
    private class Node {
        public Node(int data) {
            this.data = data;
        }

        private int data;//数据
        private Node next;//尾部
    }

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.insert(1, 0);
        singleLinkedList.insert(2, 1);
        singleLinkedList.insert(5, 2);
        singleLinkedList.insert(8, 3);
        //1-2-5-8
        singleLinkedList.insert(3, 1);
        //1-3-2-5-8
        singleLinkedList.remove(3);
        //1-3-5-8
        singleLinkedList.output();
    }
}
