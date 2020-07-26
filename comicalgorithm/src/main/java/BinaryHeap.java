import java.util.Arrays;

/**
 * @author mrdonkey
 * @create on 2020/7/26 16:28
 * @description 二叉堆，利用数组来实现一个无序二叉树，通过二叉树来调整成二叉堆
 */
public class BinaryHeap {

    /**
     * [上浮调整]，应用在二叉堆插入元素后通过上浮进行自我调整；
     * #1 插入节点：插入的的元素位于二叉堆的最后一个元素，在数据层面看即数组的最后一个元，
     * 最小堆：插入的元素要和其父节点对比看是否小于其父节点，小于则上浮（交互元素值）反之不变；
     * 上浮后的元素成为新的孩子节点，继续进行上浮判断；
     * 最大堆：与最小堆相反。 这里实现的是
     *
     * @param array 待调整的堆（本质上是一个数组，因为是基于数组实现的二叉树）
     */
    private static void upAdjust(int[] array) {

        int childIndex = array.length - 1;//最后一个孩子节点下标
        int parentIndex = (childIndex - 1) / 2;//最后一个父节点下标
        int tempData = array[childIndex];//保存插入节点点值，用于最后的赋值

        //维持循环的条件
        while (childIndex > 0 && tempData < array[parentIndex]) {
            //如果子节点元素小于父节点的元素
            //无须进行真正交互，单向赋值即可
            //父节点和孩子连续交互时，并不一定要针对交换，只需要先把交换一方的值存入temp变化，然后做单向覆盖，
            //循环结束后，再把temp值存入交换后的位置即可
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;//找到它（此时已经是上浮后的元素了）的父节点，继续进行上浮操作
        }
        array[childIndex] = tempData;//最终的childIndex可能为parentIndex,
    }


    /**
     * [下沉调整]，应用在删除元素、构建二叉堆；
     * #1 删除节点：删除的是堆顶元素，然后将最后一个元素放到栈顶，然后在对此时在栈顶的元素进行下沉操作
     * #2 构建二叉堆：依次从二叉树最后一个非叶子节点（lastParentIndex= floor((lastChildIndex-1)/2）进行下沉操作
     * 而 lastChildIndex = array.length()-1，所以最后一个非叶子节点的下标为
     * lastParentIndex= floor((array.length()-2)/2
     * 有多少个非叶子节点就下沉几次（看最后一个非叶子节点的下标i，其他在它之上的非叶子节点就是(i-1..0)）。
     *
     * @param array       需要调整的堆
     * @param parentIndex 父节点的下标
     */
    private static void downAdjust(int[] array, int parentIndex) {
        int temp = array[parentIndex];
        int childIndex = parentIndex * 2 + 1;//得到左孩子的下标(肯定有左孩子节,右孩子节点不一定有)
        int length = array.length;
        while (childIndex < length) {
            //如果存在右孩子，并且右孩子比左孩子小，之后就用右孩子（因为它是最小的孩子节点）与父节点进行比较
            if (childIndex + 1 < length && array[childIndex] > array[childIndex + 1]) {
                childIndex++;//让左节点变成父节点
            }
            //如果最小的节点比父节点还大，则退出这次循环
            if (temp < array[childIndex])
                break;

            //无须真正交互，单向赋值即可
            //优化点：父节点和孩子连续交互时，并不一定要针对交换，只需要先把交换一方的值存入temp变化，然后做单向覆盖，
            //循环结束后，再把temp值存入交换后的位置即可
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = childIndex * 2 + 1;//child作为它的孩子节点的父节点，继续
        }
        array[parentIndex] = temp;
    }

    /**
     * 构建二叉堆(最小堆)： 传入一个无序的二叉树构建二叉堆。从最后一个非叶子节点进行下沉调整
     *
     * @param array 无序的二叉树数组
     */
    private static void buildBinaryHeap(int[] array) {
        int lastParentIndex = (array.length - 2) / 2;//最后一个非叶子节点的下标
        for (int i = lastParentIndex; i >=0; i--) {
            downAdjust(array, i);
        }
    }

    /**          1
     *         /   \
     *       3      2
     *     /  \    / \
     *    6    5  7   8
     *  / \   /
     * 9  10 0
     *           0
     *         /   \
     *       1      2
     *     /  \    / \
     *    6    3  7   8
     *  / \   /
     * 9  10 5
     *          1
     *        /   \
     *      5      2
     *    /  \    / \
     *   6    7  3   8
     *  / \
     * 9  10
     */
    public static void main(String[] args) {
        //在1, 3, 2, 6, 5, 7, 8, 9, 10 二叉堆 后插入一个0
        int[] array = new int[]{1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
        upAdjust(array);//上浮后 [0, 1, 2, 6, 3, 7, 8, 9, 10, 5]
        System.out.println(Arrays.toString(array));
        //在无序的数组(基于数组构建的二叉树)构建二叉堆
        array = new int[]{7, 1, 3, 10, 5, 2, 8, 9, 6};
        buildBinaryHeap(array);
        System.out.println(Arrays.toString(array));
    }

}
