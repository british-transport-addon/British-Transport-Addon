@echo off
setlocal enabledelayedexpansion

rem Define the color variable
set "color=red"

rem Check if a file was dragged onto the script
if "%~1"=="" (
    echo Drag a model file onto this script to generate blockstates and models.
    pause
    exit /b
)

rem Extract the filename without the extension
set "filename=%~n1"

rem Define the directories
set "blockstates_dir=..\..\blockstates"
set "models_block_dir=..\..\models\block"
set "models_item_dir=..\..\models\item"

rem Create the directories if they don't exist
mkdir "%blockstates_dir%" 2>nul
mkdir "%models_block_dir%" 2>nul
mkdir "%models_item_dir%" 2>nul

rem Blockstate JSON content
(
echo {
echo.  "variants": {
echo.    "facing=north": {
echo.      "model": "jta:block/%color%_%filename%"
echo.    },
echo.    "facing=south": {
echo.      "model": "jta:block/%color%_%filename%",
echo.      "y": 180
echo.    },
echo.    "facing=east": {
echo.      "model": "jta:block/%color%_%filename%",
echo.      "y": 90
echo.    },
echo.    "facing=west": {
echo.      "model": "jta:block/%color%_%filename%",
echo.      "y": -90
echo.    }
echo.  }
echo }
) > "%blockstates_dir%\%color%_%filename%.json"

rem Block model JSON content
(
echo {
echo.  "parent": "jta:block/%filename%",
echo.  "textures": {
echo.    "color": "jta:block/%color%"
echo.  }
echo }
) > "%models_block_dir%\%color%_%filename%.json"

rem Item model JSON content
(
echo {
echo.  "parent": "jta:block/%filename%",
echo.  "textures": {
echo.    "color": "jta:block/%color%"
echo.  }
echo }
) > "%models_item_dir%\%color%_%filename%.json"

echo JSON files generated successfully.
pause
