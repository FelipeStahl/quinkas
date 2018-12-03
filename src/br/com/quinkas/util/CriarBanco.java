/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe-Sistema
 */
public class CriarBanco {

    public static List<String> sqlBanco() {
        List<String> sqls = new ArrayList();
        sqls.add("SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;");
        sqls.add("SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;");
        sqls.add("SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';");

        sqls.add("CREATE SCHEMA IF NOT EXISTS `quinkasbd` DEFAULT CHARACTER SET utf8 ;");

        sqls.add("CREATE TABLE IF NOT EXISTS `quinkasbd`.`professor` ("
                + "`id` INT(11) NOT NULL AUTO_INCREMENT,"
                + "`senha` VARCHAR(16) NOT NULL,"
                + "`nome` VARCHAR(80) NOT NULL,"
                + "`email` VARCHAR(80) NOT NULL,"
                + "PRIMARY KEY (`id`)) "
                + "ENGINE = InnoDB "
                + "AUTO_INCREMENT = 4 "
                + "DEFAULT CHARACTER SET = utf8;");

        sqls.add("CREATE TABLE IF NOT EXISTS `quinkasbd`.`questionario` ("
                + "`id` INT(11) NOT NULL AUTO_INCREMENT,"
                + "`nome` VARCHAR(45) NOT NULL,"
                + "`datacriacao` DATE NOT NULL,"
                + "`professor_id` INT(11) NOT NULL,"
                + "PRIMARY KEY (`id`),"
                + "INDEX `fk_questionario_professor_idx` (`professor_id` ASC) VISIBLE,"
                + "CONSTRAINT `fk_questionario_professor`"
                + " FOREIGN KEY (`professor_id`)"
                + " REFERENCES `quinkasbd`.`professor` (`id`)"
                + " ON DELETE NO ACTION"
                + " ON UPDATE NO ACTION)"
                + " ENGINE = InnoDB"
                + " AUTO_INCREMENT = 5"
                + " DEFAULT CHARACTER SET = utf8;");

        sqls.add("CREATE TABLE IF NOT EXISTS `quinkasbd`.`pergunta` ("
                + " `id` INT(11) NOT NULL AUTO_INCREMENT,"
                + "`pergunta` VARCHAR(250) NOT NULL,"
                + " `questionario_id` INT(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " INDEX `fk_pergunta_questionario1_idx` (`questionario_id` ASC) VISIBLE,"
                + " CONSTRAINT `fk_pergunta_questionario1`"
                + "    FOREIGN KEY (`questionario_id`)"
                + "   REFERENCES `quinkasbd`.`questionario` (`id`)"
                + "   ON DELETE NO ACTION"
                + "   ON UPDATE NO ACTION)"
                + " ENGINE = InnoDB"
                + " AUTO_INCREMENT = 4"
                + " DEFAULT CHARACTER SET = utf8;");

        sqls.add("CREATE TABLE IF NOT EXISTS `quinkasbd`.`alternativa` ("
                + "`id` INT(11) NOT NULL AUTO_INCREMENT,"
                + "`resposta` VARCHAR(250) NOT NULL,"
                + "`isTrue` TINYINT(4) NOT NULL,"
                + "`pergunta_id` INT(11) NOT NULL,"
                + "PRIMARY KEY (`id`),"
                + "INDEX `fk_alternativa_pergunta1_idx` (`pergunta_id` ASC) VISIBLE,"
                + "CONSTRAINT `fk_alternativa_pergunta1`"
                + " FOREIGN KEY (`pergunta_id`)"
                + " REFERENCES `quinkasbd`.`pergunta` (`id`)"
                + " ON DELETE NO ACTION"
                + " ON UPDATE NO ACTION)"
                + " ENGINE = InnoDB"
                + " AUTO_INCREMENT = 13"
                + " DEFAULT CHARACTER SET = utf8;");
        return sqls;
    }

}
