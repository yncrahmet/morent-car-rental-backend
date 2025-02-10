package com.archisacademy.morent.services.concretes;

import com.archisacademy.morent.dtos.requests.FeedbackRequest;
import com.archisacademy.morent.dtos.responses.FeedbackAdminResponse;
import com.archisacademy.morent.dtos.responses.FeedbackResponse;
import com.archisacademy.morent.entities.Feedback;
import com.archisacademy.morent.repositories.FeedbackRepository;
import com.archisacademy.morent.repositories.UserRepository;
import com.archisacademy.morent.services.abstracts.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    @CacheEvict(value = "allFeedbacks", allEntries = true)
    public FeedbackResponse save(FeedbackRequest feedbackRequest) {

        return userRepository.findByUserId(feedbackRequest.getUserId())
                .map(user -> {
                    Feedback feedback = new Feedback();
                    feedback.setUser(user);
                    feedback.setFeedbackText(feedbackRequest.getFeedbackText());
                    feedbackRepository.save(feedback);
                    return new FeedbackResponse("Feedback submitted successfully");
                })
                .orElse(new FeedbackResponse("User not found!"));

    }

    @Override
    @Cacheable("allFeedbacks")
    public List<FeedbackAdminResponse> findAll() {
        System.out.println("Now the data came from the database.");
        return feedbackRepository.findAll().stream()
                .map(feedback -> modelMapper.map(feedback, FeedbackAdminResponse.class))
                .collect(Collectors.toList());
    }

}
