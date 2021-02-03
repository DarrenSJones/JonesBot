# JonesBot
This is a Java-based Discord bot built using [JDA](https://github.com/DV8FromTheWorld/JDA).<br>
This is being built as a personal project and isn't being supported for outside use currently.

## Installation
### Prerequisites
Currently only Windows 10 is supported. The following must be installed before installing the bot:
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
<t>By default the databases are called `jonesbot` and `jonesbottest`, running on `localhost:1433`.<br>
<t>This can be changed in `src/main/resources/config/database.properties`.<br>
4. **Update Databases to the Current Version**<br>
<t>In the command prompt, `cd` to the directory containing the project.<br>
<t>Send `gradle database` to update the database to match the current bot version.<br>
5. **Run Tests to Validate Installation**<br>
<t>In the command prompt, `cd` to the directory containing the project.<br>
<t>Send `gradle clean test` to run the test suite, ensuring the bot installed correctly.<br>
6. **Run the Bot**<br>
<t>In the command prompt, `cd` to the directory containing the project.<br>
<t>Send `gradle run` to run the bot.<br>
<t>A console message will display when the bot has started successfully.
