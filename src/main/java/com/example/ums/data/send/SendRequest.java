package com.example.ums.data.send;

import com.example.ums.code.SendType;
import com.example.ums.data.template.Template;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(indexes = @Index(columnList = "requestDtm"))
public class SendRequest {
    @Id
    @Comment("발송요청아이디")
    private String requestId;

    @Comment("수신자")
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Receiver receiver;

    @Comment("발송상태")
    @Enumerated(EnumType.STRING)
    private SendType status;

    @Comment("발송요청일시")
    private LocalDateTime requestDtm;

    @Comment("재시도 횟수")
    private Long retryCnt;

    @Comment("본발송 요청 아이디")
    @ManyToOne
    @JoinColumn(name = "p_request_id")
    private SendRequest pRequestId;

    @OneToMany(mappedBy = "pRequestId", cascade = CascadeType.ALL)
    private List<SendRequest> requestList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "result_id")
    private SendResult sendResult;



}
