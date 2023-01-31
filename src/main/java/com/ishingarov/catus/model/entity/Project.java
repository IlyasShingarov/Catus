package com.ishingarov.catus.model.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@Entity
@Table(name = "projects")
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @NotNull
    @Column(name = "create_dttm", nullable = false)
    private final LocalDate createDttm = LocalDate.now();
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(name = "title", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String title;
    @Column(name = "description")
    @Type(type = "org.hibernate.type.TextType")
    private String description;
    @Column(name = "status")
    @Type(type = "org.hibernate.type.TextType")
    private String status;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @OneToMany(mappedBy = "project")
    private Set<Task> tasks = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "projects")
    private Set<User> users = new LinkedHashSet<>();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "title = " + title + ", " +
                "description = " + description + ", " +
                "status = " + status + ", " +
                "createDttm = " + createDttm + ")";
    }
}
