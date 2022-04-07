package Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class BarnItem {
    private String name;
    private String initialCarrot;
    private String  birthdayCarrot;
    private String startDate;
    private String endDate;    
}
