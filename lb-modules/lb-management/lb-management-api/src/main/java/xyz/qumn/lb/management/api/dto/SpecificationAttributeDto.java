package xyz.qumn.lb.management.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecificationAttributeDto {
    String key;

    String value;
}
