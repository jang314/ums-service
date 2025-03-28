package com.example.ums.data.template;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TemplateItemRepository extends JpaRepository<TemplateItem, Long> {
    List<TemplateItem> findByItemType(String type);
}
