package com.spring.example.repository;

import com.spring.example.model.Story;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends PagingAndSortingRepository<Story, Long>, JpaSpecificationExecutor<Story> {
    Story findById(Long id);
}
