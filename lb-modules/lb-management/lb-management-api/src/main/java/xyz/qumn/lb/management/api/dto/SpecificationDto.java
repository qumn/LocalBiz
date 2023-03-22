package xyz.qumn.lb.management.api.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecificationDto {
    // 以分为单位
    Integer price;
    List<SpecificationAttributeDto> attributes;
}
