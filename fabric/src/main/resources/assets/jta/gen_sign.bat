@echo off
setlocal

rem Directories
set "blockstates_dir=blockstates"
set "models_block_dir=models\block"
set "models_item_dir=models\item"

rem Filenames
set "filename_base=your_block"
set "filename_wall=wall_speed_45"

rem List of materials
set "materials=rusty black steel"

rem Angles for rotation
set "angles=-22.5 -45 22.5"

rem Function to write blockstate JSON
:write_blockstate_json
(
    echo {
    echo.  "multipart": [
    for %%d in (north east south west) do (
        for %%r in (false true) do (
            for %%a in (false true) do (
                if "%%r"=="false" if "%%a"=="false" (
                    echo.    { "when": { "facing": "%%d", "is_22_5": "%%r", "is_45": "%%a" }, "apply": { "model": "jta:block/%filename_base%" } },
                ) else if "%%r"=="true" if "%%a"=="false" (
                    echo.    { "when": { "facing": "%%d", "is_22_5": "%%r", "is_45": "%%a" }, "apply": { "model": "jta:block/%filename_base%_22_5", "y": %%y, "uvlock": true } },
                ) else if "%%r"=="false" if "%%a"=="true" (
                    echo.    { "when": { "facing": "%%d", "is_22_5": "%%r", "is_45": "%%a" }, "apply": { "model": "jta:block/%filename_base%_45", "y": %%y, "uvlock": true } },
                ) else (
                    echo.    { "when": { "facing": "%%d", "is_22_5": "%%r", "is_45": "%%a" }, "apply": { "model": "jta:block/%filename_base%_67_5", "y": %%y, "uvlock": true } },
                )
            )
        )
    )
    echo.  ]
    echo }
)

rem Function to write model JSON for block
:write_model_json
(
    echo {
    echo.  "credit": "Made with Blockbench",
    echo.  "texture_size": [128, 193],
    echo.  "textures": {
    echo.    "0": "jta:block/speed_45",
    echo.    "particle": "jta:block/speed_45"
    echo.  },
    echo.  "elements": [
    echo.    {
    echo.      "from": [0, 0, 0.3],
    echo.      "to": [16, 16, 0.3],
    echo.      "rotation": {
    echo.        "angle": 0,
    echo.        "axis": "y",
    echo.        "origin": [8, 8, 8]
    echo.      },
    echo.      "faces": {
    echo.        "north": { "uv": [0, 0, 16, 16], "texture": "#0" },
    echo.        "east": { "uv": [0, 0, 16, 16], "texture": "#0" },
    echo.        "south": { "uv": [0, 0, 16, 16], "texture": "#0" },
    echo.        "west": { "uv": [0, 0, 16, 16], "texture": "#0" },
    echo.        "up": { "uv": [0, 0, 16, 16], "texture": "#0" },
    echo.        "down": { "uv": [0, 0, 16, 16], "texture": "#0" }
    echo.      }
    echo.    }
    echo.  ],
    echo.  "display": {
    echo.    "thirdperson_righthand": { "rotation": [0, 180, 0] },
    echo.    "thirdperson_lefthand": { "rotation": [0, 180, 0] },
    echo.    "firstperson_righthand": { "rotation": [0, 180, 0], "translation": [0, 0, -2] },
    echo.    "firstperson_lefthand": { "rotation": [0, 180, 0] },
    echo.    "gui": { "translation": [1.25, -0.75, 0] },
    echo.    "head": { "rotation": [0, -180, 0], "translation": [-1, 14.25, -7.5] }
    echo.  }
    echo }
)

rem Function to write model JSON for wall
:write_wall_model_json
(
    echo {
    echo.  "credit": "Made with Blockbench",
    echo.  "texture_size": [128, 193],
    echo.  "textures": {
    echo.    "0": "jta:block/speed_45",
    echo.    "particle": "jta:block/speed_45"
    echo.  },
    echo.  "elements": [
    echo.    {
    echo.      "from": [0, 0, 0.3],
    echo.      "to": [16, 16, 0.3],
    echo.      "rotation": {
    echo.        "angle": 0,
    echo.        "axis": "y",
    echo.        "origin": [4.8, 3.4, -1.9]
    echo.      },
    echo.      "faces": {
    echo.        "north": { "uv": [1.125, 1.16062, 1.925, 1.95648], "texture": "#0" },
    echo.        "east": { "uv": [0, 0, 0, 0.79585], "texture": "#missing" },
    echo.        "south": { "uv": [0, 0, 16, 16], "texture": "#0" },
    echo.        "west": { "uv": [0, 0, 0, 0.79585], "texture": "#missing" },
    echo.        "up": { "uv": [0, 0, 0.8, 0], "texture": "#missing" },
    echo.        "down": { "uv": [0, 0, 0.8, 0], "texture": "#missing" }
    echo.      }
    echo.    }
    echo.  ],
    echo.  "display": {
    echo.    "thirdperson_righthand": { "rotation": [0, 180, 0] },
    echo.    "thirdperson_lefthand": { "rotation": [0, 180, 0] },
    echo.    "firstperson_righthand": { "rotation": [0, 180, 0], "translation": [0, 0, -2] },
    echo.    "firstperson_lefthand": { "rotation": [0, 180, 0] },
    echo.    "gui": { "translation": [1.25, -0.75, 0] },
    echo.    "head": { "rotation": [0, -180, 0], "translation": [-1, 14.25, -7.5] }
    echo.  }
    echo }
)

rem Function to write item model JSON for block
:write_item_model_json
(
    echo {
    echo.  "parent": "jta:block/%filename_base%"
    echo }
)

rem Function to write item model JSON for wall
:write_item_model_json_wall
(
    echo {
    echo.  "parent": "jta:block/%filename_wall%"
    echo }
)

rem Create directories if they don't exist
if not exist "%blockstates_dir%" mkdir "%blockstates_dir%"
if not exist "%models_block_dir%" mkdir "%models_block_dir%"
if not exist "%models_item_dir%" mkdir "%models_item_dir%"

rem Generate JSON files for each material and angle for block
for %%m in (%materials%) do (
    set "filename=%filename_base%_%%m"

    rem Write blockstate JSON for block
    call :write_blockstate_json > "%blockstates_dir%\%filename%_blockstate.json"

    rem Write model JSON for block
    call :write_model_json > "%models_block_dir%\%filename%.json"

    rem Write item model JSON for block
    call :write_item_model_json > "%models_item_dir%\%filename%.json"
)

rem Generate JSON files for wall
set "filename_wall=%filename_wall%"

rem Write model JSON for wall
call :write_wall_model_json > "%models_block_dir%\%filename_wall%.json"

rem Write item model JSON for wall
call :write_item_model_json_wall > "%models_item_dir%\%filename_wall%.json"

echo Generation complete!
pause
