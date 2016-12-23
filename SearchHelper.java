package material.danny_jiang.com.searchhelperdemo.utils;

import android.util.Log;

/**
 * Created by axing on 16/12/13.
 */

public class SearchHelper {

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

}
