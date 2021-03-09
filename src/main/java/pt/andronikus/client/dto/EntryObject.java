package pt.andronikus.client.dto;

public class EntryObject {
    private String key;
    private String value;

    public EntryObject() {
    }

    public EntryObject(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "EntryObject{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
