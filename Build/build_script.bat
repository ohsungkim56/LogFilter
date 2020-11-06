@echo off

java -version

mkdir Build_output
mkdir Bin

del /Q .\Build_output\*

echo [*] building src
javac -verbose -encoding utf-8 -Xlint:unchecked -d .\Build_output\ -s ..\src\ ..\src\* 2> Buildlog.log

:: check comfile error
if NOT ERRORLEVEL 0 (
echo [!] Build error. %ERRORLEVEL%
goto exit
)

:: check menifest
if NOT EXIST manifest.txt (
echo [!] No menifest.txt file found. use default one.
echo Main-class: LogFilterMain > manifest.txt
)

pushd 
cd Build_output

echo [*] building class files
jar -cvmf ..\manifest.txt ..\Bin\logfilter.jar * >> ..\Buildlog.log
echo [*] Done.

popd

:exit


pause