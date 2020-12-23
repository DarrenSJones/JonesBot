# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

## [Unreleased][Unreleased]

## [1.0.1][1.0.1]
### Added
- This change log to keep track of changes by version.
- ReadMe is no longer just a blank file.

### Changed
- Commands are now added to the bot automatically as they're added to the commands package.
- On DB setup, default version is now "0.0.0".
- Simplified the main method, mock server/responses, and JDBC connection creation.
- Renamed private methods that were misleading or wordy

### Removed
- DB Table "version" and associated tests.

## [1.0.0][1.0.0] - 2020-12-14
### Added
- The Bot as a whole, carried over from the old Java Prototype, excluding the schedule.
- MSSQL database to store saved data, along with controllers and models to use it.
- Commands: CatFact, Cowbell, Futurama, Help, Owner, Ping, Reaction, Reload, Rick&Morty, Simpsons, Version, and Weather.
- AutoResponse for Reactions.
- Custom Reporter for Console and Trace logging.

[Unreleased]: https://github.com/DarrenSJones/JonesBot/compare/tag/v1.0.0...main
[1.0.0]: https://github.com/DarrenSJones/JonesBot/releases/tag/v1.0.0