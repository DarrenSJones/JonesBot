# JonesBot
This is a Java-based Discord bot built using [JDA](https://github.com/DV8FromTheWorld/JDA).<br>
This is being built as a personal project and isn't being supported for outside use currently.

## Installation
### Prerequisites
Currently only Windows is supported. The following must be installed before installing the bot:
* [Gradle](https://gradle.org/)
* [Java JDK 15](https://www.oracle.com/java/technologies/javase-jdk15-downloads.html)
* [Microsoft SQL Server 2019](https://www.microsoft.com/en-us/sql-server/sql-server-2019)

### Steps to Install
1. **Clone Project**<br>
<t>Get the latest release from [GitHub](https://github.com/DarrenSJones/JonesBot/releases).<br>
2. **Get Dependencies**<br>
<t>In the command prompt, `cd` to the directory containing the project.<br>
<t>Send `gradle check` to download any missing dependencies.<br>
3. **Create Databases**<br>
<t>Create two new databases in MSSQL. One is used in production and the other used for testing.<br>
<t>Currently these are non-configurable and must be called `jonesbot` and `jonesbottest`, with that casing.<br>
<t>The host and port are also currently non-configurable, and must run using `localhost:1433`.<br>
4. **Build Database Tables**<br>
<t>Currently the Databases must be populated manually, in the following order:<br>
<t>a. Run `src/main/resources/db/1_0_0.sql`, followed by any patches in order.<br>
<t>b. Repeat Step a, using the test database.<br>
<t>c. Run `src/test/resources/db/testdata.sql` to add test data.<br>
<t>d. Run a local file to add required data to the main database, or edit it directly.<br>
<t>In the future, this process will be automated.
5. **Run Tests to Validate Installation**<br>
<t>In the command prompt, `cd` to the directory containing the project.<br>
<t>Send `gradle clean test` to run the test suite, ensuring the bot installed correctly.<br>
6. **Run the Bot**<br>
<t>In the command prompt, `cd` to the directory containing the project.<br>
<t>Send `gradle run` to run the bot.<br>
<t>A console message will display when the bot has started successfully.
