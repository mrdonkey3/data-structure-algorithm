import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author mrdonkey
 * @create on 2020/7/25 22:27
 * @description 用链表实现二叉树（利用递归），实现二叉树的前序/中序/遍历
 */
public class BinaryTree {

    /**
     * 创建二叉树
     *
     * @param linkedList
     * @return
     */
    private static TreeNode createBinaryTree(LinkedList<Integer> linkedList) {
        //节点
        TreeNode node = null;
        //校验
        if (linkedList == null || linkedList.isEmpty()) {
            return null;
        }
        //数据
        Integer data = linkedList.removeFirst();
        //填充二叉树
        if (data != null) {
            node = new TreeNode(data);//创建新的节点，并设置数据
            node.leftChild = createBinaryTree(linkedList);//利用递归，将数据填充到左孩子里，遇到null跳出方法栈
            node.rightChild = createBinaryTree(linkedList);//同理
        }
        return node;
    }

    /**
     * 前序遍历:根节点、左孩子、右孩子 （递归实现，利用回溯原理）
     *
     * @param root 节点
     * @return
     */
    private static void preOrderTraversalWithRecursive(TreeNode root) {
        if (root == null) return;
        System.out.print(root.data);
        preOrderTraversalWithRecursive(root.leftChild);
        preOrderTraversalWithRecursive(root.rightChild);
    }

    /**
     * 中序遍历：左孩子、根节点、右孩子
     *
     * @param root
     */
    private static void inOrderTraversalWithRecursive(TreeNode root) {
        if (root == null) return;
        inOrderTraversalWithRecursive(root.leftChild);
        System.out.print(root.data);
        inOrderTraversalWithRecursive(root.rightChild);
    }

    /**
     * 后序遍历：左孩子、右孩子、根节点
     *
     * @param root
     */
    private static void postOrderTraversalWithRecursive(TreeNode root) {
        if (root == null) return;
        postOrderTraversalWithRecursive(root.leftChild);
        postOrderTraversalWithRecursive(root.rightChild);
        System.out.print(root.data);
    }


    /**
     * 利用栈来实现 前序遍历 ：栈也可以实现回溯的效果
     *
     * @param root
     */
    private static void preOrderTraversalWithStack(TreeNode root) {
        //存储节点的栈
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            //迭代访问孩子的左孩子，并入栈
            while (root != null) {
                System.out.print(root.data);
                stack.push(root);
                root = root.leftChild;
            }
            //如果节点没有左孩子了，则将栈顶的节点弹出，迭代访其子的右孩子
            if (!stack.isEmpty()) {
                TreeNode top = stack.pop();
                root = top.rightChild;
            }
        }
    }

    /**
     * 中序遍历，非递归的做法
     *
     * @param root
     */
    private static void inOrderTraversalWithStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {

            //迭代访问孩子的左节点并入栈
            while (root != null) {
                stack.push(root);
                root = root.leftChild;
            }

            //如果节点没有左孩子了，则弹出该节点，打印，并且迭代它的右孩子
            if (!stack.isEmpty()) {
                TreeNode top = stack.pop();
                System.out.print(top.data);//弹出栈顶节点
                root = top.rightChild;
            }
        }
    }

    private static void postOrderTraversalWithStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode tempNode;
        while (root != null || !stack.isEmpty()) {

            //迭代访问孩子的左节点并入栈
            while (root != null) {
                stack.push(root);
                root = root.leftChild;
            }

            //如果节点没有左孩子了，则弹出该节点，打印，并且迭代它的右孩子
            if (!stack.isEmpty()) {
                root = stack.pop();
                tempNode = root.rightChild;//看是否有右孩子
                if (tempNode != null) {
                    root.rightChild = null;//将root的right放入栈，若不置空则每次pop出右孩子的父节点时，又重新造成循环
                    stack.push(root);//将右节点进栈
                } else {
                    System.out.print(root.data);//若不存在右孩子，则直接输出根节点
                }
                root = tempNode;
            }
        }
    }

    /**
     * 通过队列来层序遍历，按照层级来进行遍历
     *
     * @param root
     */
    private static void levelTraversalWithQueue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode head = queue.poll();//弹出队头
            System.out.print(head.data);
            //若是左孩子不为空，则入队
            if (head.leftChild != null) {
                queue.offer(head.leftChild);
            }
            //若是右孩子也不为空则也入队
            if (head.rightChild != null) {
                queue.offer(head.rightChild);
            }
        }
    }

    /**
     * 通过递归来实现层序遍历，通过层级控制
     * 递归中断条件：level=0，level等于0 是相对于它是否进到了二叉树指定的层级
     * 第一层 只递归1次，leve-1 =0
     * 第二层 递归2次， 2-1=1 1-1=0，这样控制就可以指定它走到第几层，打印对应的节点
     * 第三层 递归3次
     * @param root
     */
    private static void levelTraversalWithRecursive(TreeNode root) {
        int level = getLevel(6);//得到二叉树的层级
        for (int i = 1; i <= level; i++) {
            levelTraversalWithRecursive_(root, i - 1);
        }
    }

    private static void levelTraversalWithRecursive_(TreeNode root, int level) {
        if (level == 0) {
            System.out.print(root.data);
            return;
        }
        if (root.leftChild != null) {
            levelTraversalWithRecursive_(root.leftChild, level - 1);
        }
        if (root.rightChild != null) {
            levelTraversalWithRecursive_(root.rightChild, level - 1);
        }
    }


    /**
     * 根据节点数算出二叉树的层级
     *
     * @param nodeSize
     * @return
     */
    private static int getLevel(int nodeSize) {
        return (int) Math.floor(Math.log(nodeSize) / Math.log(2)) + 1;
    }


    /**
     * 二叉树的节点
     */
    private static class TreeNode {
        private final int data;
        private TreeNode leftChild;
        private TreeNode rightChild;

        public TreeNode(int data) {
            this.data = data;
        }
    }


    public static void main(String[] args) {

        /**
         * 1, 2, 3, null, null, 4, null, null, 5, 6, null, null, null
         *          1
         *        /    \
         *      /      \
         *     2        5
         *    / \      / \
         *   3   4    6   n
         *  / \  /\  / \
         * n   n n n n  n
         */
        LinkedList<Integer> linkedList = new LinkedList<>(Arrays.asList(1, 2, 3, null, null, 4, null, null, 5, 6, null, null, null));
        TreeNode binaryTree = createBinaryTree(linkedList);


        System.out.println("\n\n----广度优先遍历：层序遍历ByQueue----");
        levelTraversalWithQueue(binaryTree);
        System.out.println("\n----层序遍历ByRecursive----");
        levelTraversalWithRecursive(binaryTree);

        System.out.println("\n\n----深度优先遍历----");

        System.out.println("----前序遍历----");
        preOrderTraversalWithRecursive(binaryTree);
        System.out.println("\n----中序遍历----");
        inOrderTraversalWithRecursive(binaryTree);
        System.out.println("\n----后序遍历----");
        postOrderTraversalWithRecursive(binaryTree);
        System.out.println("\n\n============");
        //借助栈
        System.out.println("\n----前序遍历ByStack----");
        preOrderTraversalWithStack(binaryTree);
        System.out.println("\n----中序遍历ByStack----");
        inOrderTraversalWithStack(binaryTree);
        System.out.println("\n----后序遍历ByStack----");
        postOrderTraversalWithStack(binaryTree);

    }
}
