package ir.iust.computer.ood.evar.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Document
public class Advertise {
    @Id
    private String advertiseId;
    private String title;
    private double price;
    private Date createDate = new Date();
    private int rate;
    private Map<String,String> advertiseSpec = new HashMap<>();
}
