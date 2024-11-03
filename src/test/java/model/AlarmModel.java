package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class AlarmModel {
    @Expose(serialize = false)
    private int alarmId;
    @Expose
    private String type;
    @Expose
    private String status;
    @Expose
    private String alarmDate;
}