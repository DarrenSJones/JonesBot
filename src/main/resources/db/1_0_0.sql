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
	('TOKEN_OPENWEATHERMAP',	'openweathermap-token-here'),
	('HOST_CATFACT',			'https://catfact.ninja');

DROP TABLE IF EXISTS "reaction";

CREATE TABLE "reaction" (
	"id"		INT IDENTITY(1, 1)	NOT NULL PRIMARY KEY,
	"shortcode"	VARCHAR(20)			NOT NULL,
	"unicode"	NVARCHAR(10)		NOT NULL,
	"regex"		VARCHAR(255)		NOT NULL);
