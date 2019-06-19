package com.spring.example.service;

import com.spring.example.exception.ResourceNotFoundException;
import com.spring.example.model.Story;
import com.spring.example.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    StoryRepository storyRepository;

    @Override
    public Story createStory(Story story) {
        return storyRepository.save(story);
    }

    @Override
    public Page<Story> getAllStory(int page, int limit) {
        Page<Story> resultPage = storyRepository.findAll(new PageRequest(page,limit));
        if (page > resultPage.getTotalPages())
             throw new ResourceNotFoundException("Story", "id", resultPage);
        return resultPage;
    }

    @Override
    public Story updateStory(Long id, Story storyDetails) {
        Story story = storyRepository.findById(id);
        if(story == null)
            throw new ResourceNotFoundException("Story", "id", id);

        story.setTitle(storyDetails.getTitle());
        story.setBody(storyDetails.getBody());
        story.setAuthor(storyDetails.getAuthor());
        return storyRepository.save(story);
    }

    @Override
    public void deleteStory(Long id) {
        Story story = storyRepository.findById(id);
        if(story == null)
            throw new ResourceNotFoundException("Story", "id", id);

        storyRepository.delete(story);
    }

    @Override
    public List<Story> searchStory(Specification<Story> spec) {
        return storyRepository.findAll(spec);
    }
}
