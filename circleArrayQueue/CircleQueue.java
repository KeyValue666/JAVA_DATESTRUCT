package circleArrayQueue;
//数组模拟循环队列的实现

import java.util.Scanner;

public class CircleQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入队列的最大长度：");
        int maxSize = scanner.nextInt();
        Queue queue = new Queue(maxSize);
        char key;
        Boolean flag = true;
        while (flag) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加元素");
            System.out.println("g(get):取出队列元素");
            System.out.println("h(head):取出队头元素");
            System.out.println("请输入你的选项: ");
            key = scanner.next().charAt(0);//取第一个字符
            switch (key) {
                case 's':
                    queue.displayQueue();
                    break;
                case 'e':
                    System.out.println("程序已退出！");
                    flag = false;
                    break;
                case 'a':
                    System.out.println("请输入想要加入的数据：");
                    int date = scanner.nextInt();
                    queue.addQueue(date);
                    break;
                case 'g':
                    try {
                        System.out.println(queue.getQueue());
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.println(queue.headQueue());
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
class Queue {
    private int front;//队头
    private int rear;//队尾
    private int[] arr;//模拟循环数组
    //队列的最大值
    private int maxSize;

    //建立构造器
    public Queue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        rear = 0;
        front = 0;//开始时两者同时指向数组第一个元素
        /*rear始终指向队头元素的下标；front始终指向队尾下一个位置的下标*/
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;//当队头于队尾下标相等时，队列为空。(联系第十九行代码)
    }

    //判断队列是否已满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;//当队列尾元素的下一个元素和头元素下标相等时，队列满员。
    }

    //入队列
    public void addQueue(int date) {
        //先判断队列是否处于满员状态
        if (isFull()) {
            System.out.println("队列已满!");
        } else {
            //将数据插入队尾
            arr[rear] = date;
            //队尾指向下一位置
            rear = (rear + 1) % maxSize;//防止队列假溢出
        }
    }

    //出队列
    public int getQueue() {
        //先判断队列是否为空状态
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能取出元素！");
        } else {
            int date = arr[front];//定义临时变量接受队首元素
            //队首指针后移
            front = (front + 1) % maxSize;//防止队列假溢出
            return date;
        }
    }

    //展示队列
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("队列元素为空，没有元素!");
        } else {
            for (int i = front; i < front + getlength(); i++) {
                System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
            }
        }
    }

    //求队列长度
    public int getlength() {
        return (rear + maxSize - front) % maxSize;//防止出现负数
    }
    //展示队头
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空！");
        }
        return arr[front];
    }
}