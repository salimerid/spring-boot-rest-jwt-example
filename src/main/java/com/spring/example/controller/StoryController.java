package com.spring.example.controller;

import com.spring.example.utill.StorySpecificationsBuilder;
import com.spring.example.model.Story;
import com.spring.example.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class StoryController {

    @Autowired
    StoryService storyService;

    //-------------------create story -------------------
    @PostMapping("/story")
    ResponseEntity<?> createStory(@RequestBody Story request) {
        Story response = storyService.createStory(request);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    //-------read the story in json format with pagination----------
    @RequestMapping(value = "/stories", method = RequestMethod.GET, produces = {"application/json"})
    ResponseEntity<?> getAllStory(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "limit", defaultValue = "5") int limit) {
        Page<Story> response = storyService.getAllStory(page, limit);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    //--------------------update the story --------------------
    @RequestMapping(value = "/story/{id}", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> updateStory(@PathVariable(value = "id") Long storyId, @RequestBody Story storyDetails) {
        Story response = storyService.updateStory(storyId, storyDetails);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    //---------------------delete the story ---------------------
    @DeleteMapping("/story/{id}")
    public ResponseEntity<?> deleteStory(@PathVariable(value = "id") Long storyId) {
        storyService.deleteStory(storyId);
        return ResponseEntity.ok().build();
    }

    //------------------------search  story ------------------------
    @RequestMapping(method = RequestMethod.GET, value = "/story")
    public ResponseEntity<?> search(@RequestParam(value = "search") String search) {
        StorySpecificationsBuilder builder = new StorySpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        Specification<Story> spec = builder.build();
        List<Story> response = storyService.searchStory(spec);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
