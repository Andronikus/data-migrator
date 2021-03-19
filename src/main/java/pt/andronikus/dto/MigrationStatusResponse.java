package pt.andronikus.dto;

public class MigrationStatusResponse {
    private String message;

    public MigrationStatusResponse() {
    }

    public MigrationStatusResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
