package com.msauchanka.craft.demo.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BadRequestResponseBody implements IResponseBody {
    private Integer code;
    private String msg;
    @JsonProperty("service_version")
    private String serviceVersion;
}
