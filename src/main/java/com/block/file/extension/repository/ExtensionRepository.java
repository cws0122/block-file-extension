package com.block.file.extension.repository;

import com.block.file.extension.entity.Extension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExtensionRepository extends JpaRepository<Extension, Long> {
    List<Extension> findByFixedTrueAndDeletedFalseOrderByIdAsc();
    List<Extension> findByFixedFalseAndDeletedFalseOrderByIdAsc();
    Optional<Extension> findByNameAndDeletedFalse(String name);
    int countByFixedFalseAndDeletedFalse();
}
