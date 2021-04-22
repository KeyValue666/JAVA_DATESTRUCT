package downArrayCompress;

import java.util.Scanner;

public class downArrayCompress {
    public static void main(String[] args) {
        //压缩N阶下三角矩阵
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入矩阵的阶数：");
        int num = scanner.nextInt();//输入矩阵的阶数
        int[][] downArray = new int[num][num];
        System.out.println("请输入下三角矩阵的数值：");
        for (int i = 0; i < num; i++) {
            for (int j = 0; j <= i; j++) {
                downArray[i][j] = scanner.nextInt();
            }
        }
        //输出下三角矩阵
        System.out.println();
        System.out.println("原下三角矩阵为：");
        for (int[] arr : downArray) {
            for (int date : arr) {
                System.out.printf("%-4d", date);
            }
            System.out.println();
        }
        System.out.println();
        //创建一维数组用于存储下三角的有效值
        int[] getDownArray = new int[num * (num + 1) / 2];
        //将下三角有效值存进一维数组中
        int count = 0;//用于记录下三角矩阵有效值
        for (int i = 0; i < num; i++) {
            for (int j = 0; j <= i; j++) {
                getDownArray[count] = downArray[i][j];
                count++;
            }
        }
        //将形成的一维数组进行输出演示
        System.out.println("形成的一维数组为：");
        for (int date : getDownArray) {
            System.out.printf("%-4d", date);
        }
        System.out.println();
        //新建二维数组用于解压后展示解压后的下三角矩阵
        int[][] downArray1 = new int[num][num];
        //使用for循环用于还原原下三角矩阵
        int count1 = 0;
        for (int i = 0; i < num; i++) {
            for (int j = 0; j <= i; j++) {
                downArray1[i][j] = getDownArray[count1];
                count1++;
            }
        }
        //遍历downArray1用于展示解压后的downArray
        System.out.println();
        System.out.println("解压后的下三角矩阵为：");
        for (int[] arr : downArray1) {
            for (int date : arr) {
                System.out.printf("%-4d", date);
            }
            System.out.println();
        }
    }
}
