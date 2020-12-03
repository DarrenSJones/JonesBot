USE jonesbot

DROP TABLE IF EXISTS "bot_config";

CREATE TABLE "bot_config" (
	"item_key"		VARCHAR(20) NOT NULL,
	"item_value"	VARCHAR(64) DEFAULT NULL);

INSERT INTO bot_config
	(item_key, item_value)
VALUES
	('BOT_VERSION',				'1.0.0'),
	('BOT_TOKEN',				'discord-bot-token-here'),
	('BOT_OWNER_ID',			'discord-bot-owner-id-here'),
	('BOT_PREFIX',				'!'),
	('CATFACT_HOST',			'https://catfact.ninja'),
	('WEATHER_DEFAULT_CITY',	'Regina,Saskatchewan,CA'),
	('WEATHER_HOST',			'http://api.openweathermap.org'),
	('WEATHER_TOKEN',			'openweathermap-token-here');


DROP TABLE IF EXISTS "reaction";

CREATE TABLE "reaction" (
	"id"		INT IDENTITY(1, 1)	NOT NULL	PRIMARY KEY,
	"shortcode"	VARCHAR(20)			NOT NULL,
	"unicode"	NVARCHAR(10)		NOT NULL,
	"regex"		VARCHAR(255)		NOT NULL);


DROP TABLE IF EXISTS "frinkiac_host";

CREATE TABLE "frinkiac_host" (
	"id"	INT IDENTITY(1, 1)	NOT NULL	PRIMARY KEY,
	"host"	VARCHAR(32)			NOT NULL,
	"color"	VARCHAR(10)			NOT NULL);

INSERT INTO frinkiac_host
	(host, color)
VALUES
	('https://frinkiac.com/',			'#FED90F'),
	('https://morbotron.com/',			'#70E3A2'),
	('https://masterofallscience.com/',	'#CFDBDB');


DROP TABLE IF EXISTS "frinkiac_saved";

CREATE TABLE "frinkiac_saved" (
	"id"		INT IDENTITY(1, 1)	NOT NULL	PRIMARY KEY,
	"host_id"	INT					NOT NULL,
	"label"		VARCHAR(32)			NOT NULL,
	"key"		VARCHAR(6)			NOT NULL,
	"timestamp"	VARCHAR(10)			NOT NULL,
	"regex"		VARCHAR(255)		NOT NULL);

