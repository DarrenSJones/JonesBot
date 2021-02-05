CREATE TABLE "simple_schedule" (
	"id"			INT IDENTITY(1, 1)	NOT NULL	PRIMARY KEY,
	"date"			DATE,
	"day_of_week"	INT,
	"guild_id"		CHAR(18)			NOT NULL,
	"channel_id"	CHAR(18)			NOT NULL,
	"time"			VARCHAR(32)			NOT NULL,
	"value"			VARCHAR(128)		NOT NULL);