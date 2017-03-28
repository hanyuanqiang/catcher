package com.hyq.util;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 提供一些常用的与字符串相关的工具方法
 */
public class StringUtil {

    /**
     * 删除所有的标点符号
     * @param s 处理的字符串
     */
    public  static String removeAllPunct(String s) {
        if(CheckUtil.isEmpty(s)){
            return "";
        }
        return s.replaceAll("[\\pP\\p{Punct}]", "");
    }

    /**
     * 字符串相似度比较(速度较快)
     */
    public static double similarityRatio(String s1, String s2){
        s1 = removeAllPunct(s1);
        s2 = removeAllPunct(s2);

        if (s1.length() > s2.length()) {
            return 1 - (double) compare(s1, s2) / Math.max(s1.length(), s2.length());
        } else {
            return 1 - (double) compare(s2, s1) / Math.max(s2.length(), s1.length());
        }
    }

    /**
     * 快速比较俩个字符串的相似度
     * @return 俩个字符串的相似度
     */
    public static double similarDegree(String s1, String s2) {
        s1 = StringUtil.removeAllPunct(s1);
        s2 = StringUtil.removeAllPunct(s2);

        s1 = removeSign(s1);
        s2 = removeSign(s2);

        if (s1.length() > s2.length()) {
            int temp = Math.max(s1.length(), s2.length());
            int temp2 = longestCommonSubstring(s1, s2).length();
            return temp2 * 1.0 / temp;
        } else {
            int temp = Math.max(s2.length(), s1.length());
            int temp2 = longestCommonSubstring(s2, s1).length();
            return temp2 * 1.0 / temp;
        }

    }

    private static String removeSign(String str) {
        StringBuffer sb = new StringBuffer();
        for (char item : str.toCharArray()){
            if (charReg(item)) {
                sb.append(item);
            }
        }
        return sb.toString();
    }

    private static boolean charReg(char charValue) {
        return (charValue >= 0x4E00 && charValue <= 0X9FA5) || (charValue >= 'a' && charValue <= 'z') || (charValue >= 'A' && charValue <= 'Z') || (charValue >= '0' && charValue <= '9');
    }

    /**
     * 获取最长公共子串
     * @param strA
     * @param strB
     * @return
     */
    private static String longestCommonSubstring(String strA, String strB) {
        char[] chars_strA = strA.toCharArray();
        char[] chars_strB = strB.toCharArray();
        int m = chars_strA.length;
        int n = chars_strB.length;
        int[][] matrix = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (chars_strA[i - 1] == chars_strB[j - 1])
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                else
                    matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i - 1][j]);
            }
        }
        char[] result = new char[matrix[m][n]];
        int currentIndex = result.length - 1;
        while (matrix[m][n] != 0) {
            if (matrix[n] == matrix[n - 1])
                n--;
            else if (matrix[m][n] == matrix[m - 1][n])
                m--;
            else {
                result[currentIndex] = chars_strA[m - 1];
                currentIndex--;
                n--;
                m--;
            }
        }
        return new String(result);
    }

    //第二种实现方式
    private static int compare(String str, String target) {
        int d[][]; // 矩阵
        int n = str.length();
        int m = target.length();
        int i; // 遍历str的
        int j; // 遍历target的
        char ch1; // str的
        char ch2; // target的
        int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n + 1][m + 1];
        for (i = 0; i <= n; i++) { // 初始化第一列
            d[i][0] = i;
        }

        for (j = 0; j <= m; j++) { // 初始化第一行
            d[0][j] = j;
        }

        for (i = 1; i <= n; i++) { // 遍历str
            ch1 = str.charAt(i - 1);
            // 去匹配target
            for (j = 1; j <= m; j++) {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }

                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }
        return d[n][m];
    }

    private static int min(int one, int two, int three) {
        return (one = one < two ? one : two) < three ? one : three;
    }

    /**
     * 判断一个字符串是否满足指定的模式，并且不能超过最大长度
     * @param src   要判断的字符串
     * @param maxLen    最大长度
     * @param pattern   匹配模式
     * @return
     */
    public static boolean matchLengthAndPattern(String src, int maxLen, String pattern) {
        if (src == null){
            return false;
        }
        if (maxLen > 0 && src.length() > maxLen) {
            return false;
        }
        if (pattern != null && !src.matches(pattern)) {
            return false;
        }
        return true;
    }

    /**
     * 判断字符串是否匹配指定模式
     * @param src
     * @param patterns  多个模式用","或";"隔开，如：view/.*,auth/.*,demo/.*
     * @return
     */
    public static boolean matchPatterns(String src, String patterns) {
        if (patterns == null || patterns.length() == 0)
            return false;

        return matchPatterns(src, patterns.split("[,;]"));
    }

    private static boolean matchPatterns(String src, String[] patterns) {
        if(patterns != null && patterns.length > 0)
        {
            for(String pattern : patterns)
            {
                Matcher matcher = Pattern.compile(pattern).matcher(src);
                if(matcher.matches())
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 替换最后一个匹配的字符串
     * @param src
     * @param oldChar
     * @param newChar
     * @return
     */
    public static String replaceLast(String src, String oldChar, String newChar){
        int index = -1;
        for(int i=index;i<src.length();i++){
            index = src.indexOf(oldChar,index+1);
            if (index != -1){
                i = index;
                i--;
            }else {
                index = i;
                break;
            }
        }
        if (index >=0){
            return src.substring(0,index)+src.substring(index).replace(oldChar,newChar);
        }else{
            return src;
        }
    }

    /**
     * 字符串加密函数MD5实现
     */
    public final static String getMd5(String password){
        MessageDigest md;
        try {
            // 生成一个MD5加密计算摘要
            md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(password.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            String pwd = new BigInteger(1, md.digest()).toString(16);
            return pwd;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }
}
