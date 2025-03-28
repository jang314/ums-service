package com.example.ums.data.cust;

import com.example.ums.data.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cust extends BaseTimeEntity {
    @Id
    @Column(name = "cust_id")
    private String id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "pCust", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Cust> departments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_cust_id")
    private Cust pCust;

    @OneToMany(mappedBy = "cust", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    // 고객사 생성
    public Cust(String id, String name, String description, Cust... departments) {
        this.id = id;
        this.name = name;
        this.description = description;

        for(Cust department : departments) {
            addDepartment(department);
        }
    }

    private void setPCust(Cust cust) {
        if(this.pCust != cust) {
            this.pCust = cust;
        }
    }

    private void addDepartment(Cust department) {
        department.setPCust(this);
        if(!this.departments.contains(department)) {
            this.departments.add(department);
        }
    }

    public Cust getDepartment(String id) {
        return this.departments.stream()
                .filter(dept -> dept.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
