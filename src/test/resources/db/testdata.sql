USE jonesbottest

-- Config
UPDATE bot_config SET item_value = '12345678901234567890123456789012345678901234567890123456789'	WHERE item_key = 'BOT_TOKEN';
UPDATE bot_config SET item_value = '123456789012345678'												WHERE item_key = 'BOT_OWNER_ID';
UPDATE bot_config SET item_value = '!'																WHERE item_key = 'BOT_PREFIX';
UPDATE bot_config SET item_value = '12345678901234567890123456789012'								WHERE item_key = 'TOKEN_OPENWEATHERMAP';

-- Reactions
INSERT INTO reaction VALUES (':lacrosse:',	N'🥍',		'lacrosse');
INSERT INTO reaction VALUES (':tophat:',	N'🎩',		'top hat');
INSERT INTO reaction VALUES (':sandwich:',	N'🥪',		'sandwich(es)?');
INSERT INTO reaction VALUES (':man_mage:',	N'🧙‍♂️',		'(mages?|wizards?)');
INSERT INTO reaction VALUES (':flag_ca:',	N'🇨🇦',		'canada');

