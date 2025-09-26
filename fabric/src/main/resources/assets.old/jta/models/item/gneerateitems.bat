@echo off
setlocal enabledelayedexpansion

:: Define the array of block identifiers
set "blocks=rusty_accompanied_horses wall_accompanied_horses rusty_bend_to_left wall_bend_to_left rusty_bend_to_right wall_bend_to_right rusty_cattle wall_cattle rusty_crossroads wall_crossroads rusty_cycle_route_ahead wall_cycle_route_ahead rusty_double_bend_to_left wall_double_bend_to_left rusty_double_bend_to_right wall_double_bend_to_right rusty_dual_carriageway_ends wall_dual_carriageway_ends rusty_elderly wall_elderly rusty_falling_rocks wall_falling_rocks rusty_junction_on_bend_ahead_left wall_junction_on_bend_ahead_left rusty_junction_on_bend_ahead_right wall_junction_on_bend_ahead_right rusty_low_flying_aircraft wall_low_flying_aircraft rusty_opening_bridge_ahead wall_opening_bridge_ahead rusty_road_narrows_both wall_road_narrows_both rusty_road_narrows_left wall_road_narrows_left rusty_road_narrows_right wall_road_narrows_right rusty_roundabout wall_roundabout rusty_slippery_road wall_slippery_road rusty_staggered_junction wall_staggered_junction rusty_t_junction_with_priority_left wall_t_junction_with_priority_left rusty_t_junction_with_priority_right wall_t_junction_with_priority_right rusty_uneven_road wall_uneven_road rusty_wild_animals wall_wild_animals rusty_wild_horses wall_wild_horses rusty_zebra_crossing wall_zebra_crossing"

:: Loop through the array and generate JSON files
for %%i in (%blocks%) do (
    set "block=%%i"
    (
        echo {
        echo   "parent": "jta:block/!block!"
        echo }
    ) > !block!.json
)

endlocal
echo Blockstate JSON files generated successfully.
pause