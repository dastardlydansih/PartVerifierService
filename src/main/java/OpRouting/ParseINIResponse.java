package OpRouting;

public class ParseINIResponse {
        final private String status;
        final private String partNumber;
        final private String attribute;
        final private String value;

    public ParseINIResponse(String partNumber, String attribute, String value, String status) {
        this.status = status;
        this.partNumber = partNumber;
        this.attribute = attribute;
        this.value = value;
    }

        public String getStatus() {
        return status;
    }

        public String getPartNumber() {
            return partNumber;
        }

        public String getAttribute() {
            return attribute;
        }

        public String getValue() { return value; }
}
