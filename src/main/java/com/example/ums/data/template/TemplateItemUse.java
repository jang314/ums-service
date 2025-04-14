package com.example.ums.data.template;

import com.example.ums.data.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class TemplateItemUse  {

    @EmbeddedId
    private TemplateItemUseId id;

    @MapsId("templateId")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TEMPLATE_ID",insertable = false, updatable = false)
    private Template template;

    @MapsId("itemId")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "ITEM_ID", insertable = false, updatable = false)
    private TemplateItem item;

    public TemplateItemUse(Template template, TemplateItem item) {
        this.id = new TemplateItemUseId(template.getId(), item.getId());
        this.template = template;
        this.item = item;
    }


    public void setTemplate(Template template) {
        if(this.template != template) {
            this.template = template;
        }
    }

    public void setItem(TemplateItem item) {
        this.item = item;
    }
}
