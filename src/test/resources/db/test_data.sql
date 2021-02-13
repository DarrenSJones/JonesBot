-- Config
UPDATE bot_config SET item_value = '0.0.0'															WHERE item_key = 'DB_VERSION';
UPDATE bot_config SET item_value = '12345678901234567890123456789012345678901234567890123456789'	WHERE item_key = 'BOT_TOKEN';
UPDATE bot_config SET item_value = 'http://localhost:1080'											WHERE item_key = 'BOT_GITHUB_REPO';
UPDATE bot_config SET item_value = '123456789012345678'												WHERE item_key = 'BOT_OWNER_ID';
UPDATE bot_config SET item_value = '!'																WHERE item_key = 'BOT_PREFIX';
UPDATE bot_config SET item_value = 'http://localhost:1080'											WHERE item_key = 'CATFACT_HOST';
UPDATE bot_config SET item_value = 'http://localhost:1080'											WHERE item_key = 'SIMPSONS_HOST';
UPDATE bot_config SET item_value = 'http://localhost:1080'											WHERE item_key = 'FUTURAMA_HOST';
UPDATE bot_config SET item_value = 'http://localhost:1080'											WHERE item_key = 'RICK&MORTY_HOST';
UPDATE bot_config SET item_value = 'Regina,Saskatchewan,CA'											WHERE item_key = 'WEATHER_DEFAULT_CITY';
UPDATE bot_config SET item_value = 'http://localhost:1080'											WHERE item_key = 'WEATHER_HOST';
UPDATE bot_config SET item_value = '12345678901234567890123456789012'								WHERE item_key = 'WEATHER_TOKEN';

-- Reactions
INSERT INTO reaction VALUES (':lacrosse:',		N'🥍',			'lacrosse');
INSERT INTO reaction VALUES (':tophat:',		N'🎩',			'top hat');
INSERT INTO reaction VALUES (':sandwich:',		N'🥪',			'sandwich(es)?');
INSERT INTO reaction VALUES (':man_mage:',		N'🧙‍♂️',			'(mages?|wizards?)');
INSERT INTO reaction VALUES (':flag_ca:',		N'🇨🇦',			'canada');
INSERT INTO reaction VALUES (':tipsfedora:',	':tipsfedora:',	'm''?lady');

--Frinkiac Saved
INSERT INTO frinkiac_saved VALUES (1,	'Trash',			'S03E22',	'937738',	'trash');
INSERT INTO frinkiac_saved VALUES (1,	'Flag',				'S14E03',	'883966',	'flags?');
INSERT INTO frinkiac_saved VALUES (1,	'Pig',				'Movie',	'1321236',	'(spider )?pig');
INSERT INTO frinkiac_saved VALUES (1,	'Catholic Church',	'S10E12',	'927876',	'(catholic( church)?)|(we''?ve made a few changes)');
INSERT INTO frinkiac_saved VALUES (1,	'The Anvil',		'S08E15',	'856087',	'(the anvil)|(gay steel mill)|(we work hard\,? we play hard)');
INSERT INTO frinkiac_saved VALUES (2,	'Ass',				'S05E15',	'586936',	'ass');
INSERT INTO frinkiac_saved VALUES (2,	'Lincoln',			'S03E10',	'589838',	'lincoln|jonesbot');
INSERT INTO frinkiac_saved VALUES (3,	'TV',				'S01E08',	'62479',	'tv');

-- DayOfWeek
INSERT INTO simple_schedule VALUES (null,'MONDAY','023456789012345678','223456789012345678','1:00 PM','Monday!');
INSERT INTO simple_schedule VALUES (null,'TUESDAY','023456789012345678','223456789012345678','2:00 PM','Tuesday!');
INSERT INTO simple_schedule VALUES (null,'WEDNESDAY','023456789012345678','223456789012345678','3:00 PM','Wednesday!');
INSERT INTO simple_schedule VALUES (null,'THURSDAY','023456789012345678','223456789012345678','4:00 PM','Thursday!');
INSERT INTO simple_schedule VALUES (null,'FRIDAY','023456789012345678','223456789012345678','5:00 PM','Friday!');
INSERT INTO simple_schedule VALUES (null,'SATURDAY','023456789012345678','223456789012345678','6:00 PM','Saturday!');
INSERT INTO simple_schedule VALUES (null,'SUNDAY','023456789012345678','223456789012345678','7:00 PM','Sunday!');

-- Date
INSERT INTO simple_schedule VALUES ('2021-02-01',null,'023456789012345678','223456789012345678','1:30 PM','Monday Date!');
INSERT INTO simple_schedule VALUES ('2021-02-02',null,'023456789012345678','223456789012345678','2:30 PM','Tuesday Date!');
INSERT INTO simple_schedule VALUES ('2021-02-03',null,'023456789012345678','223456789012345678','3:30 PM','Wednesday Date!');
INSERT INTO simple_schedule VALUES ('2021-02-04',null,'023456789012345678','223456789012345678','4:30 PM','Thursday Date!');
INSERT INTO simple_schedule VALUES ('2021-02-05',null,'023456789012345678','223456789012345678','5:30 PM','Friday Date!');
INSERT INTO simple_schedule VALUES ('2021-02-06',null,'023456789012345678','223456789012345678','6:30 PM','Saturday Date!');
INSERT INTO simple_schedule VALUES ('2021-02-07',null,'023456789012345678','223456789012345678','7:30 PM','Sunday Date!');