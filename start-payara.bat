@echo off

pushd %PAYARA_HOME%\bin

start "Payara" asadmin.bat start-domain -v -d domain1

popd
