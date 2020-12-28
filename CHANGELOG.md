# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

## [1.1.0][1.1.0] - 2020-12-28 - Roll Command
### Added
- New Command: Roll.
- New Test Set: Roll.
- Added ReactionHandler for later use, currently logs reactions to trace.

### Fixed
- Expanded SQL column for reaction/unicode to support custom emoji.

## [1.0.2][1.0.2] - 2020-12-22 - Change Log and To Do changes
### Added
- Command to return change log links separate from the version list.
- To Do list to show, in general, what's being worked on.

### Changed
- Version now shows a list of all versions, the date they released, and a small summary.

### Fixed
- Naming and links in the change log.

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

[Unreleased]: https://github.com/DarrenSJones/JonesBot/compare/v1.1.0...dev
[1.1.0]: https://github.com/DarrenSJones/JonesBot/compare/v1.0.2...v1.1.0
[1.0.2]: https://github.com/DarrenSJones/JonesBot/compare/v1.0.1...v1.0.2
[1.0.1]: https://github.com/DarrenSJones/JonesBot/compare/v1.0.0...v1.0.1
[1.0.0]: https://github.com/DarrenSJones/JonesBot/releases/tag/v1.0.0