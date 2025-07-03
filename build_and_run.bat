@echo off
echo Running Maven build...
call mvn clean install -DskipTests

IF %ERRORLEVEL% NEQ 0 (
    echo Maven build failed. Exiting.
    exit /b %ERRORLEVEL%
)

echo Maven build successful. Building and running Docker Compose in detached mode...
docker-compose up --build -d

IF %ERRORLEVEL% NEQ 0 (
    echo Docker Compose failed.
    exit /b %ERRORLEVEL%
)

echo Application started successfully in detached mode.

echo Cleaning the system...
docker system prune -f
