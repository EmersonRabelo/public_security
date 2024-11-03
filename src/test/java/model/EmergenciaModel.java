package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class EmergenciaModel {
    @Expose(serialize = false)
    private int emergencyId;
    @Expose
    private String requesterEmail;
    @Expose
    private String address;
    @Expose
    private String type;
    @Expose
    private String title;
    @Expose
    private String description;
    @Expose
    private String status;
}