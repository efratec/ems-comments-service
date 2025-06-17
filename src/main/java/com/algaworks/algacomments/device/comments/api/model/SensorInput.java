package com.algaworks.algacomments.device.comments.api.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SensorInput {

    @NotBlank
    private String name;
    @NotBlank
    private String ip;
    @NotBlank
    private String location;
    @NotBlank
    private String protocol;
    @NotBlank
    private String model;

}
