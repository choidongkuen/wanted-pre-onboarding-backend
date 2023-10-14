docker run --name pre_onboarding_mysql \
-e MYSQL_ROOT_PASSWORD=$(cat .env | grep MYSQL_ROOT_PASSWORD | cut -d '=' -f2) \
-e TZ=Asia/Seoul \
-d -p 3304:3306 mysql:latest