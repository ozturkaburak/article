package com.ahmetburak.article.entity;

import com.ahmetburak.article.enumeration.RoleEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by ahmetburakozturk on 18.08.2022
 **/
@Entity
@Table(name = "RoleEntity", indexes = {
        @Index(name = "idx_role_entity_name", columnList = "name")
})
@Getter
@Setter
@EqualsAndHashCode
public class Role implements Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleEnum name;

    //    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_entity_id")
    private User userEntity;

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
