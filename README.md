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

- [X] Interact with the server without Java knowledge
- [X] Flexible tasks and many triggers
- [X] PlaceholderAPI integration
- [X] Player triggers:
  - [X] Sneak, Sneak Release,Fall, Left/right click (air, entity, block), Hand Swap
- [X] Easy to create you own Action and even Trigger
- [X] Execute commands as player/console
- [X] Check permissions
- [X] Pause
- [X] Conditions
- [X] Scheduling
- [ ] Messaging
- [ ] Broadcast
- [ ] MultiVerse-core integration
- [ ] Lands integration
- [ ] Chunky integration

# Disclaimer

Initially, I needed a plugin that would control the actions of others plugins in order to perform wipes on the server.
In the process of thinking about the implementation, I realized that I could make primitive scripts out of this to
interact with players/items/worlds and the server as a whole. This can replace a lot of unnecessary plugins in your
build. The list of functionality is currently modest, but it will definitely be actively expanded; moreover, creating
your own triggers and actions is very simple. All you need to do is use TaskTypeRequestEvent and ActionRequestEvent
events, examples you can find in this repository. The concept of task types defines how the task will be activated, and
the concept of actions defines a list that needs to be walked through and completed everything, sequentially. Each
action is as short as possible to create, all you need to do is specify the entry point using '[ACTION_NAME]' and the
input data via ';'. If you combine this with PlaceholderAPI, you can do completely cool things. for example

```json
tasks {
  open-menu {
  type=
  "PLAYER_ACTION"
  <
  1.
  Work
  with
  player
  action
  's
  handle=
  "HAND_SWAP"
  <
  2.
  On
  player
  swap
  hand
  actions=[
  "[PERMISSIONS] example.menu.open;true"
  <
  3.
  Check
  player
  permission
  to
  do
  it
  "[CONDITION] %player_is_sneaking%;true"
  <
  4.
  If
  player
  sneaking
  "[CONSOLE_COMMAND] menu %player_name%"
  <
  5.
  Open
  menu
]
}
}
```


# How To Install Plugin

* Download plugin [link (will add later)]()
* Download dependencies [link (will add later)]()
* Put plugin and dependencies to your plugins folder
