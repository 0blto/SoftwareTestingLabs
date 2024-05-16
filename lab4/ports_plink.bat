@echo off
chcp 65001 > nul 2>&1
setlocal
rem 
set /p username=Введите имя пользователя: 
rem 
if "%username%"=="" (
    echo Необходимо ввести имя пользователя.
    exit /b
)
rem 
set /p password=Введите пароль: 
rem 
if "%password%"=="" (
    echo Необходимо ввести пароль.
    exit /b
)
rem 
echo ls | plink -ssh %username%@se.ifmo.ru -P 2222 -pw %password% -L 8080:stload.se.ifmo.ru:8080
endlocal