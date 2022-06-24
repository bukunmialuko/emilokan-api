package com.plunex.emilokan.modules.subscription;


import com.plunex.emilokan.modules.event.Event;
import com.plunex.emilokan.modules.event.EventService;
import com.plunex.emilokan.modules.subscription.dto.SubscriptionRequest;
import com.plunex.emilokan.modules.subscription.dto.SubscriptionResponse;
import com.plunex.emilokan.modules.user.User;
import com.plunex.emilokan.modules.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService implements ISubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;
    private final EventService eventService;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserService userService,
                               EventService eventService) {
        this.subscriptionRepository = subscriptionRepository;
        this.userService = userService;
        this.eventService = eventService;
    }


    @Override
    public SubscriptionResponse subscribeToEvent(SubscriptionRequest request) {
        User user = userService.getById(request.getUserId());
        Event event = eventService.getById(request.getEventId());

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setEvent(event);

        subscriptionRepository.save(subscription);

        return new SubscriptionResponse(event.getId().toString(), event.getTitle(),
                user.getId().toString(),
                user.getUserName());
    }
}
