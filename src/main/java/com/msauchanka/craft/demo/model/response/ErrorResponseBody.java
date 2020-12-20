package com.msauchanka.craft.demo.model.response;

import com.msauchanka.craft.demo.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseBody implements IResponseBody {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Error {
        private ErrorCode code;
        private String message;
    }

    private Error error;
}
