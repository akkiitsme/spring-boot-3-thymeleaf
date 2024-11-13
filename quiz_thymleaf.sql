CREATE DATABASE quiz_portal 

USE quiz_portal;

/*Table structure for table lu_answer */

DROP TABLE IF EXISTS lu_answer;

CREATE TABLE lu_answer (
  answer_id int(10) NOT NULL AUTO_INCREMENT,
  question_id int(10) DEFAULT NULL,
  answer varchar(1500) DEFAULT NULL,
  status tinyint(1) DEFAULT NULL,
  created_on datetime DEFAULT NULL,
  PRIMARY KEY (answer_id)
) 

/*Table structure for table lu_correct_answer */

DROP TABLE IF EXISTS lu_correct_answer;

CREATE TABLE lu_correct_answer (
  correct_answer_id int(10) NOT NULL AUTO_INCREMENT,
  question_id int(10) DEFAULT NULL,
  answer_id int(10) DEFAULT NULL,
  status tinyint(1) DEFAULT NULL,
  created_on datetime DEFAULT NULL,
  PRIMARY KEY (correct_answer_id)
) 

/*Table structure for table lu_question */

DROP TABLE IF EXISTS lu_question;

CREATE TABLE lu_question (
  question_id int(10) NOT NULL AUTO_INCREMENT,
  subject_id int(10) DEFAULT NULL,
  question varchar(1000) DEFAULT NULL,
  que_status tinyint(1) DEFAULT NULL,
  status tinyint(1) DEFAULT NULL,
  created_on datetime DEFAULT NULL,
  PRIMARY KEY (question_id)
) 

/*Table structure for table lu_subject */

DROP TABLE IF EXISTS lu_subject;

CREATE TABLE lu_subject (
  subject_id int(10) NOT NULL AUTO_INCREMENT,
  subject_name varchar(255) DEFAULT NULL,
  subject_status tinyint(1) DEFAULT NULL,
  status tinyint(1) DEFAULT NULL,
  created_on datetime DEFAULT NULL,
  PRIMARY KEY (subject_id)
) 

/*Table structure for table trx_question_answer */

DROP TABLE IF EXISTS trx_question_answer;

CREATE TABLE trx_question_answer (
  que_ans_id int(10) NOT NULL AUTO_INCREMENT,
  user_id int(10) DEFAULT NULL,
  question_id int(10) DEFAULT NULL,
  answer_id int(10) DEFAULT NULL,
  status tinyint(1) DEFAULT NULL,
  created_on datetime DEFAULT NULL,
  PRIMARY KEY (que_ans_id)
) 

/*Table structure for table tx_users */

DROP TABLE IF EXISTS tx_users;

CREATE TABLE tx_users (
  id int(11) NOT NULL AUTO_INCREMENT,
  email varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  phone_no varchar(255) DEFAULT NULL,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL,
  gender varchar(100) DEFAULT NULL,
  dob varchar(100) DEFAULT NULL,
  role char(10) DEFAULT NULL,
  status tinyint(1) DEFAULT NULL,
  created_on datetime DEFAULT NULL,
  PRIMARY KEY (id)
)
