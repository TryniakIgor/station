package com.work.station.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", uniqueConstraints = @UniqueConstraint (name = "user_name_uniq", columnNames = "name"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;
}
