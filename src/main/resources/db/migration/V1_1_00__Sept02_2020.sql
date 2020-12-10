DELIMITER $$

DROP PROCEDURE IF EXISTS AlterTableAddColumn 
$$
create procedure AlterTableAddColumn(
		IN tableName text,
		IN fieldName text,
		IN fieldDef text)
begin
	IF NOT EXISTS (
			SELECT * FROM information_schema.COLUMNS
			WHERE column_name=fieldName
			and table_name=tableName
			and table_schema=database()
		)
	THEN
		set @ddl=CONCAT('ALTER TABLE ',database(),'.',tableName,
			' ADD COLUMN ',fieldName,' ',fieldDef);
		prepare stmt from @ddl;
		execute stmt;
	END IF;
end;
$$


DROP PROCEDURE IF EXISTS AlterTableRemoveColumn 
$$
create procedure AlterTableRemoveColumn(
		IN tableName text,
		IN fieldName text)
begin
	IF EXISTS (
			SELECT * FROM information_schema.COLUMNS
			WHERE column_name=fieldName
			and table_name=tableName
			and table_schema=database()
		)
	THEN
		set @ddl=CONCAT('ALTER TABLE ',database(),'.',tableName,
			' DROP COLUMN ',fieldName);
		prepare stmt from @ddl;
		execute stmt;
	END IF;
end;
$$



DROP PROCEDURE IF EXISTS DropColumnIndex $$
CREATE PROCEDURE DropColumnIndex (IN  tableName varchar(64), IN colmnName varchar(64))
BEGIN

    DECLARE IndexColumnCount INT;
    DECLARE SQLStatement VARCHAR(256);

    SELECT COUNT(*) INTO IndexColumnCount
    FROM information_schema.statistics
    WHERE table_schema = database()
    AND table_name = tableName
    AND index_name = colmnName;

    IF IndexColumnCount > 0 THEN
        SET SQLStatement = CONCAT('ALTER TABLE `',database(),'`.`',tableName,'` DROP
INDEX `',colmnName,'`');
        SET @SQLStmt = SQLStatement;
        PREPARE s FROM @SQLStmt;
        EXECUTE s;
        DEALLOCATE PREPARE s;
    END IF;

end;
$$

DROP PROCEDURE IF EXISTS AddColumnIndex$$
CREATE PROCEDURE AddColumnIndex(IN  tableName varchar(64), IN colmnName varchar(64))
BEGIN

    SELECT @indexes := COUNT(1)
        FROM INFORMATION_SCHEMA.STATISTICS
        WHERE table_schema = database()
        AND table_name = tableName
        AND COLUMN_NAME = colmnName;

    IF @indexes = 0 THEN
        SET @sql_cmd := CONCAT('CREATE INDEX `',colmnName,'` ON `',database(),'`.`',tableName,'` ( `',colmnName,'`)');
        SELECT @sql_cmd;
        PREPARE stmt FROM @sql_cmd;
        EXECUTE stmt;
        DEALLOCATE PREPARE stmt;
    END IF;

end;
$$


DROP PROCEDURE IF EXISTS AddColumnIndexWithUNIQUE$$
CREATE PROCEDURE AddColumnIndexWithUNIQUE(IN  tableName varchar(64), IN colmnName varchar(64))
BEGIN

    SELECT @indexes := COUNT(1)
        FROM INFORMATION_SCHEMA.STATISTICS
        WHERE table_schema = database()
        AND table_name = tableName
        AND COLUMN_NAME = colmnName;

    IF @indexes = 0 THEN
        SET @sql_cmd := CONCAT('ALTER TABLE `',database(),'`.`',tableName,'` ADD UNIQUE INDEX `',colmnName,'_UNIQUE`',
            '( `', colmnName, '` ASC)');
        SELECT @sql_cmd;
        PREPARE stmt FROM @sql_cmd;
        EXECUTE stmt;
        DEALLOCATE PREPARE stmt;
    END IF;

end;
$$

DELIMITER ;




