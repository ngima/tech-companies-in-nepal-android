package np.com.ngimasherpa.tech_companies_in_nepal.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by ngima on 6/8/17.
 */

public class DataUtils {

    public static <T> T getObjectFromJson(String json, Class<T> tClass) {
        return new Gson().fromJson(json, tClass);
    }

    public static <T> T getObjectFromJson(String json, Type type) {
        return new Gson().fromJson(json, type);
    }

    public static String getJsonStringFromEntity(Object object) {
        return new Gson().toJson(object);
    }

}
