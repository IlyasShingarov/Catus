package com.ishingarov.catus.model.entity;

import com.ishingarov.catus.model.UserRole;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users", schema = "catus")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "login", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String login;

    @NotNull
    @Column(name = "name", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String name;

    @Column(name = "avatar_url", nullable = true)
    @Type(type = "org.hibernate.type.TextType")
    private String avatarUrl;

    @NotNull
    @Column(name = "role", nullable = false)
//    @Type(type = "org.hibernate.type.TextType")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @NotNull
    @Column(name = "password", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "in_group")
    private Group inGroup;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "createdBy")
    private Set<Task> tasks = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "project_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> projects = new LinkedHashSet<>();

    @OneToMany(mappedBy = "createdBy")
    private Set<Project> managedProjects = new LinkedHashSet<>();

    @OneToMany(mappedBy = "createdBy")
    private Set<Comment> comments = new LinkedHashSet<>();


    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "login = " + login + ", " +
                "name = " + name + ", " +
                "title = " + role + ", " +
                "password = " + password + ", " +
                "isActive = " + isActive + ")";
    }
}
