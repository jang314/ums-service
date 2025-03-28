package com.example.ums.data.template;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class TemplateItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemType;
    private String itemValue;

    @OneToMany(mappedBy = "item")
    private List<TemplateItemUse> templates = new ArrayList<>();

    public TemplateItem(String itemType, String itemValue) {
        this.itemType = itemType;
        this.itemValue = itemValue;
    }
    public void add(TemplateItemUse itemUse) {
        this.templates.add(itemUse);
    }

}
