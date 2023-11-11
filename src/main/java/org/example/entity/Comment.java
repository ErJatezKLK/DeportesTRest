package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comentario")
public class Comment implements Serializable {
    @Id
    private Integer id;
    @Column(name = "contenido")
    private String content;
    @Column(name = "fecha")
    private Timestamp date;
    @Column(name = "hora")
    private Date hour;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comments", insertable=false, updatable=false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comments", insertable=false, updatable=false)
    private Sport sport;

}
