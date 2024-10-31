package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class UsuarioModel {
    @Expose(serialize = false)
    private int userId;
    @Expose
    private String name;
    @Expose
    private String password;
    @Expose
    private String email;
    @Expose
    private String role;
}