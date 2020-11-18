USE jonesbot

DROP TABLE IF EXISTS "bot_config";

CREATE TABLE "bot_config" (
	"item_key" VARCHAR(20) NOT NULL,
	"item_value" VARCHAR(64)
);

INSERT INTO bot_config
	(item_key, item_value)
VALUES
	('BOT_TOKEN', NULL),
	('BOT_OWNER_ID', NULL),
	('BOT_PREFIX', NULL),
	('PATH_RESOURCES', NULL),
	('PATH_FRINKIAC', NULL),
	('PATH_SCHEDULE', NULL),
	('TOKEN_OPENWEATHERMAP', NULL)
;



