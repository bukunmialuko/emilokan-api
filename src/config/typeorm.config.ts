import { TypeOrmModuleOptions } from '@nestjs/typeorm';
import { User } from 'src/features/user/user.entity';

export const typeOrmConfig: TypeOrmModuleOptions = {
  type: 'postgres',
  host: 'localhost',
  port: 5432,
  username: 'postgres',
  password: 'postgres',
  database: 'taskmanagement',
  entities: [User],
  synchronize: true,
};
