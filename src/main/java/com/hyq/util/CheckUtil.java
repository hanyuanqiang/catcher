package com.hyq.util;

import com.google.common.collect.Lists;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *  用于一些检查和判断
 */
public class CheckUtil {

    private static final Logger _log = Logger.getLogger(CheckUtil.class);

    /**
     * 检查指定对象一定不能为空
     * @param o
     * @throws Exception
     */
    public static void mustNotBeNull(Object o) throws Exception {
        if (isNull(o)) {
            throw new Exception(o + " must not be null");
        }
    }

    /**
     * 判断指定对象是否为空
     * @param o
     * @return
     */
    public static boolean isNull(Object o) {
        if (o == null){
            return true;
        } else if (o instanceof String){
            return isEmpty((String)o);
        }else if(Collection.class.isAssignableFrom(o.getClass())){  //如果集合中，元素个数为0也返回true
            return ((Collection)o).size() == 0 ? true : false;
        } else{
            return false;
        }
    }

    /**
     * 判断指定对象是否不为空
     * @param o
     * @return
     */
    public static boolean isNotNull(Object o) {
        return !isNull(o);
    }

    /**
     * 判断字符串是否为空
     * @param value
     * @return
     */
    public static boolean isEmpty(String value) {
        if (value == null || _trim(value).length() == 0)
            return true;
        else
            return false;
    }

    /**
     * 裁剪字符串的头尾空格
     * @param value
     * @return
     */
    private static String _trim(String value) {
        if (value != null) {
            value = value.trim();
        }
        return value;
    }

    public static boolean isEqual(Object v1, Object v2) throws ParseException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (v1 == v2){
            return true;
        }else if (v1.equals(v2)){
            return true;
        }else{
            String clazzName = v1.getClass().getName();

            if ("java.util.Date".equals(clazzName)){
                String d1 = DateUtil.transDate2Str((Date) v1);
                String d2 = DateUtil.transDate2Str((Date) v2);
                if (d1.equals(d2)){
                    return true;
                }else{
                    return false;
                }
            }

            if (clazzName.startsWith("com.hyq.entity")){
                Integer id1 = (Integer) v1.getClass().getMethod("getId").invoke(v1);
                Integer id2 = (Integer) v2.getClass().getMethod("getId").invoke(v2);
                return id1 == id2;
            }

            if ("java.util.List".equals(clazzName)){
                if (((List)v1).size() != ((List)v2).size()){
                    return false;
                }else if(((List)v1).size()==0){
                    return true;
                }else{
                    String className = ((List) v1).get(0).getClass().getName();
                    if (className.contains("com.hyq.entity")){
                        List<Integer> idList1 = Lists.newArrayList();
                        List<Integer> idList2 = Lists.newArrayList();
                        for (int i=0;i<((List)v1).size();i++){
                            Object o1 = ((List) v1).get(i);
                            Object o2 = ((List) v2).get(i);
                            Integer id1 = (Integer) o1.getClass().getMethod("getId").invoke(o1);
                            Integer id2 = (Integer) o1.getClass().getMethod("getId").invoke(o2);
                            idList1.add(id1);
                            idList2.add(id2);
                        }
                        Collections.sort(idList1);
                        Collections.sort(idList2);
                        return idList1.toString().equals(idList2.toString());
                    }else{
                        return false;
                    }
                }
            }



        }
        return false;
    }
}
