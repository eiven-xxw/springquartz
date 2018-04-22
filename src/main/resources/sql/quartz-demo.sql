CREATE TABLE `schedule_job` (
	`job_group` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8_bin',
	`job_name` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8_bin',
	`class_name` VARCHAR(200) NULL DEFAULT NULL COLLATE 'utf8_bin',
	`method_name` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8_bin',
	`cron_expression` VARCHAR(200) NULL DEFAULT NULL COLLATE 'utf8_bin',
	`description` VARCHAR(200) NULL DEFAULT ' ' COLLATE 'utf8_bin',
	UNIQUE INDEX `Index 1` (`job_group`, `job_name`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB;
