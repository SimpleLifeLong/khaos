package org.greece.mythology.tartarus.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TimeZone;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;


/**
 * @author suhaohao
 */
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper()
            // 开启支持字段名称不带引号的转换模式 -> "{a:1,b:\"ccc\"}"
            .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
            // 对象为空不抛异常
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            // 只转换定义的字段，未定义的字段忽略。
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            // 时间格式化
            .setTimeZone(TimeZone.getTimeZone("CTT"))//Asia/Shanghai
            .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    private JsonUtils(Include inclusion) {
        //设置输出包含的属性
        objectMapper.setSerializationInclusion(inclusion);
    }

    /**
     * 创建输出全部属性到Json字符串的Binder.
     */
    public static JsonUtils buildNormalBinder() {
        return new JsonUtils(ALWAYS);
    }

    /**
     * 创建只输出非空属性到Json字符串的Binder.
     */
    public static JsonUtils buildNonNullBinder() {
        return new JsonUtils(NON_NULL);
    }

    /**
     * 创建只输出非空属性到Json字符串的Binder.
     */
    public static JsonUtils buildNonEmptyBinder() {
        return new JsonUtils(NON_EMPTY);
    }

    /**
     * 创建只输出初始值被改变的属性到Json字符串的Binder.
     */
    public static JsonUtils buildNonDefaultBinder() {
        return new JsonUtils(NON_DEFAULT);
    }


    public static <T> String toJson(T t, Include... includes) {
        buildNormalBinder();
        if (includes != null && includes.length > 0) {
            final Include include = includes[0];
            if (NON_NULL.equals(include)) {
                buildNonNullBinder();
            } else if (NON_EMPTY.equals(include)) {
                buildNonEmptyBinder();
            } else if (NON_DEFAULT.equals(include)) {
                buildNonDefaultBinder();
            }
        }
        try {
            return objectMapper.writeValueAsString(t);
        } catch (IOException e) {
            throw new IllegalArgumentException("convert to json string error:" + e);
//            return null;
        }
    }

    /**
     * convert jsonString to (T) Class Instance
     *
     * @param jsonString
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String jsonString) {
        return fromJson(jsonString, new TypeReference<T>() {
        });
    }

    /**
     * convert jsonString to Class Instance with given Class T
     *
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }

        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("parse json string error:" + jsonString, e);
//            return null;
        }
    }

    /**
     * convert jsonString to Class Instance with given TypeReference<T>
     *
     * @param jsonString
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String jsonString, TypeReference<T> type) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return objectMapper.readValue(jsonString, type);
        } catch (IOException e) {
            throw new IllegalArgumentException("parse json string error:" + jsonString, e);
//            return null;
        }
    }

    public static <T> Map toMap(T t, Include... includes) {
        return fromJson((t instanceof String ? (String) t : toJson(t, includes)), Map.class);
    }

    public static <T> T fromMap(Map map, Class<T> clazz) {
        return fromJson(toJson(map), clazz);
    }


//    public static void main(String[] args) {
//        Map map = new HashMap(){{put("id", 1);put("name", "test");}};
//        SysHospital sysHospital = fromMap(map, SysHospital.class);
//        System.out.println(sysHospital);
//        System.out.println(toMap(sysHospital));
//        System.out.println(toMap(sysHospital, NON_NULL));
//    }
//        String json;
//
//        AccountBean bean = new AccountBean();
//        bean.setAddress("china-Guangzhou");
//        bean.setEmail("hoojo_@126.com");
//        bean.setId(1);
//        bean.setName("hoojo");
//
//        /**
//         * T to JSON
//         */
//        System.out.println("<<< T to JSON >>>");
//        System.out.println(toJson(bean, null));
//        System.out.println(toJson(bean, NON_NULL));
//        System.out.println(toJson(bean, NON_EMPTY));
//        System.out.println(toJson(bean, NON_DEFAULT));
//
//        AccountBean bean2 = fromJson(toJson(bean), AccountBean.class);
//        System.out.println(bean2);
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", bean.getName());
//        map.put("account", bean);
//        bean = new AccountBean();
//        bean.setAddress("china-Beijin");
//        bean.setEmail("hoojo@qq.com");
//        map.put("account2", bean);
//
//        /**
//         * Map<T> to JSON
//         */
//        System.out.println("\n<<< Map<T> to JSON >>>");
//        System.out.println(toJson(map));
//        System.out.println(fromJson(toJson(map), Map.class));
//
//        /**
//         * List<T> to JSON
//         */
//        System.out.println("\n<<< List<T> to JSON >>>");
//        List<String> stringList = Arrays.asList("1", "b2", "C3");
//        json = toJson(stringList);
//        System.out.println(json);
//        System.out.println(fromJson(json, List.class));
//
//        /**
//         * Map<T> to JSON
//         */
//        System.out.println("\n<<< Map<T> to JSON >>>");
//        Map<Integer, AccountBean> beanMap = new HashMap(){{put(2, new AccountBean(){{setName("a"); setAddress("addr1"); setId(3);}});put(23, new AccountBean(){{setName("asc"); setAddress("sss"); setId(334);}});}};
//        json = toJson(beanMap);
//        System.out.println(json);
//        System.out.println(fromJson(json, new TypeReference<Map<Integer, AccountBean>>(){}));
//
//        /**
//         * List<T> to JSON
//         */
//        System.out.println("\n<<< List<T> to JSON >>>");
//        List<AccountBean> beanList = Arrays.asList(new AccountBean(){{setName("a"); setAddress("addr1"); setId(1);setBirthday(new Birthday(new Date()));}}, new AccountBean(){{setName("b"); setAddress("addr2"); setId(2);}}, new AccountBean(){{setName("dds"); setAddress("addr3"); setId(3);}});
//        json = toJson(beanList);
//        System.out.println(json);
//        System.out.println(fromJson(json, new TypeReference<List<AccountBean>>(){}));
//
//        /**
//         * List<Map<T, Map<T, LIST<T>>>> to JSON
//         */
//        System.out.println("\n<<< List<Map<T, Map<T, LIST<T>>>> to JSON >>>");
//        List<Map<Integer, Map<String, List<AccountBean>>>> mixEntity = new ArrayList<>();
//
//        mixEntity.add(
//        new HashMap() {
//            {
//                put(1, new HashMap() {{
//                    put("str1-1", new ArrayList() {{
//                        add(new AccountBean() {{
//                            setName("a1");
//                            setAddress("addr1");
//                            setId(1);
//                        }});
//                        add(new AccountBean() {{
//                            setName("b2");
//                            setAddress("addr2");
//                            setId(2);
//                        }});
//                    }});
//                    put("str1-2", new ArrayList() {{
//                        add(new AccountBean() {{
//                            setName("c3");
//                            setAddress("addr3");
//                            setId(3);
//                            setBirthday(new Birthday(new Date()));
//                        }});
//                        add(new AccountBean() {{
//                            setName("d4");
//                            setAddress("addr4");
//                            setId(4);
//                        }});
//                    }});
//                }});
//                put(2, new HashMap() {{
//                    put("str2-1", new ArrayList() {{
//                        add(new AccountBean() {{
//                            setName("2a1");
//                            setAddress("2addr1");
//                            setId(11);
//                        }});
//                        add(new AccountBean() {{
//                            setName("2b2");
//                            setAddress("2addr2");
//                            setId(12);
//                        }});
//                    }});
//                    put("str2-2", new ArrayList() {{
//                        add(new AccountBean() {{
//                            setName("2c3");
//                            setAddress("2addr3");
//                            setId(13);
//                        }});
//                        add(new AccountBean() {{
//                            setName("2d4");
//                            setAddress("2addr4");
//                            setId(14);
//                        }});
//                    }});
//                }});
//            }
//        }
//        );
//        json = toJson(mixEntity, NON_NULL);
//        System.out.println(json);
//        System.out.println(fromJson(json, new TypeReference<List<Map<Integer, Map<String, List<AccountBean>>>>>(){}).get(0).get(1).get("str1-2").get(0).getBirthday());
//    }


}
