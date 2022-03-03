package test.utils;

public class ViStringBuilder {

    private StringBuilder stringBuilder = new StringBuilder();

    public String getStringValue() {
        return stringBuilder.toString();
    }

    public void appendIfNotEmpty(String str) {
        if (str != null && !str.isEmpty()) {
            stringBuilder.append(str);
        }
    }

    public void appendIfNotEmpty(String prefix, String str) {
        if (str != null && !str.isEmpty()) {
            stringBuilder.append(prefix).append(str);
        }
    }

    public void appendIfNotEmpty(String prefix, String str, String suffix) {
        if (str != null && !str.isEmpty()) {
            stringBuilder.append(prefix).append(str).append(suffix);
        }
    }

    public void append(String str) {
        stringBuilder.append(str);
    }
}
