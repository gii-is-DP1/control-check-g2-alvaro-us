package org.springframework.samples.petclinic.feeding;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedingService {
	@Autowired
	FeedingRepository fr;
	
    public List<Feeding> getAll(){
        return fr.findAll();
    }

    public List<FeedingType> getAllFeedingTypes(){
        return fr.findAllFeedingTypes();
    }

    public FeedingType getFeedingType(String typeName) {
        return fr.findFeedingTypeByName(typeName);
    }

    public Feeding save(Feeding p) throws UnfeasibleFeedingException {
        return fr.save(p);       
    }

    
}
