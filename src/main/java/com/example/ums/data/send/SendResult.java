package com.example.ums.data.send;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SendResult {
    @Id
    private String resultId;
    @OneToMany(mappedBy = "sendResult")
    private List<SendRequest> requestList = new ArrayList<>();
    private LocalDateTime sendStartDtm;
    private LocalDateTime sendEndDtm;
}
