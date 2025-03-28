package com.example.ums.data.send;

import com.example.ums.data.Contact;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Receiver {
    @Id
    @Column(name = "receiver_id")
    private Long id;
    private String name;
    @Embedded
    private Contact contact;

    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    private List<SendRequest> requestList = new ArrayList<>();
}
