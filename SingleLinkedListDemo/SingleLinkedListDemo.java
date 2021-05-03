package SingleLinkedListDemo;

import java.util.Scanner;
import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //先建立节点
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(6, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "林冲", "豹子头");
        HeroNode heroNode4 = new HeroNode(7, "武松", "行者");
        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        /*添加节点
        无序添加(依据英雄的编号)
        singleLinkedList.addNode(heroNode1);
        singleLinkedList.addNode(heroNode2);
        singleLinkedList.addNode(heroNode3);*/


        /*有序添加
        singleLinkedList.addOderNode(heroNode1);
        singleLinkedList.addOderNode(heroNode2);
        singleLinkedList.addOderNode(heroNode3);
        singleLinkedList.addOderNode(heroNode4);*/


//        int number = scanner.nextInt();//输入选项进行验证


         /*删除节点
        singleLinkedList.delNode(number);*/


        /*修改节点
        singleLinkedList.update(number);*/


       /* 输出有效节点的个数
        System.out.println("节点个数为"+getLength(singleLinkedList.getHead()));*/


        /*展示链表倒数第index个节点的信息
       System.out.println("倒数第"+number+"个节点为"+getHeroNode(singleLinkedList.getHead(),number));*/


        /*反转链表，改变链表结构
        reserveList(singleLinkedList.getHead());*/


       /* 将链表逆序输出，但不改变链表结构
        reserveList(singleLinkedList.getHead());*/


        /*展示输出链表
       singleLinkedList.showList();*/

    }

    /*
    目的：将链表逆序打印，而不破坏链表的结构
    思路：遍历链表将链表元素压入栈中，之后输出
     */
    public static void reversePrint(HeroNode heroNode){
        if (heroNode.next==null){
            return;//链表为空，直接退出
        }
        Stack<HeroNode>stack=new Stack<HeroNode>();//建立新站，将链表的元素压入栈底
        HeroNode temp=heroNode.next;
        while (temp!=null){
            stack.push(temp);
            temp=temp.next;
        }
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }

    /*
    目的：输出链表中有效节点的个数
    思路：除去头节点后遍历链表
     */
    public static int getLength(HeroNode heroNode) {
        if (heroNode.next == null) {
            return 0;
        }
        int size = 0;
        HeroNode temp = heroNode.next;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        return size;

    }

    /*
     * 目的：将单链表逆序
     * 思路：使用前插法将原链表中的节点前插入新链表中，插入完后，再互换新链表于旧链表头节点的指针
     * */
    public static void reserveList(HeroNode heroNode) {
        //如果单链表只有一个节点或没有节点，直接退出
        if (heroNode.next == null || heroNode.next.next == null) {
            return;
        }
        HeroNode next = null;//用于临时存储变量
        HeroNode temp = heroNode.next;
        HeroNode reserveHead = new HeroNode(0, "", "");//建立一个新头节点
        while (temp != null) {
            next = temp.next;//将temp的下一个节点暂时存储在next中
            temp.next = reserveHead.next;//将temp接到新节点后面
            reserveHead.next = temp;//将原链表中摘掉的节点插入新链表中
            temp = next;
        }
        heroNode.next = reserveHead.next;
    }

    /*
    目的：返回链表中倒数第index个节点
    思路：获得链表的长度后，链表长度减去index既是所要返回的节点，之后进行遍历求的
     */
    public static HeroNode getHeroNode(HeroNode heroNode, int index) {

        HeroNode temp = heroNode.next;
        int size = getLength(heroNode);//链表的长度
        if (index <= 0 || index > size || temp == null) {
            return null;
        }
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;

    }
}

//    定义SingleLinkedList 管理英雄
class SingleLinkedList {
    Scanner scanner = new Scanner(System.in);
    //建立头节点(头节点什么信息也不存储)
    private HeroNode head = new HeroNode(0, "", "");

    //返回头节点
    public HeroNode getHead() {

        return head;
    }

    /*目的：不考虑英雄的编号时进行添加英雄
     * 思路：将链表遍历到最后位置，之后将新节点插入最后位置
     * */
    public void addNode(HeroNode heroNode) {
        //由于链表的头节点不能动，创建一个临时节点代替
        HeroNode temp = head;
        //循环，找到最后一个节点，将新节点插入
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;//一直查找下一个，直到节点为空
        }
        temp.next = heroNode;
    }

    /*目的：考虑英雄编号时进行插入
     * 思路：将链表中的节点编号进行遍历比较，找到第一个编号大于要插入节点编号的节点，将节点插入其后
     * */
    public void addOderNode(HeroNode heroNode) {
        HeroNode temp = head;//临时节点变量不能指向首元节点
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.number > heroNode.number) {
                break;
            } else if (temp.next.number == heroNode.number) {
                flag = true;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("编号号已存在！");
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /*目的：删除节点
     * 思路：遍历链表查找到要删除链表的前一位，之后对要删除的链表进行“覆盖”
     * */
    public void delNode(int number) {
        HeroNode temp = head;//临时节点变量指向头节点
        boolean flag = false;//是否查找到的标志
        while (true) {
            if (temp.next == null) {
                break;//当查找的下一个节点为空时，说明节点不存在
            }
            if (temp.next.number == number) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;//将要删除的节点进行覆盖
        } else {
            System.out.println("要删除的节点不存在！");
        }
    }

    /*目的：修改节点
     * 思路：根据编号遍历找到节点，对该节点进行重新赋值
     * */
    public void update(int number) {
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.number == number) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("请重新输入编号：");
            temp.number = scanner.nextInt();
            System.out.println("请重新输入姓名：");
            String n = scanner.nextLine();//吸收scanner留下的回车键字符
            temp.name = scanner.nextLine();
            System.out.println("请重新输入昵称：");
            temp.nickName = scanner.nextLine();
        }
    }

    //展示链表
    public void showList() {
        if (head.next == null) {
            System.out.println("链表为空！");
            return;//链表为空，直接结束
        }
        HeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);//输出的是temp的字符串表达式
            temp = temp.next;
        }
    }

}

//英雄的节点定义
class HeroNode {
    /*节点成员的属性*/
    public int number;//英雄的编号
    public String name;//英雄的名字
    public String nickName;//英雄的昵称
    public HeroNode next;//指向下一个节点

    //建立构造器，为节点赋值
    public HeroNode(int number, String name, String nickName) {
        this.number = number;
        this.name = name;
        this.nickName = nickName;
    }

    //重写toString方法用于查看英雄的属性
    @Override
    public String toString() {
        return "HeroNode{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}