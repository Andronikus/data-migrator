package pt.andronikus.client.dto;

import pt.andronikus.client.utils.JSONUtils;

public class BaseDto {
    public String toJson() {
        return JSONUtils.toJSON(this);
    }
}
