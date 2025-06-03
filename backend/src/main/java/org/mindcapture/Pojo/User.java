package org.mindcapture.Pojo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class User {
    public int id;
    public String name;

    @NotNull
    public String password;
    public String email;

    @NotNull
    public String phone;
}
