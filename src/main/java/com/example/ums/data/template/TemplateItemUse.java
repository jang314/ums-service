package com.example.ums.data.template;

import com.example.ums.data.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class TemplateItemUse  {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEMPLATE_ITEM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TEMPLATE_ID")
    private Template template;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private TemplateItem item;

    public TemplateItemUse(Template template, TemplateItem item) {
        this.template = template;
        this.item = item;

    }
}
