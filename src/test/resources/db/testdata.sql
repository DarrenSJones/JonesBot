USE jonesbottest

-- Config
UPDATE bot_config SET item_value = '1.0.0'															WHERE item_key = 'BOT_VERSION';
UPDATE bot_config SET item_value = '12345678901234567890123456789012345678901234567890123456789'	WHERE item_key = 'BOT_TOKEN';
UPDATE bot_config SET item_value = '123456789012345678'												WHERE item_key = 'BOT_OWNER_ID';
UPDATE bot_config SET item_value = '!'																WHERE item_key = 'BOT_PREFIX';
UPDATE bot_config SET item_value = 'http://localhost:1080'											WHERE item_key = 'CATFACT_HOST';
UPDATE bot_config SET item_value = 'Regina,Saskatchewan,CA'											WHERE item_key = 'WEATHER_DEFAULT_CITY';
UPDATE bot_config SET item_value = 'http://localhost:1080'											WHERE item_key = 'WEATHER_HOST';
UPDATE bot_config SET item_value = '12345678901234567890123456789012'								WHERE item_key = 'WEATHER_TOKEN';

-- Reactions
INSERT INTO reaction VALUES (':lacrosse:',	N'🥍',		'lacrosse');
INSERT INTO reaction VALUES (':tophat:',	N'🎩',		'top hat');
INSERT INTO reaction VALUES (':sandwich:',	N'🥪',		'sandwich(es)?');
INSERT INTO reaction VALUES (':man_mage:',	N'🧙‍♂️',		'(mages?|wizards?)');
INSERT INTO reaction VALUES (':flag_ca:',	N'🇨🇦',		'canada');
