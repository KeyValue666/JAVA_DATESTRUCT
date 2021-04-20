package xishuarray;

/*
 *稀疏数组的使用
 */
public class XishuArray {
    public static void main(String[] args) {
        //建立二维数组，1表示黑子，2表示红子
        
        int[][] chessArray = new int[10][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        chessArray[2][6]=1;
        //遍历最初的数组
        for (int[] row : chessArray) {
            for (int date : row) {
                System.out.printf("%d\t", date);
            }
            System.out.println();
        }
        System.out.println();
        //统计二维数组中有效的数据个数使用sum进行计数
        int sum = 0;//sum同时用于基数稀疏数组的行数
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[0].length; j++) {
                if (chessArray[i][j] != 0) {
                    sum++;
                }
            }
        }
        //建立稀疏数组
        int[][] sparsArray = new int[sum + 1][3];//多一行用于存储原数组的行、列以及有效值的个数
        sparsArray[0][0] = chessArray.length;//行数
        sparsArray[0][1] = chessArray[0].length;//列数
        sparsArray[0][2] = sum;
        //重要一步，将原数组的值放入稀疏数组中
        int count = 0;//用于计数原数组中有效数据在稀疏数组行的位置
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[0].length; j++) {
                if (chessArray[i][j] != 0) {
                    count++;
                    sparsArray[count][0] = i;//行数
                    sparsArray[count][1] = j;//列数
                    sparsArray[count][2] = chessArray[i][j];//将原数组中有效数据放在稀疏数组中
                }
            }
        }
        //输出稀疏数组
        for (int i = 0; i < sparsArray.length; i++) {
            System.out.printf("%d\t%d\t%d\n", sparsArray[i][0], sparsArray[i][1], sparsArray[i][2]);
            System.out.println();
        }
        //建立解压后的二维数组
        int[][] chessArray2 = new int[sparsArray[0][0]][sparsArray[0][1]];
        //数组从下标1开始
        for (int i = 1; i < sparsArray.length; i++) {
            chessArray2[sparsArray[i][0]][sparsArray[i][1]] = sparsArray[i][2];//微信数组赋值行和列
        }
        //输出解压后的数组
        for (int[] row : chessArray2) {
            for (int date : row) {
                System.out.printf("%d\t", date);
            }
            System.out.println();
        }
    }
}
