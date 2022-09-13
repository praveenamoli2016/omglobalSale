package com.omglobal.omglobal.model.entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity(name = "role")
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UniqueRoleForStore", columnNames = {"role_name", "store_id"})
})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @JsonIdentityReference(alwaysAsId=true)
    @ManyToOne
    @JoinColumn (name = "store_id")
    private Store store;

    public Role() {
    }

    public Role(String roleName, String description, Status status, String createdAt, Store store) {
        this.roleName = roleName;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.store = store;
    }
//    @ManyToMany(mappedBy = "roleSet")
//    @JsonIgnore
//    private Set<User> userSet;

}
