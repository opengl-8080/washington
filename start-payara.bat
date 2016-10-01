@echo off

cd /d %PAYARA_HOME%\bin

title Payara

asadmin.bat start-domain -v -d domain1

pause