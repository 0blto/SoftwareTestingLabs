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
ssh -L 8080:stload.se.ifmo.ru:8080 %username%@se.ifmo.ru -p 2222
endlocal