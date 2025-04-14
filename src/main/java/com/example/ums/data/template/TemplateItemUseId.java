package com.example.ums.data.template;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TemplateItemUseId implements Serializable {
    @Column(name = "template_id")
    private Long templateId;

    @Column(name = "item_id")
    private Long itemId;
}
