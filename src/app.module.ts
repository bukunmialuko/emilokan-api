import { Module } from '@nestjs/common';
import { EventModule } from './event/event.module';
import { AuthModule } from './auth/auth.module';
import { UserModule } from './user/user.module';
import { EventSubscriptionModule } from './event_subscription/event_subscription.module';
import { SubscriptionModule } from './subscription/subscription.module';
import { ReviewModule } from './review/review.module';

@Module({
  imports: [EventModule, AuthModule, UserModule, EventSubscriptionModule, SubscriptionModule, ReviewModule],
})
export class AppModule {}
