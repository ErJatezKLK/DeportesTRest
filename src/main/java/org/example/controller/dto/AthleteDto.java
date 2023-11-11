package org.example.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Athlete;
import org.example.entity.Sport;
import org.example.entity.Team;
import org.example.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AthleteDto {
    @NotNull
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String position;
    @NotNull
    private Integer age;
    @NotBlank
    private String nacionality;
    @NotBlank
    private String nickName;
    @NotBlank
    private Integer titles;
    @NotEmpty
    private Team team;
    @NotEmpty
    private Sport sport;

    public static Athlete toEntity(AthleteDto dto) {
        return new Athlete(
                dto.getId(),
                dto.getName(),
                dto.getSurname(),
                dto.getPosition(),
                dto.getAge(),
                dto.getNacionality(),
                dto.getNickName(),
                dto.getTitles(),
                new Team(),
                new Sport(),
                new ArrayList<>()
        );
    }

    public static AthleteDto toDto(AthleteDto entity) {
        return new AthleteDto(
                entity.getId(),
                entity.getName(),
                entity.getSurname(),
                entity.getPosition(),
                entity.getAge(),
                entity.getNacionality(),
                entity.getNickName(),
                entity.getTitles(),
                new Team(),
                new Sport()
        );
    }
}
