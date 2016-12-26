package material.danny_jiang.com.searchhelperdemo.utils;

import android.util.Log;

/**
 * Created by axing on 16/12/13.
 */

public class AlgorithmHealper {

    /**************************二分查找 BEGIN*********************************/
    /**
     * 使用二分查找法查询在数组array中是否存在value
     *
     * @param array
     * @param value
     * @return 如果存在返回value在数组array中的下标， 否则返回有序数组所对应位置取反值
     *
     * Note: 返回值取反是为了判断<0说明目前数组中没有此value，如果想插入此value
     * 则需要插入返回值再取反即可
     */
    public static int binarySearch(int[] array, int value) {
        int lo = 0;
        int hi = array.length - 1;

        while (lo <= hi) {
            int middle = (lo + hi) >>> 1;
            int midValue = array[middle];

            if (midValue > value) {
                hi = middle - 1;
            } else if (midValue < value) {
                lo = middle + 1;
            } else {
                return middle;
            }
        }

        return ~lo;
    }
    /**************************二分查找 END*********************************/


    /**************************KMP查找 BEGIN*********************************/
    /**
     * 使用KMP查找算法，从主字符串sourceString中查找是否存在子串dstString
     *
     * @param sourceString 主字符串
     * @param dstString    需要被查找的子串
     * @return 如果存在返回dstString所在的位置，否则返回-1
     */
    public static final int kmpFindSubString(String sourceString, String dstString) {

        int sourceLength = sourceString.length();

        int dstLength = dstString.length();

        int i = 0, j = 0;

        int[] kmpNext = kmpNext(dstString);

        while (i < sourceLength && j < dstLength) {
            if (j == -1 || sourceString.charAt(i) == dstString.charAt(j)) {
                i++;
                j++;
            } else {
                j = kmpNext[j];
            }
        }

        if (j == dstLength) {
            return i - j;
        } else {
            return -1;
        }
    }

    /**
     * KMP算法中所用到的获取next数组的方法
     *
     * @param dstString 需要映射到int数组的子串
     * @return
     */
    private static final int[] kmpNext(String dstString) {
        int j = 0;

        int k = -1;

        int[] next = new int[dstString.length()];

        next[0] = -1;

        while (j < dstString.length() - 1) {
            if (k == -1 || dstString.charAt(j) == dstString.charAt(k)) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }

        return next;
    }
    /**************************KMP查找 END*********************************/


    /*************************字符串无序匹配 BEGIN*********************************/
     /* 题目
     * 假设两个字符串中所含有的字符和个数都相同我们就叫这两个字符串匹配，
     * 比如：abcda和adabc,由于出现的字符个数都是相同，只是顺序不同，所以这两个字符串是匹配的。
     * 要求高效
     * ***********************************/
    public static boolean stringMatchUnOrdered(String str1, String str2) {
        int[] count = new int[256];

        int size1 = str1.length();
        int size2 = str2.length();

        if (size1 != size2) {
            return false;
        }

        for (int i = 0; i < size1; i++) {
            ++count[str1.charAt(i)];
        }

        for (int i = 0; i < size2; i++) {
            --count[str2.charAt(i)];
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                return false;
            }
        }

        return true;
    }
    /**************************字符串无序匹配 END*********************************/


    /**************************字符串翻转 BEGIN******************************************/
    /**
     * 题目
     * 给定一个字符，要求将字符串前面的若干个字符串移动到字符串的尾部
     * 例如字符串"abcdef"的前3个字符移动到尾部后为"defabc"
     */
    /**
     * 将字符串string中从0下标开始到index下标的子串移动到尾部
     * @param string
     * @param index
     * @return
     */
    public static String leftToRightString(char[] string, int index) {
        int length = string.length;

        reverseString(string, 0, index - 1);

        reverseString(string, index, length - 1);

        reverseString(string, 0, length - 1);

        return new String(string);
    }

    /**
     * 将s字符串完全颠倒  abc -> cba
     * @param string
     * @param from
     * @param to
     */
    private static void reverseString(char[] string, int from, int to){
        while (from < to) {
            char charAt = string[from];

            string[from++] = string[to];

            string[to--] = charAt;
        }
    }
    /**************************字符串翻转 END******************************************/

    /**************************字符串全排列 BEGIN*********************************/
    /**
     * 题目：输入一个字符串,打印出该字符的所有排列
     * 例如：输入"abc", 怎输出 abc acb bac bca cab cba六种
     * 思路：采用递归的方式，依次对后续的子串进行全排列，当递归到只有一个字母时，则打印出当前的字符串
     * 1 将a固定在第一位，求后面bc的排列，得到：abc和acb
     * 2 将b固定在第一位，求后面bc的排列，得到：bac和bca
     * 3 将c固定在第一位，求后面bc的排列，得到：cab和cba
     * @param arr   需要进行全排列的字符数组，需要将String转化为char数组
     * @param start 需要全排列的子串下边，一般从0开始
     * @param end   需要全排列的结束为止，一般为length - 1
     */
    public static void permutation(char[] arr, int start, int end) {
        if (start == end) { // 当只要求对数组中一个字母进行全排列时，只要就按该数组输出即可
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i <= end; i++) {
                System.out.print(arr[i]);
                stringBuilder.append(arr[i]);
            }
            stringBuilder.append("\n");
            Log.e("TAG", stringBuilder.toString());
        } else { // 对多字符进行递归替换调用
            for (int i = start; i <= end; i++) {
                //对首字母进行替换操作
                char temp = arr[start];
                arr[start] = arr[i];
                arr[i] = temp;

                //递归调用start + 1开始的子串
                permutation(arr, start + 1, end);

                //递归调用完毕之后，需要还原， 比如从bac从新还原回abc
                temp = arr[i];
                arr[i] = arr[start];
                arr[start] = temp;
            }
        }
    }
    /**************************字符串全排列 END*********************************/
}
