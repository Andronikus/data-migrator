package pt.andronikus.client.utils;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.json.JSONObject;
import pt.andronikus.client.enums.AppConstants;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TimeZone;


public class JSONUtils {
    private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JodaModule())
                                                                 .setSerializerProvider(new CustomNullSerializer())
                                                                 .setTimeZone(TimeZone.getDefault())
                                                                 .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                                                                 .setDateFormat(new SimpleDateFormat(AppConstants.DATETIME_WITH_MS_AND_TZ_FORMAT));


    public static String toJSON(Object object) {
        String json = "{}";
        try {
            json = mapper.writeValueAsString(object);
        } catch (Exception exception) {
            exception.printStackTrace();
            /*
            if (logger.isWarnEnabled()) {
                logger.warn("An Exception occurred while converting entity to json");
                logger.warn(ExceptionUtils.getFullStackTrace(exception));
            }
            */

        }
        return json;
    }

    public static String toJSON(List<?> objectList) {
        String json = "[]";
        try {
            json = mapper.writeValueAsString(objectList);
        } catch (Exception exception) {
            exception.printStackTrace();
            /*
            if (logger.isWarnEnabled()) {
                logger.warn("An Exception occurred while converting json to entity list");
                logger.warn(ExceptionUtils.getFullStackTrace(exception));
            }
             */
        }

        return json;
    }

    public static String toJSON(Set<?> objectSet) {
        String json = "[]";
        try {
            json = mapper.writeValueAsString(objectSet);
        } catch (Exception exception) {
            exception.printStackTrace();
            /*
            if (logger.isWarnEnabled()) {
                logger.warn("An Exception occurred while converting json to entity Set");
                logger.warn(ExceptionUtils.getFullStackTrace(exception));
            }

             */
        }

        return json;
    }

    public static JSONObject toJSONObject(Object object) {
        JSONObject jsonObject = null;
        try {
            String jsonStrong = mapper.writeValueAsString(object);
            jsonObject = mapper.readValue(jsonStrong, JSONObject.class);
        } catch (Exception exception) {
            exception.printStackTrace();
            /*
            if (logger.isWarnEnabled()) {
                logger.warn("An Exception occurred while converting entity to json");
                logger.warn(ExceptionUtils.getFullStackTrace(exception));
            }

             */
        }
        return jsonObject;
    }

    public static <T> T toClass(JSONObject json, Class<T> objectClass) {
        T object = null;

        if (Objects.nonNull(json)) {
            try {
                object = mapper.readValue(json.toString(), objectClass);
            } catch (Exception exception) {
                exception.printStackTrace();
                /*
                if (logger.isWarnEnabled()) {
                    logger.warn("An Exception occurred while converting json to entity");
                    logger.warn(ExceptionUtils.getFullStackTrace(exception));
                }

                 */
            }
        }
        return object;
    }

    public static <T> T toClass(String jsonString, Class<T> objectClass) {
        T object = null;

        if (Objects.nonNull(jsonString)) {
            try {
                object = mapper.readValue(jsonString, objectClass);
            } catch (Exception exception) {
                exception.printStackTrace();
                /*
                if (logger.isWarnEnabled()) {
                    logger.warn("An Exception occurred while converting json to entity");
                    logger.warn(ExceptionUtils.getFullStackTrace(exception));
                }
                 */
            }
        }
        return object;
    }

    public static <T> List<T> toObjectList(String jsonString, Class<T> objectClass) {
        List<T> list = null;

        if (Objects.nonNull(jsonString)) {
            try {
                JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, objectClass);
                list = mapper.readValue(jsonString, type);
            } catch (Exception exception) {
                exception.printStackTrace();
                /*
                if (logger.isWarnEnabled()) {
                    logger.warn("An Exception occurred while converting json to entity");
                    logger.warn(ExceptionUtils.getFullStackTrace(exception));
                }

                 */
            }
        }
        return list;
    }
}
