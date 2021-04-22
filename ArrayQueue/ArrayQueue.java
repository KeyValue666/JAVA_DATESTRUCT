package ArrayQueue;

import java.util.Scanner;

//创建数组模拟队列
class Queue {
    private int maxSize;//模拟数组的最大长度
    private int front;//队头
    private int rear;//队尾
    private int[] arr;//创建模拟数组

    public Queue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;//指向队列头的前一个位置，为什么是-1
        rear = -1;//指向队列尾的最后一个位置
    }

    //判断队列是否已满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //队列加元素
    public void addQueue(int date) {
        if (isFull()) {
            System.out.println("数据已满！");
            return;
        }
        rear++;
        arr[rear] = date;
    }

    //取队列元素
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取元素！");
        }
        front++;
        return arr[front];//为什么不能是arr[front+1]?和取头元素比较
    }

    //显示队列的所有元素
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空，没有元素！");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    //取队头元素
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据！");
        }
        return arr[front + 1];//可不可以是arr[++front]?为什么？
    }
}

public class ArrayQueue {
    public static void main(String[] args) {
        char key = ' ';//用户输入
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你要测试队列的最大长度：");
        int maxSize = scanner.nextInt();
        Queue queue = new Queue(maxSize);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加元素");
            System.out.println("g(get):取出队列元素");
            System.out.println("h(head):取出队头元素");
            System.out.println("请输入你的选项: ");
            key = scanner.next().charAt(0);//取第一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'e':
                    System.out.println("程序已退出！");
                    loop = false;
                    break;
                case 'a':
                    System.out.println("请输入想要加入的数据：");
                    int date = scanner.nextInt();
                    queue.addQueue(date);
                    break;
                case 'g':
                    try {
                        int num = queue.getQueue();
                        System.out.println(num);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int num1 = queue.headQueue();
                        System.out.println(num1);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                default:
                    System.out.println("你输入的选项不对！请重新输入！");
                    break;

            }
        }
        scanner.close();
    }
}
