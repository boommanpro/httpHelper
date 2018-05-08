package cn.boommanpro.http.helper.query;



import cn.boommanpro.http.helper.annotation.HttpQueryString;
import cn.boommanpro.http.helper.common.StringUtil;

import java.lang.reflect.Field;

public class HttpQueryStringUtil {

    public static String objectToQueryString(Object obj)  {
        StringBuilder stringBuilder = new StringBuilder("?");
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object o = null;
            try {
                o = field.get(obj);
            } catch (IllegalAccessException e) {
                System.out.println("explain:http://www.boommanpro.cn/httpHelper?code=");
                e.printStackTrace();
            }
            if (field.getAnnotation(HttpQueryString.class) != null) {
                if (StringUtil.isNotEmpty(field.getAnnotation(HttpQueryString.class).name()) && field.getAnnotation(HttpQueryString.class).require()) {
                    stringBuilder.append(field.getAnnotation(HttpQueryString.class).name()).append("=").append(getBeanValue(o)).append("&");
                }
            } else if (o != null) {
                stringBuilder.append(field.getName()).append("=").append(getBeanValue(o)).append("&");
            }

        }
        String result = stringBuilder.toString();
        return result.length() > 0 ? result.substring(0, result.length() - 1) : "";
    }


    private static String getBeanValue(Object obj) {
        if (obj != null) {
            return obj.toString();
        }
        return "";
    }




}
