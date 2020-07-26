import java.util.Arrays;

/**
 * @author mrdonkey
 * @create on 2020/7/26 20:37
 * @description 最大优先队列, 利用最大堆实现
 */
public class PriorityQueue {
    private int[] arrays;
    private int size;//元素大小

    public PriorityQueue() {
        arrays = new int[8];//初始长度为32
    }

    /**
     * 入队，即二叉堆的插入操作
     *
     * @param key
     */
    private void enqueue(int key) {
        if (size > arrays.length) {//扩容
            resize();
        }
        //加到队尾
        arrays[size++] = key;
        //上浮调整
        upAdjust();

    }


    /**
     * 出队
     */
    private int dequeue() {
        ;
        //校验
        if (size <= 0) {
            throw new IndexOutOfBoundsException();
        }
        int head = arrays[0];//获得头部元素
        arrays[0] = arrays[--size];//让队尾的元素来的对头
        arrays[size] = 0;
        //进行下沉调整
        downAdjust();
        return head;
    }

    /**
     * 上浮操作 ,从最后一个孩子节点开始
     */
    private void upAdjust() {
        int childIndex = size - 1;
        int parentIndex = (childIndex - 1) / 2;
        int temp = arrays[childIndex];//保存临时数据

        while (childIndex > 0 && temp > arrays[parentIndex]) {
            arrays[childIndex] = arrays[parentIndex];
            childIndex = parentIndex;
            parentIndex = (childIndex - 1) / 2;
        }
        arrays[childIndex] = temp;

    }


    /**
     * 下沉调整 ，堆顶下沉
     */
    private void downAdjust() {
        int childIndex = 1;
        int parentIndex = 0;
        int temp = arrays[parentIndex];//保存临时数据

        while (childIndex < size) {//因为childIndex越来越大，限制添加时到达size
            //如果右孩子大于左孩子，下标使用右孩子的下标
            if (childIndex + 1 < size & arrays[childIndex] < arrays[childIndex + 1]) {
                childIndex++;
            }
            //如果最大的孩子还是小于父节点，则退出这轮循环
            if (arrays[childIndex] < temp) {
                break;
            }
            arrays[parentIndex] = arrays[childIndex];
            parentIndex = childIndex;
            childIndex = childIndex * 2 + 1;
        }
        arrays[parentIndex] = temp;
    }


    /**
     * 扩容
     */
    private void resize() {
        arrays = Arrays.copyOf(arrays, size * 2);
    }

    @Override
    public String toString() {
        return "PriorityQueue{" +
                "arrays=" + Arrays.toString(arrays) +
                ", size=" + size +
                '}';
    }

    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.enqueue(3);
        priorityQueue.enqueue(5);
        priorityQueue.enqueue(10);
        priorityQueue.enqueue(2);
        priorityQueue.enqueue(7);
        System.out.println("当前队列:" + priorityQueue);
        System.out.println("出队元素:" + priorityQueue.dequeue());
        System.out.println("当前队列:" + priorityQueue);
        System.out.println("出队元素:" + priorityQueue.dequeue());
        System.out.println("当前队列:" + priorityQueue);

    }
}
