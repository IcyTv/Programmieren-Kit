@echo off
SET path=%USERPROFILE%\bin
set /p path= "Enter path to install to (default %USERPROFILE%\bin): "
CALL :NORMALIZEPATH %path%
set path=%RETVAL%
mkdir %path%>NUL 2>NUL
del %path%\checkstyle.bat>NUL 2>NUL
echo @echo off >> %path%\checkstyle.bat
echo python3 %path%\checkstyle.py %%* >> %path%\checkstyle.bat
copy .\checkstyle.py %path%
%SystemRoot%\system32\SETX.exe PATH "%PATH%";"%path%";
:NORMALIZEPATH
  SET RETVAL=%~f1
  EXIT /B