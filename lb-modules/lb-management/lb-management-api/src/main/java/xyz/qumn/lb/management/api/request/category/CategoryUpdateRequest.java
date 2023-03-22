package xyz.qumn.lb.management.api.request.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryUpdateRequest {
    Long catId;
    String name;
    int priority;
}
