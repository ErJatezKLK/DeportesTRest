package org.example.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.SportEntity;
import org.example.entity.TeamEntity;

import javax.validation.constraints.Positive;
import java.sql.Timestamp;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TeamDto {
    @Positive
    private Integer id;
    private String name;
    private Timestamp creationDate;
    private String country;

    public static TeamEntity toEntity(TeamDto dto) {
        return new TeamEntity(
                dto.getId(),
                dto.getName(),
                dto.getCreationDate(),
                dto.getCountry(),
                new ArrayList<>(),
                new SportEntity(),
                new ArrayList<>(),
                new ArrayList<>()
        );
    }
    public static TeamDto toDto(TeamEntity entity) {
        return new TeamDto(
                entity.getId(),
                entity.getName(),
                entity.getCreationDate(),
                entity.getCountry()
        );
    }
}
