package com.archisacademy.morent.services.concretes;

import com.archisacademy.morent.dtos.requests.FeedbackRequest;
import com.archisacademy.morent.dtos.responses.FeedbackResponse;
import com.archisacademy.morent.entities.Feedback;
import com.archisacademy.morent.repositories.FeedbackRepository;
import com.archisacademy.morent.repositories.UserRepository;
import com.archisacademy.morent.services.abstracts.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;

    @Override
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

}
