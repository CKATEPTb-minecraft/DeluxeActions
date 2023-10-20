<p align="center">
<h3 align="center">ScheduledActions</h3>

------

<p align="center">
A simple and incredibly powerful plugin that allows you to do many things.
Create tasks and specify a trigger to start executing them.
</p>

<p align="center">
<img alt="License" src="https://img.shields.io/github/license/CKATEPTb-minecraft/DeluxeActions">
<a href="https://docs.gradle.org/7.5/release-notes.html"><img src="https://img.shields.io/badge/Gradle-7.5-brightgreen.svg?colorB=469C00&logo=gradle"></a>
<a href="https://discord.gg/P7FaqjcATp" target="_blank"><img alt="Discord" src="https://img.shields.io/discord/925686623222505482?label=discord"></a>
</p>

------

# Versioning

We use [Semantic Versioning 2.0.0](https://semver.org/spec/v2.0.0.html) to manage our releases.

# Features

- [ ] Plugin integrations:
    - [ ] PlaceholderAPI integration
        - [X] You can use the PlaceholderAPI to its full potential
        - [ ] Expand placeholders
            - [ ] Player target
                - [ ] Entity
                    - [ ] %player_target_entity_name%
                    - [ ] %player_target_entity_location%
                    - [ ] %player_target_entity_type%
                    - [ ] %player_target_entity_health%
                    - [ ] TODO... all from [player ecloud](https://api.extendedclip.com/expansions/player/)
                - [ ] Block
                    - [ ] %player_target_block_type%
                    - [ ] %player_target_block_location%
                - [X] Item ~~included in [player ecloud](https://api.extendedclip.com/expansions/player/)~~
    - [ ] MultiVerse-core integration
    - [ ] Lands integration
    - [ ] Chunky integration
- [ ] Triggers
    - [X] Player `type="PLAYER_ACTION"`:
        - [X] Sneak `handle="SNEAK"`
        - [X] Sneak Release `handle="SNEAK_RELEASE"`
        - [X] Fall  `handle="FALL"`
        - [X] Left click `handle="LEFT_CLICK"`
            - [X] Entity `handle="LEFT_CLICK_ENTITY"`
            - [X] Block `handle="LEFT_CLICK_BLOCK"`
        - [X] Right click `handle="RIGHT_CLICK"`
            - [X] Entity `handle="RIGHT_CLICK_ENTITY"`
            - [X] Block `handle="RIGHT_CLICK_BLOCK"`
        - [X] Hand Swap `handle="HAND_SWAP"`
        - [ ] ???
    - [ ] World
    - [ ] Item
    - [ ] Server
    - [X] Scheduled `type="SCHEDULED"`
        - [X] TimeUnit `handle="MINUTES;1"` ~~you can use any ChornoUnit~~
- [X] Events
    - [X] TaskTypeRequestEvent
        - [X] ~~Used to register your own task types~~
    - [X] ActionRequestEvent
        - [X] ~~Used to register your own task actions~~
- [X] Misc
    - [X] Commands
        - [X] Player `[COMMAND] examplecommand`
        - [X] Console `[CONSOLE_COMMAND] examplecomand %player_name%`
    - [ ] Messages ~~Can be implemented using commands~~
        - [ ] Directly `[MESSAGE] text`
        - [ ] Broadcast `[BROADCAST] text;world`
    - [X] Permissions `[PERMISSION] example.permission.access;true` ~~set false if you need not_has check~~
    - [ ] Conditions `[CONDITION] %player_is_sneaking%;true` ~~set false if you need not_has check~~
        - [X] PlaceholderAPI any registered boolean placeholder can be user as condition check
        - [ ] Comparing
        - [ ] External
    - [X] Delay `[DELAY] SECONDS;5"` ~~you can use any ChornoUnit~~`

# Disclaimer

Initially, I needed a plugin that would control the actions of others plugins in order to perform wipes on the server.
In the process of thinking about the implementation, I realized that I could make primitive scripts out of this to
interact with players/items/worlds and the server as a whole. This can replace a lot of unnecessary plugins in your
build. The list of functionality is currently modest, but it will definitely be actively expanded; moreover, creating
your own triggers and actions is very simple. All you need to do is use `TaskTypeRequestEvent` and `ActionRequestEvent`
events, examples you can find in this repository. The concept of task types defines how the task will be activated, and
the concept of actions defines a list that needs to be walked through and completed everything, sequentially. Each
action is as short as possible to create, all you need to do is specify the entry point using `[ACTION_NAME]` and the
input data via `;`. If you combine this with `PlaceholderAPI`, you can do completely cool things. for example

```hocon
tasks {
  open-menu {
    type="PLAYER_ACTION" < 1. Work with player action's
    handle="HAND_SWAP" < 2. On player swap hand
    actions=[
      "[PERMISSIONS] example.menu.open;true" < 3. Check player permission to do it
      "[CONDITION] %player_is_sneaking%;true" < 4. If player sneaking
      "[CONSOLE_COMMAND] menu %player_name%" < 5. Open menu
    ]
  }
}
```

# How To Install Plugin

* Download plugin [link (will add later)]()
* Download dependencies [link (will add later)]()
* Put plugin and dependencies to your plugins folder
