package constants;

public final class Constants {
// Class for easily maintaining the test parameters

    private Constants() {
        // Restrict any instantiation
    }

    // ENDPOINT URLs
    public static final String URL_GET_BY_STATUS = "https://petstore.swagger.io/v2/pet/findByStatus?status=";
    public static final String URL_POST = "https://petstore.swagger.io/v2/pet";
    public static final String URL_PUT = "https://petstore.swagger.io/v2/pet";
    public static final String URL_DELETE = "https://petstore.swagger.io/v2/pet/";

    // REQUEST/RESPONSE JSONs
    public static final String JSON_POST = "{"
            + "  \"id\": %s,"
            + "  \"category\": {"
            + "    \"id\": 0,"
            + "    \"name\": \"string\""
            + "  },"
            + "  \"name\": \"%s\","
            + "  \"photoUrls\": ["
            + "    \"string\""
            + "  ],"
            + "  \"tags\": ["
            + "    {"
            + "      \"id\": 0,"
            + "      \"name\": \"string\""
            + "    }"
            + "  ],"
            + "  \"status\": \"available\""
            + "}";

    public static final String JSON_POST_RESPONSE = "{"
            + "\"id\":%s,"
            + "\"category\":{"
            + "\"id\":0,"
            + "\"name\":\"string\""
            + "},"
            + "\"name\":\"%s\","
            + "\"photoUrls\":["
            + "\"string\""
            + "],"
            + "\"tags\":["
            + "{"
            + "\"id\":0,"
            + "\"name\":\"string\""
            + "}"
            + "],"
            + "\"status\":\"available\""
            + "}";

    public static final String JSON_PUT = "{"
            + "  \"id\": %s,"
            + "  \"category\": {"
            + "    \"id\": 0,"
            + "    \"name\": \"string\""
            + "  },"
            + "  \"name\": \"%s\","
            + "  \"photoUrls\": ["
            + "    \"string\""
            + "  ],"
            + "  \"tags\": ["
            + "    {"
            + "      \"id\": 0,"
            + "      \"name\": \"string\""
            + "    }"
            + "  ],"
            + "  \"status\": \"%s\""
            + "}";

    public static final String JSON_PUT_RESPONSE = "{"
            + "\"id\":%s,"
            + "\"category\":{"
            + "\"id\":0,"
            + "\"name\":\"string\""
            + "},"
            + "\"name\":\"%s\","
            + "\"photoUrls\":["
            + "\"string\""
            + "],"
            + "\"tags\":["
            + "{"
            + "\"id\":0,"
            + "\"name\":\"string\""
            + "}"
            + "],"
            + "\"status\":\"%s\""
            + "}";

    public static final String JSON_DELETE_RESPONSE = "{\"code\":%s,\"type\":\"unknown\",\"message\":\"%s\"}";
}
