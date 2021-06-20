@echo off

java -version

pushd .
cd ..
mkdir bin
mkdir jar
popd

:: class files remove
::del /Q ..\bin\* >nul 2>&1
del /Q ..\bin\*

echo [*] building src
::javac -verbose -encoding utf-8 -Xlint:unchecked -Xlint:-deprecation -d ..\bin\ -s ..\src\ ..\src\* 2> Buildlog.log
javac -verbose -encoding utf-8 -Xlint:unchecked -Xlint:-deprecation -d ..\bin\ -s ..\src\ ..\src\*

:: check comfile error
if NOT ERRORLEVEL 0 (
echo [!] Build error. %ERRORLEVEL%
goto exit
)

:: check menifest
if NOT EXIST manifest.txt (
echo [!] No menifest.txt file found. use default.
echo Main-class: LogFilterMain > ..\manifest.txt
)

pushd .
cd ..\bin
echo [*] making jar.
::jar -cvmf ..\manifest.txt ..\jar\logfilter.jar * >> ..\build\Buildlog.log
jar -cvmf ..\manifest.txt ..\jar\logfilter.jar *
popd

echo [*] jar is made.


:exit

