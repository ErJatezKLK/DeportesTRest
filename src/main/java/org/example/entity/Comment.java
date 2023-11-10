package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comentario")
public class Comment implements Serializable {
    @Id
    private Integer id;
    @Column(name = "contenido")
    private String contenido;
    @Column(name = "fecha")
    private Timestamp fecha;
    @Column(name = "hora")
    private String hora;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comments")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comments")
    private Sport sport;

}
