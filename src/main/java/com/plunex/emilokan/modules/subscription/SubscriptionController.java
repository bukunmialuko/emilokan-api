package com.plunex.emilokan.modules.subscription;

import com.plunex.emilokan.modules.event.Event;
import com.plunex.emilokan.modules.event.EventService;
import com.plunex.emilokan.modules.subscription.dto.SubscriptionRequest;
import com.plunex.emilokan.modules.subscription.dto.SubscriptionResponse;
import com.plunex.emilokan.modules.user.User;
import com.plunex.emilokan.modules.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/subscriptions/")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping()
    public SubscriptionResponse subscribeToEvent(@RequestBody SubscriptionRequest request) {
        return subscriptionService.subscribeToEvent(request);
    }

}
