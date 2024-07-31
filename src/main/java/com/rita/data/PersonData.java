package com.rita.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class PersonData {
    private String ipAddress;
    private String firstName;
    private String lastName;
    private String email;

}
