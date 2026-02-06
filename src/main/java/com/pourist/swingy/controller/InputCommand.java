package com.pourist.swingy.controller;

public enum InputCommand {

    // movement
    MOVE_NORTH,
    MOVE_SOUTH,
    MOVE_EAST,
    MOVE_WEST,

    // encounter decisions
    FIGHT,
    RUN,

    // artifact decision
    TAKE_ARTIFACT,
    SKIP_ARTIFACT,

    // UI navigation
    SWITCH_TO_GUI,
    SWITCH_TO_CONSOLE,

    INVALID
}
