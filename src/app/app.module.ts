import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { EventModule } from '../features/event/event.module';
import { AuthModule } from '../features/auth/auth.module';
import { UserModule } from '../features/user/user.module';
import { SubscriptionModule } from '../features/subscription/subscription.module';
import { ReviewModule } from '../features/review/review.module';
import { typeOrmConfig } from '../config/typeorm.config';


@Module({
  imports: [
    TypeOrmModule.forRoot(typeOrmConfig),
    EventModule, AuthModule, UserModule, SubscriptionModule, ReviewModule],
})
export class AppModule {}
