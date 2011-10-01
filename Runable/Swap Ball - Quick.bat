@ECHO OFF
FOR /F "skip=2 tokens=2*" %%A IN ('REG QUERY "HKLM\Software\JavaSoft\Java Runtime Environment" /v CurrentVersion') DO set CurVer=%%B
FOR /F "skip=2 tokens=2*" %%A IN ('REG QUERY "HKLM\Software\JavaSoft\Java Runtime Environment\%CurVer%" /v JavaHome') DO set JAVA_HOME=%%B
FOR /F "skip=2 tokens=2*" %%A IN ('REG QUERY "HKLM\Software\Wow6432Node\JavaSoft\Java Runtime Environment" /v CurrentVersion') DO set CurVer=%%B
FOR /F "skip=2 tokens=2*" %%A IN ('REG QUERY "HKLM\Software\Wow6432Node\JavaSoft\Java Runtime Environment\%CurVer%" /v JavaHome') DO set JAVA_HOME=%%B
CLS
IF "%JAVA_HOME%"=="" GOTO ERROR
SET PATH=%PATH%;%JAVA_HOME%\bin
GOTO PROGRAM

:ERROR
echo.
echo.Please install JDK or JRE and set path enviroment for it
echo.Please change PATH in environment variables if don't run
echo.
PAUSE
exit

:PROGRAM
SETLOCAL ENABLEEXTENSIONS
SETLOCAL ENABLEDELAYEDEXPANSION

SET TIMEDELAY="500"
SET ALGO="QUICK"

:menu
CLS
echo.+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
echo.+	Author 	 : Mac Van Anh					
echo.+	Class  	 : K15CMU-TCD2 - Duy Tan University
echo.+	Project	 : Computer Science For Practicing Engineers
echo.+	Languages: Java, Batch Script
echo.+	Tools	 : Eclipse, Bat To Exe Converter
echo.+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
echo.
echo.= Menu ========================================================================
echo.
for /f "tokens=1,2,* delims=_ " %%A in ('"findstr /b /c:":menu_" "%~f0""') do (
	echo. %%B - %%C
	echo. -------------------------------------------------------
)
set choice=
echo.&set /p choice=Make a choice or hit Enter to quit: || GOTO::EOF
echo.&call:menu_%choice%
GOTO:menu

:menu_1 Run with Command 			
java -jar Ball.jar DOS %ALGO% %TIMEDELAY%
echo.
PAUSE
GOTO:EOF

:menu_2 Run with Window 			
java -jar Ball.jar WIN %ALGO% %TIMEDELAY%
PAUSE
GOTO:EOF

:menu_3 Run All				
java -jar Ball.jar WINDOS %ALGO% %TIMEDELAY%
PAUSE
GOTO:EOF

:menu_4 Calculator numbers of swap ball
java -jar Ball.jar CALC_2
PAUSE
GOTO:EOF