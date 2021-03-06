CREATE TABLE "bot_config" (
	"item_key"		VARCHAR(20) NOT NULL,
	"item_value"	VARCHAR(64) DEFAULT NULL);

INSERT INTO bot_config
	(item_key, item_value)
VALUES
	('DB_VERSION',				'0.0.0'),
	('BOT_TOKEN',				'discord-bot-token-here'),
	('BOT_GITHUB_REPO',			'github-repo-path-here'),
	('BOT_OWNER_ID',			'discord-bot-owner-id-here'),
	('BOT_PREFIX',				'!'),
	('CATFACT_HOST',			'https://catfact.ninja'),
	('SIMPSONS_HOST',			'https://frinkiac.com'),
	('FUTURAMA_HOST',			'https://morbotron.com'),
	('RICK&MORTY_HOST',			'https://masterofallscience.com'),
	('WEATHER_DEFAULT_CITY',	'Regina,Saskatchewan,CA'),
	('WEATHER_HOST',			'http://api.openweathermap.org'),
	('WEATHER_TOKEN',			'openweathermap-token-here');


CREATE TABLE "reaction" (
	"id"		INT IDENTITY(1, 1)	NOT NULL	PRIMARY KEY,
	"shortcode"	VARCHAR(20)			NOT NULL,
	"unicode"	NVARCHAR(64)		NOT NULL,
	"regex"		VARCHAR(255)		NOT NULL);


CREATE TABLE "frinkiac_saved" (
	"id"		INT IDENTITY(1, 1)	NOT NULL	PRIMARY KEY,
	"host_id"	INT					NOT NULL,
	"name"		VARCHAR(32)			NOT NULL,
	"key"		VARCHAR(6)			NOT NULL,
	"timestamp"	VARCHAR(10)			NOT NULL,
	"regex"		VARCHAR(255)		NOT NULL);