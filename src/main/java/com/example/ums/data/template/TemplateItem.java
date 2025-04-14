package com.example.ums.data.template;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class TemplateItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Comment("템플릿 아이템 타입")
    private String itemType;

    @Comment("템플릿 아이템 위치/값")
    private String itemValue;

    @Comment("템플릿 아이템 확장자")
    private String itemExt;

    @OneToMany(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    private List<TemplateItemUse> templates = new ArrayList<>();

    public TemplateItem(Long id, String itemType, String itemValue, String itemExt) {
        this.id = id;
        this.itemType = itemType;
        this.itemValue = itemValue;
        this.itemExt = itemExt;
    }

    public TemplateItem(TemplateItem item, TemplateItemUse itemUse) {
        this.id = item.getId();
        this.itemType = item.getItemType();
        this.itemValue = item.getItemValue();
        this.itemExt = item.getItemExt();

        for(TemplateItemUse use : item.getTemplates()) {
            addItem(use);
        }
        addItem(itemUse);
    }

    public void addItem(TemplateItemUse itemUse) {
        if(!this.templates.contains(itemUse)) {
            this.templates.add(itemUse);
        }
    }
}
