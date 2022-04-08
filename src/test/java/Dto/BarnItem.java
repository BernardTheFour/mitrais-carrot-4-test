package Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor
@NoArgsConstructor
public class BarnItem {
    private String name;
    private String initialCarrot;
    private String  birthdayCarrot;
    private String startDate;
    private String endDate;    
}
