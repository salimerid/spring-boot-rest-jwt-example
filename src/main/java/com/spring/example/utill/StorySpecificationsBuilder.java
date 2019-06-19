package com.spring.example.utill;

import com.spring.example.dto.SearchCriteria;
import com.spring.example.model.Story;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import java.util.ArrayList;
import java.util.List;

public class StorySpecificationsBuilder {
    private final List<SearchCriteria> params;

    public StorySpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public StorySpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Story> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification<Story>> specs = new ArrayList<Specification<Story>>();
        for (SearchCriteria param : params) {
            specs.add(new StorySpecification(param));
        }

        Specification<Story> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and(specs.get(i));
        }
        return result;
    }
}
