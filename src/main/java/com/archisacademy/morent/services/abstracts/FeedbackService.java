package com.archisacademy.morent.services.abstracts;

import com.archisacademy.morent.dtos.requests.FeedbackRequest;
import com.archisacademy.morent.dtos.responses.FeedbackResponse;

public interface FeedbackService {

    FeedbackResponse save(FeedbackRequest feedbackRequest);

}
