package com.project.monica.snobsinenobilitate.models.rest;

import android.text.TextUtils;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.monica.snobsinenobilitate.utils.DeveloperConstants;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

/**
 * {@link Converter} that uses Jackson for reading and writing entities.
 */
public class JacksonConverter implements Converter {
  private static final String MIME_TYPE = "application/json; charset=UTF-8";

  private static ObjectMapper objectMapper = null;

  static {
    objectMapper = new ObjectMapper();
  }

  @Override public Object fromBody(TypedInput body, Type type) throws ConversionException {
    try {
      JavaType javaType = objectMapper.getTypeFactory().constructType(type);
      return objectMapper.readValue(body.in(), javaType);
    } catch (JsonParseException e) {
      throw new ConversionException(e);
    } catch (JsonMappingException e) {
      throw new ConversionException(e);
    } catch (IOException e) {
      throw new ConversionException(e);
    }
  }

  @Override public TypedOutput toBody(Object object) {
    try {
      String json = objectMapper.writeValueAsString(object);
      return new TypedByteArray(MIME_TYPE, json.getBytes("UTF-8"));
    } catch (JsonProcessingException e) {
      throw new AssertionError(e);
    } catch (UnsupportedEncodingException e) {
      throw new AssertionError(e);
    }
  }

  public static <T> T getItem(String json, Class<T> clazz) {
    if (!TextUtils.isEmpty(json)) {

      try {
        return objectMapper.readValue(json, clazz);
      } catch (JsonParseException e) {
        if (DeveloperConstants.LOGGING_ENABLED) {
          e.printStackTrace();
        }
      } catch (JsonMappingException e) {
        if (DeveloperConstants.LOGGING_ENABLED) {
          e.printStackTrace();
        }
      } catch (IOException e) {
        if (DeveloperConstants.LOGGING_ENABLED) {
          e.printStackTrace();
        }
      } catch (Exception e) {
        if (DeveloperConstants.LOGGING_ENABLED) {
          e.printStackTrace();
        }
      }
    }
    return null;
  }

  public static String toJson(Object item) {
    if (item != null) {

      try {
        return objectMapper.writeValueAsString(item);
      } catch (JsonGenerationException e) {
        if (DeveloperConstants.LOGGING_ENABLED) {
          e.printStackTrace();
        }
      } catch (JsonMappingException e) {
        if (DeveloperConstants.LOGGING_ENABLED) {
          e.printStackTrace();
        }
      } catch (IOException e) {
        if (DeveloperConstants.LOGGING_ENABLED) {
          e.printStackTrace();
        }
      } catch (Exception e) {
        if (DeveloperConstants.LOGGING_ENABLED) {
          e.printStackTrace();
        }
      }
    }

    return null;
  }
}
