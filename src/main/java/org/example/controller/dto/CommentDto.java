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
import javax.validation.constraints.Positive;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    @Positive
    private Integer id;
    @NotBlank
    private String content;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Timestamp date;
    @NotEmpty
    private User user;
    @NotEmpty
    private Sport sport;

    public static Comment toEntity(CommentDto dto) {
        return new Comment(
                dto.getId(),
                dto.getContent(),
                dto.getDate(),
                new User(),
                new Sport()
        );
    }

    public static CommentDto toDto(Comment entity) {
        return new CommentDto(
                entity.getId(),
                entity.getContent(),
                entity.getDate(),
                entity.getUser(),
                entity.getSport()
        );
    }
}
