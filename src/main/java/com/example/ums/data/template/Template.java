package com.example.ums.data.template;

import com.example.ums.data.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @OneToMany(mappedBy = "template")
    protected List<TemplateItemUse> items = new ArrayList<>();

    public Template(String templateName, String serviceType) {
        this.templateName = templateName;
        this.serviceType = serviceType;
    }

    public void addItem(TemplateItemUse item) {
        this.items.add(item);
    }

}
