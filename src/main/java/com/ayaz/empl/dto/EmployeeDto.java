package com.ayaz.empl.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class EmployeeDto {

    private Long id;

    private String name;

    private String surName;

    private String email;

    private String password;
}
