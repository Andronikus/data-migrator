package pt.andronikus.client.dto;

import pt.andronikus.client.enums.ExecutionsModes;

public class AsyncExecutionMode extends ExecutionMode{
    public AsyncExecutionMode(String callbackUrl) {
        super(ExecutionsModes.ASYNC, callbackUrl);
    }
}
