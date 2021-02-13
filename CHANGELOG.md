# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

## [Unreleased][Unreleased] - Simple Schedule
### Added
- Simple Schedule, taken from the old Java prototype, updated with database support.
- Cron can now initiate the schedule directly from the Bot.

### Changed
- Renamed SQL files to match versions and keep alphabetical order.

## [1.1.4][1.1.4] - 2021-02-02 - Database Updater
### Added
- DbUpdater, a new process that handles all database creation and updates.
- Gradle JavaExec can now run DbUpdater from the command line before the bot is ran.

### Changed
- ReadMe installation was changed for DbUpdater to remove manual steps.

### Fixed
- Can now determine if the Database has been updated to the same version as the bot.
- Database and Mock Server information is now stored in the database properties config file.
- Fatal errors now log exceptions in a standard way.

## [1.1.3][1.1.3] - 2021-01-19 - Config Cleanup
### Changed
- Moved Config around to remove some values from the DB.
- Console and debug logging cleaned up to be more descriptive.

### Fixed
- Reorganized Roll command and tests to fix some minor issues, including range.
- Changelog command now links to the Dev changelog to show unreleased changes.

## [1.1.2][1.1.2] - 2021-01-13 - Internal Logging Cleanup
### Added
- Foundations for future Games, including the Handler and Models.

### Changed
- ReadMe formatting to be more readable in GitHub.
- Console and debug logging cleaned up to be more descriptive.

### Fixed
- Standardized how commands are found in the command list.
- Standardized how Users and Messages are found via JDA.

## [1.1.1][1.1.1] - 2020-12-29 - AutoResponse Reorganization
### Added
- DiscordUtils to manage common JDA-related tasks, including getCustomEmoji and getUser.

### Changed
- AutoResponse Reaction list moved into the Data Handler with other SQL lists, and renamed.
- AutoResponse Reaction Command list has been cleaned up.
- Cleaned up Reaction listeners and ReactionHandler.

### Fixed
- Tests no longer fail when "Unreleased" isn't in the change log.
- AutoResponse Reactions won't post a custom reaction if doesn't exist in the current guild.

## [1.1.0][1.1.0] - 2020-12-28 - Added Roll Command
### Added
- New Command: Roll.
- New Test Set: Roll.
- Added ReactionHandler for later use, currently logs reactions to trace.

### Changed
- Expanded SQL column for reaction/unicode to support custom emoji.

## [1.0.2][1.0.2] - 2020-12-22 - Change Log and To Do changes
### Added
- Command to return change log links separate from the version list.
- To Do list to show, in general, what's being worked on.

### Changed
- Version now shows a list of all versions, the date they released, and a small summary.

### Fixed
- Naming and links in the change log are now correct.

## [1.0.1][1.0.1] - 2020-12-19 - General Cleanup
### Added
- This change log to keep track of changes by version.
- ReadMe is no longer just a blank file.

### Changed
- Commands are now added to the bot automatically as they're added to the commands package.
- On DB setup, default version is now "0.0.0".
- Simplified the main method, mock server/responses, and JDBC connection creation.
- Renamed private methods that were misleading or wordy.

### Removed
- DB Table "version" and associated tests.

## [1.0.0][1.0.0] - 2020-12-14 - Initial Release
### Added
- The Bot as a whole, carried over from the old Java Prototype, excluding the schedule.
- MSSQL database to store saved data, along with controllers and models to use it.
- Commands: CatFact, Cowbell, Futurama, Help, Owner, Ping, Reaction, Reload, Rick&Morty, Simpsons, Version, and Weather.
- AutoResponse for Reactions.
- Custom Reporter for Console and Trace logging.

[Unreleased]: https://github.com/DarrenSJones/JonesBot/compare/v1.1.3...dev
[1.1.4]: https://github.com/DarrenSJones/JonesBot/compare/v1.1.3...v1.1.4
[1.1.3]: https://github.com/DarrenSJones/JonesBot/compare/v1.1.2...v1.1.3
[1.1.2]: https://github.com/DarrenSJones/JonesBot/compare/v1.1.1...v1.1.2
[1.1.1]: https://github.com/DarrenSJones/JonesBot/compare/v1.1.0...v1.1.1
[1.1.0]: https://github.com/DarrenSJones/JonesBot/compare/v1.0.2...v1.1.0
[1.0.2]: https://github.com/DarrenSJones/JonesBot/compare/v1.0.1...v1.0.2
[1.0.1]: https://github.com/DarrenSJones/JonesBot/compare/v1.0.0...v1.0.1
[1.0.0]: https://github.com/DarrenSJones/JonesBot/releases/tag/v1.0.0