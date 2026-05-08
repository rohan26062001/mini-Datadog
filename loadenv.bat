@echo off
echo Loading .env file...
for /f "tokens=*" %%a in (.env) do (
    set %%a
)
echo .env loaded successfully.