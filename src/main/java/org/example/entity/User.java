package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "contraseña")
    private String password;

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
