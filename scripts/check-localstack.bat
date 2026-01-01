@echo off
echo ===== LocalStack Status Check =====
echo.
echo Checking Docker...
docker --version
echo.
echo Checking LocalStack container...
docker ps -a --filter "name=localstack"
echo.
echo Starting LocalStack if not running...
docker start localstack
echo.
echo Waiting 5 seconds...
timeout /t 5 /nobreak >nul
echo.
echo Checking LocalStack status...
docker ps --filter "name=localstack"
echo.
echo Testing LocalStack health endpoint...
curl -s http://localhost:4566/_localstack/health
echo.
echo.
echo ===== Check Complete =====
pause

