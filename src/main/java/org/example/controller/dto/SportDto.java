package org.example.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Athlete;
import org.example.entity.Comment;
import org.example.entity.Sport;
import org.example.entity.User;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SportDto {
    @NotNull
    private Integer id;
    @NotBlank
    private String name;

    public static Sport toEntity(Sport dto) {
        return new Sport(
                dto.getId(),
                dto.getName(),
                new ArrayList<>(),
                new Athlete()
        );
    }

    public static SportDto toDto(Sport entity) {
        return new SportDto(
                entity.getId(),
                entity.getName()
        );
    }
}
