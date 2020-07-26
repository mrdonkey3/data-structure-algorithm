import java.util.Arrays;

/**
 * @author mrdonkey
 * @create on 2020/7/15 23:35
 * @description 自定义数组
 */
public class MyArray {

    private int[] array;
    private int size;//当前数组元素大小

    public MyArray(int capacity) {
        this.array = new int[capacity];//创建一个长度为capacity的数组
        this.size = 0;
    }


    /**
     * 读取元素
     *
     * @param index 下标
     * @return 返回读取的元素
     */
    public int read(int index) {
        checkIndex(index);
        return array[index];
    }


    /**
     * 更新元素
     *
     * @param index   下标
     * @param element 元素
     * @return 返回旧元素
     */
    public int update(int index, int element) {
        checkIndex(index);
        int oldElement = array[index];
        array[index] = element;
        return oldElement;
    }


    /**
     * 插入元素：插入的位置，之后的元素需从左往右移动；若是插入到尾部，则不需要变化
     * 若是插入到与数组大小一样的位置，则扩容一倍；
     *
     * @param index   下标
     * @param element 插入的元素
     * @return 返回是否插入成功
     */
    public boolean insert(int index, int element) {
        checkIndex(index);
        //扩容
        if (checkResize(index)) {
            resize();
        }
        if (size - index >= 0) System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
        return true;
    }

    /**
     * 删除指定下标的元素 将index之后的元素向前挪一位
     *
     * @param index 下标
     * @return
     */
    public boolean remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        for (int i = index; i < size; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return true;
    }

    /**
     * 数组扩容
     */
    private void resize() {
        int dstCapacity = array.length * 2;
        int[] dstArray = new int[dstCapacity];
        //src->dst 数组copy
        System.arraycopy(array, 0, dstArray, 0, array.length);
        array = dstArray;
    }

    /**
     * 检验是否要扩容
     * 数组元素达到数组容量上限
     *
     * @param index
     * @return
     */
    private boolean checkResize(int index) {
        return size >= array.length;
    }

    /**
     * 检测index是否合法
     *
     * @param index
     */
    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        return "MyArray{" +
                "array=" + Arrays.toString(array) +
                ", size=" + size +
                '}';
    }

    public static void main(String... args) {
        MyArray myArray = new MyArray(5);
        myArray.insert(0, 0);
        myArray.insert(1, 1);
        myArray.insert(2, 2);
        myArray.insert(3, 3);
        myArray.insert(4, 4);
        System.out.println("--->" + myArray.toString());
        myArray.insert(1, 3);
        System.out.println("--->" + myArray.toString());
        myArray.insert(5, 3);
        System.out.println("--->" + myArray.toString());
        myArray.remove(5);
        System.out.println("--->" + myArray.toString());
    }
}
