# JonesBot
This is a Java-based Discord bot built using [JDA](https://github.com/DV8FromTheWorld/JDA).

## Installation
### Prerequisites
* [Java JDK 15](https://www.oracle.com/java/technologies/javase-jdk15-downloads.html)
* [Gradle](https://gradle.org/)
* [Microsoft SQL Server 2019](https://www.microsoft.com/en-us/sql-server/sql-server-2019)

1. Create Databases
	Create two new databases, one for the bot and the other for testing.
	Currently these are non-configurable and must be called `jonesbot` and `jonesbottest`.
	The host and port are also currently non-configurable and must run using `localhost:1433`.

2. Clone Project
	Get the latest release from [GitHub](https://github.com/DarrenSJones/JonesBot/releases).

3. Get Dependencies
	In the command prompt, `cd` to the directory containing the project.
	Send `gradle check` to download any missing dependencies.

4. Build Database Tables
	Currently the Databases must be populated manually, in the following order:
	a. Run `src/main/resources/db/1_0_0.sql`, followed by any patches in order.
	b. Repeat Step a, using the test database.
	c. Run `src/test/resources/db/testdata.sql` to add test data.
	d. Run a local file to add required data to the main database, or edit it directly.

5. Validate Tests
	In the command prompt, `cd` to the directory containing the project.
	Send `gradle clean test` to run the test suite.
	This ensures that the bot is built and configured correctly.

6. Run the Bot
	In the command prompt, `cd` to the directory containing the project.
	Send `gradle run`.
	The bot will return a console message when it has started successfully.
