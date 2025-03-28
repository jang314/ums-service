package com.example.ums.data.cust;

import com.example.ums.code.EnableType;
import com.example.ums.data.BaseTimeEntity;
import com.example.ums.data.Contact;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User   extends BaseTimeEntity{
    @Id
    private String userId;
    private String password;
    private String name;

    @Embedded
    private Contact contact;

    @ManyToOne
    @JoinColumn(name = "users")
    private Cust cust;

    @Enumerated
    private EnableType enable;

    public User(Cust cust, Contact contact, String userId, String password, String name) {
        this.cust = cust;
        this.contact = contact;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.enable = EnableType.Y;
    }

    public static User disableUser(User user) {
        if(user.getEnable() != EnableType.N) {
            return new User(user, false);
        } else {
            return user;
        }
    }

    public static User enableUser(User user) {
        if(user.getEnable() != EnableType.N) {
            return new User(user, true);
        } else {
            return user;
        }
    }

    private User(User user, boolean enable) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.cust = user.getCust();
        this.contact = user.getContact();
        this.enable = enable ? EnableType.Y : EnableType.N;
    }


}
