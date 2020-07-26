import java.util.Arrays;
import java.util.Objects;
import java.util.Queue;

/**
 * @author mrdonkey
 * @create on 2020/7/18 17:50
 * @description 通过数组来实现顺序 循环队列 队满的条件：(tail+1)%length=head  队列空：tail=head 下标 对尾总是得指向空的
 */
public class CycleQueue {

    private Object[] array;//数组
    private int head;//队首指针
    private int tail;//队尾指针
    private int length;//长度

    public CycleQueue(int capacity) {
        this.array = new Object[capacity];
        this.length = capacity;
    }

    /**
     * 入队 改变队尾的指针
     *
     * @param elements
     */
    public void enqueue(int elements) {
        //队满条件
        if ((tail + 1) % length == head) {
            throw new IndexOutOfBoundsException("队满啦");
        }
        array[tail] = elements;
        tail = (tail + 1) % length;
    }


    /**
     * 出队 改变队首的指针
     */
    public Object dequeue() {
        if (tail == head) {
            throw new IndexOutOfBoundsException("对空啦");
        }
        Object temp = array[head];
        array[head] = null;//重新指向空
        head = (head + 1) % length;//队首指针发生移动
        return temp;
    }

    private void output() {
        for (int i = head; i < tail; i = (i + 1) % length) {
            System.out.print(array[i] + "-");
        }
    }

    public static void main(String... arg) {
        CycleQueue cycleQueue = new CycleQueue(6);
        System.out.println(Arrays.toString(cycleQueue.array));
        cycleQueue.enqueue(0);
        cycleQueue.enqueue(1);
        cycleQueue.enqueue(2);
        cycleQueue.enqueue(3);
        cycleQueue.enqueue(4);
        System.out.println("----enqueue----");
        cycleQueue.output();
        cycleQueue.dequeue();
        cycleQueue.dequeue();
        System.out.println("\n----dequeue----");
        cycleQueue.output();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CycleQueue)) return false;
        CycleQueue that = (CycleQueue) o;
        return head == that.head &&
                tail == that.tail &&
                length == that.length &&
                Arrays.equals(array, that.array);
    }

}
