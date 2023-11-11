package org.example.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Comment;
import org.example.entity.Sport;
import org.example.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    @NotNull
    private Integer id;
    @NotBlank
    private String content;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Timestamp date;
    @NotNull
    @JsonFormat
    private Date hour;
    @NotEmpty
    private User user;
    @NotEmpty
    private Sport sport;

    public static Comment toEntity(CommentDto dto) {
        return new Comment(
                dto.getId(),
                dto.getContent(),
                dto.getDate(),
                dto.getHour(),
                new User(),
                new Sport()
        );
    }

    public static CommentDto toDto(Comment entity) {
        return new CommentDto(
                entity.getId(),
                entity.getContent(),
                entity.getDate(),
                entity.getHour(),
                entity.getUser(),
                entity.getSport()
        );
    }
}
