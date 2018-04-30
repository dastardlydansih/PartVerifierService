package OpRouting;

public class ParseINIResponseRaw {
    final private String status;
    final private String group;
    final private String key;
    final private String value;

    public ParseINIResponseRaw(String group, String key, String value, String status) {
        this.status = status;
        this.group = group;
        this.key = key;
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public String getGroup() {
        return group;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
