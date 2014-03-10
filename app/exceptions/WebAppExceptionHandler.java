package exceptions;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;

import java.util.*;

public class WebAppExceptionHandler {
    private Integer errorCode;
    private String errorMessage;

    public WebAppExceptionHandler(Integer errorCode, String errorMessage) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public JsonNode getMessage() {
        Map<String, String> errorMap = new HashMap<String, String>();
//        List<Map> errorList = new ArrayList<Map>();
        errorMap.put("errorCode", this.getErrorCode().toString());
        errorMap.put("error", this.getErrorMessage());
        return (Json.toJson(errorMap));
    }
}
