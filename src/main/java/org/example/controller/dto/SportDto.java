package org.example.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.SportEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SportDto {
    @Positive
    private Integer id;
    @NotBlank
    private String name;

    public static SportEntity toEntity(SportEntity dto) {
        return new SportEntity(
                dto.getId(),
                dto.getName(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );
    }

    public static SportDto toDto(SportEntity entity) {
        return new SportDto(
                entity.getId(),
                entity.getName()
        );
    }
}
