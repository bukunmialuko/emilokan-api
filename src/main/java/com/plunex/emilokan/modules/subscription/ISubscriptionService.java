package com.plunex.emilokan.modules.subscription;


import com.plunex.emilokan.modules.subscription.dto.SubscriptionRequest;
import com.plunex.emilokan.modules.subscription.dto.SubscriptionResponse;


public interface ISubscriptionService {

    SubscriptionResponse subscribeToEvent(SubscriptionRequest request);

}
