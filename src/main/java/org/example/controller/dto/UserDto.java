package org.example.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Athlete;
import org.example.entity.Comment;
import org.example.entity.Team;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    @Column(name = "contraseña")
    private String contraseña;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "equipo_usuario",
            joinColumns = {@JoinColumn(name = "equipo")},
            inverseJoinColumns = {@JoinColumn(name = "usuario")})
    private List<Team> teams;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_deportista",
            joinColumns = {@JoinColumn(name = "usuario")},
            inverseJoinColumns = {@JoinColumn(name = "deportista")})
    private List<Athlete> athletes;
}
