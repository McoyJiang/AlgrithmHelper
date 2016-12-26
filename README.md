# AlgorithmHelper
常用算法的封装类


### binarySearch -- 二分查找

```
private int[] arr = {1, 5, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34};

private int value = 35;

int pos = AlgorithmHealper.binarySearch(arr, value);

Log.e("TAG", "binary pos is " + pos);
```

### kmpFindSubString -- 用KMP查找算法，从主字符串sourceString中查找是否存在子串dstString

```
String source = "SSSSSA";

String dst = "SA;
        
int position = AlgorithmHealper.kmpFindSubString(source, dst);

Log.e("TAG", "kmp pos is " + position);
```

### stringMatchUnOrdered -- 字符串无序匹配
**比如：abcda和adabc,由于出现的字符个数都是相同，只是顺序不同，所以这两个字符串是匹配的**

```
String source = "abce";

String dst = "ebca;

boolean matched = AlgorithmHealper.stringMatchUnOrdered(source, dst);

Log.e("TAG", "字符串无序匹配" + matched);
```

### leftToRightString -- 字符串旋转
**例如字符串"abcdef"的前3个字符移动到尾部后为"defabc"**

```
String source = "abcdefghi";

String dstString = AlgorithmHealper.leftToRightString(string.toCharArray(), 3);

Log.e("TAG", "字符串旋转之后 " + dstString);  //打印defghiabc
```

### permutation -- 字符串全排列
**例如 输入"abc", 则输出 abc acb bac bca cab cba六种**

```
String string = "abc";

AlgorithmHealper.permutation(string.toCharArray(), 0, string.length() - 1); //打印abc acb bac bca cab cba
```
