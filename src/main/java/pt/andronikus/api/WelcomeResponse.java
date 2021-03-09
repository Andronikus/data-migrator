package pt.andronikus.api;

import com.fasterxml.jackson.annotation.JsonProperty;


public class WelcomeResponse {
    @JsonProperty
    private String name;

    // private LocalDateTime now;

    public WelcomeResponse() {
    }

    public WelcomeResponse(String name) {
        this.name = name;
        // this.now = now;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
    public LocalDateTime getNow() {
        return now;
    }

    public void setNow(LocalDateTime now) {
        this.now = now;
    }
     */

    @Override
    public String toString() {
        return "WelcomeResponse{" +
                "name='" + name + '\'' +
                '}';
    }
}
