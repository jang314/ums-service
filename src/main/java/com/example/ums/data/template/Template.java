package com.example.ums.data.template;

import com.example.ums.data.BaseTimeEntity;
import com.example.ums.request.template.RequestTemplate;
import com.example.ums.service.template.ItemDto;
import com.example.ums.service.template.TemplateDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "CHANNEL")
public abstract class Template  {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEMPLATE_ID")
    protected Long id;

    @Comment("템플릿 제목")
    protected String templateName;

    @Comment("서비스 유형")
    protected String serviceType;

    @Comment("아이템 리스트")
    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    protected List<TemplateItemUse> items = new ArrayList<>();


    public Template(String templateName, String serviceType, TemplateItem... items) {
        this.templateName = templateName;
        this.serviceType = serviceType;

        for(TemplateItem item : items) {
            TemplateItemUse itemUse = new TemplateItemUse(this, item);
            addItemUse(itemUse);
            item.addItem(itemUse);
        }
    }

    public Template(Long id, String templateName, String serviceType, TemplateItem... items) {
        this.id = id;
        this.templateName = templateName;
        this.serviceType = serviceType;

        for(TemplateItem item : items) {
            TemplateItemUse itemUse = new TemplateItemUse(this, item);
            addItemUse(itemUse);
            item.addItem(itemUse);
        }
    }

    public void addItemUse(TemplateItemUse itemUse) {
        this.items.add(itemUse);
    }

    private void setId(Long id) {
        this.id = id;
    }


    public void setTemplateItem(TemplateItemUse itemUse, TemplateItem item) {
        itemUse.setItem(item);
        this.items.add(itemUse);
    }

    public static Template modify(Long id,Template toTemplate, TemplateItem... items) {
        toTemplate.setId(id);
        toTemplate.applyTo(toTemplate);

        for(TemplateItem item : items) {
            TemplateItemUse itemUse = new TemplateItemUse(toTemplate, item);
            toTemplate.addItemUse(itemUse);
        }

        return toTemplate;
    }

    protected abstract void applyTo(Template toTemplate);

    public void setNameAndType(String templateName, String serviceType) {
        this.templateName = templateName;
        this.serviceType = serviceType;
    }

    public List<TemplateItemUse> addItems(List<TemplateItem> items) {
         return items.stream()
                .map(item -> {
                    TemplateItemUse itemUse = new TemplateItemUse(this, item); // itemUse.template, itemUse.item set
                    item.addItem(itemUse);  // item.itemUse set
                    return itemUse;
                })
                .map(itemUse -> {
                    this.getItems().add(itemUse); // template.itemUse set
                    return itemUse;
                })
                .collect(Collectors.toList());
    }
}
