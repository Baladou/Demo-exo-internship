package com.exo.demo.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Role")
public class Role {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    @NonNull
    private String name;

    @OneToMany(targetEntity=User.class, mappedBy="role",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();


}