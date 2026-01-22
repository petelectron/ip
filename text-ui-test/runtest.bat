@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ip\bin mkdir ip\bin

REM delete output from previous run
if exist ip\bin\ACTUAL.TXT del ip\bin\ACTUAL.TXT


REM compile the code into the bin folder
javac  -cp ip\src\main\java -Xlint:none -d ip\bin ip\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ip\bin Mortis < ip\text-ui-test\input.txt > ip\bin\ACTUAL.TXT

REM compare the output to the expected output
FC ip\bin\ACTUAL.TXT ip\text-ui-test\EXPECTED.TXT
