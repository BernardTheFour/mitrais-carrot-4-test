package Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data @AllArgsConstructor
public class BazaarItem {
    private String name;
    private String description;
    private String image;
    private String price;
    private String stock;
    private String bazaarId;
    private String startDate;
    private String endDate;
}
