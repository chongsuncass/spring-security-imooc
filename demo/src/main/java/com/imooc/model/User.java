package com.imooc.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class User {
    @NotNull
    @Min(1)
    private Integer id;

    @NotNull
    private String username;
    @NotNull
    private String password;

}
