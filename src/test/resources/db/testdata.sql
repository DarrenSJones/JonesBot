USE jonesbottest

-- Config
UPDATE bot_config SET item_value = '0.0.0'															WHERE item_key = 'BOT_VERSION';
UPDATE bot_config SET item_value = '12345678901234567890123456789012345678901234567890123456789'	WHERE item_key = 'BOT_TOKEN';
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
INSERT INTO reaction VALUES (':lacrosse:',	N'🥍',		'lacrosse');
INSERT INTO reaction VALUES (':tophat:',	N'🎩',		'top hat');
INSERT INTO reaction VALUES (':sandwich:',	N'🥪',		'sandwich(es)?');
INSERT INTO reaction VALUES (':man_mage:',	N'🧙‍♂️',		'(mages?|wizards?)');
INSERT INTO reaction VALUES (':flag_ca:',	N'🇨🇦',		'canada');

--Frinkiac Saved
INSERT INTO frinkiac_saved VALUES (1,	'Trash',			'S03E22',	'937738',	'trash');
INSERT INTO frinkiac_saved VALUES (1,	'Flag',				'S14E03',	'883966',	'flags?');
INSERT INTO frinkiac_saved VALUES (1,	'Pig',				'Movie',	'1321236',	'(spider )?pig');
INSERT INTO frinkiac_saved VALUES (1,	'Catholic Church',	'S10E12',	'927876',	'(catholic( church)?)|(we''?ve made a few changes)');
INSERT INTO frinkiac_saved VALUES (1,	'The Anvil',		'S08E15',	'856087',	'(the anvil)|(gay steel mill)|(we work hard\,? we play hard)');
INSERT INTO frinkiac_saved VALUES (2,	'Ass',				'S05E15',	'586936',	'ass');
INSERT INTO frinkiac_saved VALUES (2,	'Lincoln',			'S03E10',	'589838',	'lincoln|jonesbot');
INSERT INTO frinkiac_saved VALUES (3,	'TV',				'S01E08',	'62479',	'tv');