DROP TABLE `Trajet`;

CREATE TABLE `Trajet` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `depart` varchar(255) default NULL,
  `arrivee` varchar(255) default NULL,
  `cout` mediumint default NULL,
  `temps` mediumint default NULL,
  `gps` varchar(30) default NULL,
  `update_date` date default CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;


INSERT INTO `Trajet` (`id`,`depart`,`arrivee`,`cout`,`temps`,`gps`) VALUES (1,"P.O. Box 885, 1348 Dui Road","Ap #839-6507 Donec Rd.",6017,35,"-9.82013, 164.80831");
INSERT INTO `Trajet` (`id`,`depart`,`arrivee`,`cout`,`temps`,`gps`) VALUES (2,"139-7284 Nullam Road","P.O. Box 838, 3847 Eget Street",7499,74,"-74.23457, -173.84694");
INSERT INTO `Trajet` (`id`,`depart`,`arrivee`,`cout`,`temps`,`gps`) VALUES (3,"871-4217 Odio. Avenue","Ap #676-6885 Donec Road",6530,86,"-41.69093, 25.92279");
INSERT INTO `Trajet` (`id`,`depart`,`arrivee`,`cout`,`temps`,`gps`) VALUES (4,"7691 Enim, Avenue","7719 Iaculis Rd.",9842,81,"3.47233, 43.87247");
INSERT INTO `Trajet` (`id`,`depart`,`arrivee`,`cout`,`temps`,`gps`) VALUES (5,"4898 Lorem, Av.","5219 Nisl. Av.",4202,16,"-71.57085, -123.56187");
INSERT INTO `Trajet` (`id`,`depart`,`arrivee`,`cout`,`temps`,`gps`) VALUES (6,"Ap #952-2304 Luctus Road","311-9708 Fusce St.",4111,66,"27.37982, 172.12627");
INSERT INTO `Trajet` (`id`,`depart`,`arrivee`,`cout`,`temps`,`gps`) VALUES (7,"Ap #862-8481 Elit Ave","577-6319 Aliquam St.",5396,55,"-65.10201, 84.55313");
INSERT INTO `Trajet` (`id`,`depart`,`arrivee`,`cout`,`temps`,`gps`) VALUES (8,"Ap #970-5936 Proin Rd.","Ap #446-7715 Consectetuer Street",8157,2,"-37.54462, -152.30607");
INSERT INTO `Trajet` (`id`,`depart`,`arrivee`,`cout`,`temps`,`gps`) VALUES (9,"P.O. Box 840, 9552 Nunc Rd.","Ap #769-6360 Dui Av.",4585,63,"-24.14364, -83.87838");
INSERT INTO `Trajet` (`id`,`depart`,`arrivee`,`cout`,`temps`,`gps`) VALUES (10,"8892 Eget Street","Ap #279-6517 Nec St.",6894,88,"19.55589, -1.03721");
