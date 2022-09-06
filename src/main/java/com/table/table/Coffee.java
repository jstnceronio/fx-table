package com.table.table;
import lombok.*;

@AllArgsConstructor
public class Coffee {
    @Getter
    private String name;
    @Getter
    private double prize;
    @Getter
    private String type;
}
