package com.example.ums.data.template;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateItemUseRepository extends JpaRepository<TemplateItemUse, Long> {
    List<TemplateItemUse> findByTemplateId(Long id);
}
