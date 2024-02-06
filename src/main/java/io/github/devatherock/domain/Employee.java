package io.github.devatherock.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
    private String id = "x12345";
    private String firstName = "Test";
    private String lastName = "User";
    private String name = "Test User";
    private String dateOfBirth = "01-01-1970";
}
