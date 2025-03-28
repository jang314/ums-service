package com.example.ums.data.template;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MOBILE")
public class MobileTemplate extends Template {
    private String subject;
    private String content;
}
