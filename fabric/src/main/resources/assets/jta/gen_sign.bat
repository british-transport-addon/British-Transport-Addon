@echo off
setlocal enabledelayedexpansion

rem Check if a file was dragged onto the script
if "%~1"=="" (
    echo Drag an image file onto this script to generate blockstates and models.
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
echo.      "model": "jta:block/rusty_%filename%"
echo.    },
echo.    "facing=south": {
echo.      "model": "jta:block/rusty_%filename%",
echo.      "y": 180
echo.    },
echo.    "facing=east": {
echo.      "model": "jta:block/rusty_%filename%",
echo.      "y": 90
echo.    },
echo.    "facing=west": {
echo.      "model": "jta:block/rusty_%filename%",
echo.      "y": -90
echo.    }
echo.  }
echo }
) > "%blockstates_dir%\rusty_%filename%.json"
rem Blockstate JSON content
(
echo {
echo.  "variants": {
echo.    "facing=north": {
echo.      "model": "jta:block/black_%filename%"
echo.    },
echo.    "facing=south": {
echo.      "model": "jta:block/black_%filename%",
echo.      "y": 180
echo.    },
echo.    "facing=east": {
echo.      "model": "jta:block/black_%filename%",
echo.      "y": 90
echo.    },
echo.    "facing=west": {
echo.      "model": "jta:block/black_%filename%",
echo.      "y": -90
echo.    }
echo.  }
echo }
) > "%blockstates_dir%\black_%filename%.json"
rem Blockstate JSON content
(
echo {
echo.  "variants": {
echo.    "facing=north": {
echo.      "model": "jta:block/steel_%filename%"
echo.    },
echo.    "facing=south": {
echo.      "model": "jta:block/steel_%filename%",
echo.      "y": 180
echo.    },
echo.    "facing=east": {
echo.      "model": "jta:block/steel_%filename%",
echo.      "y": 90
echo.    },
echo.    "facing=west": {
echo.      "model": "jta:block/steel_%filename%",
echo.      "y": -90
echo.    }
echo.  }
echo }
) > "%blockstates_dir%\steel_%filename%.json"

rem Blockstate JSON content
(
echo {
echo.  "variants": {
echo.    "facing=north": {
echo.      "model": "jta:block/wall_%filename%"
echo.    },
echo.    "facing=south": {
echo.      "model": "jta:block/wall_%filename%",
echo.      "y": 180
echo.    },
echo.    "facing=east": {
echo.      "model": "jta:block/wall_%filename%",
echo.      "y": 90
echo.    },
echo.    "facing=west": {
echo.      "model": "jta:block/wall_%filename%",
echo.      "y": -90
echo.    }
echo.  }
echo }
) > "%blockstates_dir%\wall_%filename%.json"

rem Rusty model JSON content
(
echo {
echo.  "credit": "Made with Blockbench",
echo.  "texture_size": [
echo.    128,
echo.    128
echo.  ],
echo.  "textures": {
echo.    "1": "jta:block/rusty_pole",
echo.    "3": "jta:block/%filename%"
echo.  },
echo.  "elements": [
echo.    {
echo.      "from": [
echo.        7.5,
echo.        0,
echo.        7.5
echo.      ],
echo.      "to": [
echo.        8.5,
echo.        16,
echo.        8.5
echo.      ],
echo.      "rotation": {
echo.        "angle": 0,
echo.        "axis": "y",
echo.        "origin": [
echo.          7,
echo.          0,
echo.          5
echo.        ]
echo.      },
echo.      "faces": {
echo.        "north": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            0.5,
echo.            16
echo.          ],
echo.          "texture": "#1"
echo.        },
echo.        "east": {
echo.          "uv": [
echo.            7.5,
echo.            0,
echo.            8,
echo.            16
echo.          ],
echo.          "texture": "#1"
echo.        },
echo.        "south": {
echo.          "uv": [
echo.            0.5,
echo.            0,
echo.            1,
echo.            16
echo.          ],
echo.          "texture": "#1"
echo.        },
echo.        "west": {
echo.          "uv": [
echo.            7,
echo.            0,
echo.            7.5,
echo.            16
echo.          ],
echo.          "texture": "#1"
echo.        },
echo.        "up": {
echo.          "uv": [
echo.            1.0625,
echo.            0,
echo.            1.5625,
echo.            1
echo.          ],
echo.          "texture": "#1"
echo.        },
echo.        "down": {
echo.          "uv": [
echo.            6.4375,
echo.            0,
echo.            6.9375,
echo.            1
echo.          ],
echo.          "texture": "#1"
echo.        }
echo.      }
echo.    },
echo.    {
echo.      "from": [
echo.        0,
echo.        0,
echo.        7.4
echo.      ],
echo.      "to": [
echo.        16,
echo.        16,
echo.        7.4
echo.      ],
echo.      "rotation": {
echo.        "angle": 0,
echo.        "axis": "y",
echo.        "origin": [
echo.          6.5,
echo.          10,
echo.          7.4
echo.        ]
echo.      },
echo.      "faces": {
echo.        "north": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            16,
echo.            16
echo.          ],
echo.          "texture": "#3"
echo.        },
echo.        "east": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            0,
echo.            0.75
echo.          ],
echo.          "texture": "#3"
echo.        },
echo.        "south": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            16,
echo.            15.9619
echo.          ],
echo.          "texture": "#3"
echo.        },
echo.        "west": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            0,
echo.            0.75
echo.          ],
echo.          "texture": "#3"
echo.        },
echo.        "up": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            0.875,
echo.            0
echo.          ],
echo.          "texture": "#3"
echo.        },
echo.        "down": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            0.875,
echo.            0
echo.          ],
echo.          "texture": "#3"
echo.        }
echo.      }
echo.    }
echo.  ],
echo.  "display": {
echo.    "thirdperson_righthand": {
echo.      "rotation": [
echo.        0,
echo.        180,
echo.        0
echo.      ]
echo.    },
echo.    "thirdperson_lefthand": {
echo.      "rotation": [
echo.        0,
echo.        180,
echo.        0
echo.      ]
echo.    },
echo.    "firstperson_righthand": {
echo.      "rotation": [
echo.        0,
echo.        180,
echo.        0
echo.      ],
echo.      "translation": [
echo.        0,
echo.        0,
echo.        -2
echo.      ]
echo.    },
echo.    "firstperson_lefthand": {
echo.      "rotation": [
echo.        0,
echo.        180,
echo.        0
echo.      ]
echo.    },
echo.    "gui": {
echo.      "rotation": [
echo.        0,
echo.        180,
echo.        0
echo.      ]
echo.    },
echo.    "head": {
echo.      "translation": [
echo.        0,
echo.        10,
echo.        0
echo.      ]
echo.    }
echo.  }
echo }
) > "%models_block_dir%\rusty_%filename%.json"

rem Black model JSON content
(
echo {
echo.  "credit": "Made with Blockbench",
echo.  "texture_size": [
echo.    128,
echo.    128
echo.  ],
echo.  "textures": {
echo.    "1": "jta:block/black_pole",
echo.    "3": "jta:block/%filename%"
echo.  },
echo.  "elements": [
echo.    {
echo.      "from": [
echo.        7.5,
echo.        0,
echo.        7.5
echo.      ],
echo.      "to": [
echo.        8.5,
echo.        16,
echo.        8.5
echo.      ],
echo.      "rotation": {
echo.        "angle": 0,
echo.        "axis": "y",
echo.        "origin": [
echo.          7,
echo.          0,
echo.          5
echo.        ]
echo.      },
echo.      "faces": {
echo.        "north": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            0.5,
echo.            16
echo.          ],
echo.          "texture": "#1"
echo.        },
echo.        "east": {
echo.          "uv": [
echo.            7.5,
echo.            0,
echo.            8,
echo.            16
echo.          ],
echo.          "texture": "#1"
echo.        },
echo.        "south": {
echo.          "uv": [
echo.            0.5,
echo.            0,
echo.            1,
echo.            16
echo.          ],
echo.          "texture": "#1"
echo.        },
echo.        "west": {
echo.          "uv": [
echo.            7,
echo.            0,
echo.            7.5,
echo.            16
echo.          ],
echo.          "texture": "#1"
echo.        },
echo.        "up": {
echo.          "uv": [
echo.            1.0625,
echo.            0,
echo.            1.5625,
echo.            1
echo.          ],
echo.          "texture": "#1"
echo.        },
echo.        "down": {
echo.          "uv": [
echo.            6.4375,
echo.            0,
echo.            6.9375,
echo.            1
echo.          ],
echo.          "texture": "#1"
echo.        }
echo.      }
echo.    },
echo.    {
echo.      "from": [
echo.        0,
echo.        0,
echo.        7.4
echo.      ],
echo.      "to": [
echo.        16,
echo.        16,
echo.        7.4
echo.      ],
echo.      "rotation": {
echo.        "angle": 0,
echo.        "axis": "y",
echo.        "origin": [
echo.          6.5,
echo.          10,
echo.          7.4
echo.        ]
echo.      },
echo.      "faces": {
echo.        "north": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            16,
echo.            16
echo.          ],
echo.          "texture": "#3"
echo.        },
echo.        "east": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            0,
echo.            0.75
echo.          ],
echo.          "texture": "#3"
echo.        },
echo.        "south": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            16,
echo.            15.9619
echo.          ],
echo.          "texture": "#3"
echo.        },
echo.        "west": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            0,
echo.            0.75
echo.          ],
echo.          "texture": "#3"
echo.        },
echo.        "up": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            0.875,
echo.            0
echo.          ],
echo.          "texture": "#3"
echo.        },
echo.        "down": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            0.875,
echo.            0
echo.          ],
echo.          "texture": "#3"
echo.        }
echo.      }
echo.    }
echo.  ],
echo.  "display": {
echo.    "thirdperson_righthand": {
echo.      "rotation": [
echo.        0,
echo.        180,
echo.        0
echo.      ]
echo.    },
echo.    "thirdperson_lefthand": {
echo.      "rotation": [
echo.        0,
echo.        180,
echo.        0
echo.      ]
echo.    },
echo.    "firstperson_righthand": {
echo.      "rotation": [
echo.        0,
echo.        180,
echo.        0
echo.      ],
echo.      "translation": [
echo.        0,
echo.        0,
echo.        -2
echo.      ]
echo.    },
echo.    "firstperson_lefthand": {
echo.      "rotation": [
echo.        0,
echo.        180,
echo.        0
echo.      ]
echo.    },
echo.    "gui": {
echo.      "rotation": [
echo.        0,
echo.        180,
echo.        0
echo.      ]
echo.    },
echo.    "head": {
echo.      "translation": [
echo.        0,
echo.        10,
echo.        0
echo.      ]
echo.    }
echo.  }
echo }
) > "%models_block_dir%\black_%filename%.json"

rem Steel model JSON content
(
echo {
echo.  "credit": "Made with Blockbench",
echo.  "texture_size": [
echo.    128,
echo.    128
echo.  ],
echo.  "textures": {
echo.    "1": "jta:block/steel_pole",
echo.    "3": "jta:block/%filename%"
echo.  },
echo.  "elements": [
echo.    {
echo.      "from": [
echo.        7.5,
echo.        0,
echo.        7.5
echo.      ],
echo.      "to": [
echo.        8.5,
echo.        16,
echo.        8.5
echo.      ],
echo.      "rotation": {
echo.        "angle": 0,
echo.        "axis": "y",
echo.        "origin": [
echo.          7,
echo.          0,
echo.          5
echo.        ]
echo.      },
echo.      "faces": {
echo.        "north": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            0.5,
echo.            16
echo.          ],
echo.          "texture": "#1"
echo.        },
echo.        "east": {
echo.          "uv": [
echo.            7.5,
echo.            0,
echo.            8,
echo.            16
echo.          ],
echo.          "texture": "#1"
echo.        },
echo.        "south": {
echo.          "uv": [
echo.            0.5,
echo.            0,
echo.            1,
echo.            16
echo.          ],
echo.          "texture": "#1"
echo.        },
echo.        "west": {
echo.          "uv": [
echo.            7,
echo.            0,
echo.            7.5,
echo.            16
echo.          ],
echo.          "texture": "#1"
echo.        },
echo.        "up": {
echo.          "uv": [
echo.            1.0625,
echo.            0,
echo.            1.5625,
echo.            1
echo.          ],
echo.          "texture": "#1"
echo.        },
echo.        "down": {
echo.          "uv": [
echo.            6.4375,
echo.            0,
echo.            6.9375,
echo.            1
echo.          ],
echo.          "texture": "#1"
echo.        }
echo.      }
echo.    },
echo.    {
echo.      "from": [
echo.        0,
echo.        0,
echo.        7.4
echo.      ],
echo.      "to": [
echo.        16,
echo.        16,
echo.        7.4
echo.      ],
echo.      "rotation": {
echo.        "angle": 0,
echo.        "axis": "y",
echo.        "origin": [
echo.          6.5,
echo.          10,
echo.          7.4
echo.        ]
echo.      },
echo.      "faces": {
echo.        "north": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            16,
echo.            16
echo.          ],
echo.          "texture": "#3"
echo.        },
echo.        "east": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            0,
echo.            0.75
echo.          ],
echo.          "texture": "#3"
echo.        },
echo.        "south": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            16,
echo.            15.9619
echo.          ],
echo.          "texture": "#3"
echo.        },
echo.        "west": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            0,
echo.            0.75
echo.          ],
echo.          "texture": "#3"
echo.        },
echo.        "up": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            0.875,
echo.            0
echo.          ],
echo.          "texture": "#3"
echo.        },
echo.        "down": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            0.875,
echo.            0
echo.          ],
echo.          "texture": "#3"
echo.        }
echo.      }
echo.    }
echo.  ],
echo.  "display": {
echo.    "thirdperson_righthand": {
echo.      "rotation": [
echo.        0,
echo.        180,
echo.        0
echo.      ]
echo.    },
echo.    "thirdperson_lefthand": {
echo.      "rotation": [
echo.        0,
echo.        180,
echo.        0
echo.      ]
echo.    },
echo.    "firstperson_righthand": {
echo.      "rotation": [
echo.        0,
echo.        180,
echo.        0
echo.      ],
echo.      "translation": [
echo.        0,
echo.        0,
echo.        -2
echo.      ]
echo.    },
echo.    "firstperson_lefthand": {
echo.      "rotation": [
echo.        0,
echo.        180,
echo.        0
echo.      ]
echo.    },
echo.    "gui": {
echo.      "rotation": [
echo.        0,
echo.        180,
echo.        0
echo.      ]
echo.    },
echo.    "head": {
echo.      "translation": [
echo.        0,
echo.        10,
echo.        0
echo.      ]
echo.    }
echo.  }
echo }
) > "%models_block_dir%\steel_%filename%.json"

rem Wall model JSON content
(
echo {
echo.  "credit": "Made with Blockbench",
echo.  "texture_size": [
echo.    128,
echo.    193
echo.  ],
echo.  "textures": {
echo.    "0": "jta:block/%filename%",
echo.    "particle": "jta:block/%filename%"
echo.  },
echo.  "elements": [
echo.    {
echo.      "from": [
echo.        0,
echo.        0,
echo.        0.3
echo.      ],
echo.      "to": [
echo.        16,
echo.        16,
echo.        0.3
echo.      ],
echo.      "rotation": {
echo.        "angle": 0,
echo.        "axis": "y",
echo.        "origin": [
echo.          4.8,
echo.          3.4,
echo.          -1.9
echo.        ]
echo.      },
echo.      "faces": {
echo.        "north": {
echo.          "uv": [
echo.            1.125,
echo.            1.16062,
echo.            1.925,
echo.            1.95648
echo.          ],
echo.          "texture": "#0"
echo.        },
echo.        "east": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            0,
echo.            0.79585
echo.          ],
echo.          "texture": "#missing"
echo.        },
echo.        "south": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            16,
echo.            16
echo.          ],
echo.          "texture": "#0"
echo.        },
echo.        "west": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            0,
echo.            0.79585
echo.          ],
echo.          "texture": "#missing"
echo.        },
echo.        "up": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            0.8,
echo.            0
echo.          ],
echo.          "texture": "#missing"
echo.        },
echo.        "down": {
echo.          "uv": [
echo.            0,
echo.            0,
echo.            0.8,
echo.            0
echo.          ],
echo.          "texture": "#missing"
echo.        }
echo.      }
echo.    }
echo.  ],
echo.  "display": {
echo.    "thirdperson_righthand": {
echo.      "rotation": [
echo.        0,
echo.        180,
echo.        0
echo.      ]
echo.    },
echo.    "thirdperson_lefthand": {
echo.      "rotation": [
echo.        0,
echo.        180,
echo.        0
echo.      ]
echo.    },
echo.    "firstperson_righthand": {
echo.      "rotation": [
echo.        0,
echo.        180,
echo.        0
echo.      ],
echo.      "translation": [
echo.        0,
echo.        0,
echo.        -2
echo.      ]
echo.    },
echo.    "firstperson_lefthand": {
echo.      "rotation": [
echo.        0,
echo.        180,
echo.        0
echo.      ]
echo.    },
echo.    "gui": {
echo.      "translation": [
echo.        1.25,
echo.        -0.75,
echo.        0
echo.      ]
echo.    },
echo.    "head": {
echo.      "rotation": [
echo.        0,
echo.        -180,
echo.        0
echo.      ],
echo.      "translation": [
echo.        -1,
echo.        14.25,
echo.        -7.5
echo.      ]
echo.    }
echo.  }
echo }
) > "%models_block_dir%\wall_%filename%.json"

rem Item model JSON content
(
echo {
echo.  "parent": "jta:block/rusty_%filename%"
echo }
) > "%models_item_dir%\rusty_%filename%.json"

rem Item model JSON content
(
echo {
echo.  "parent": "jta:block/black_%filename%"
echo }
) > "%models_item_dir%\black_%filename%.json"

rem Item model JSON content
(
echo {
echo.  "parent": "jta:block/steel_%filename%"
echo }
) > "%models_item_dir%\steel_%filename%.json"

(
echo {
echo.  "parent": "jta:block/wall_%filename%"
echo }
) > "%models_item_dir%\wall_%filename%.json"

echo JSON files generated successfully.
pause
