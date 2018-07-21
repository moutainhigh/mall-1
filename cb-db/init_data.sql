INSERT INTO `insurance_product` VALUES (1, '生命福星高照终身寿险（分红型）', '福相随综合保障计划（2017版）', 'http://pb9sg55i7.bkt.clouddn.com/Fjf83MxmMEf9X2b3q7p2PslcjYDk', './static/img/product1.a28ff7e.png', '爱相伴、心相知、福相随', '终身', '十年', '生命福星高照终身寿险', '2018-07-05 16:28:07');
INSERT INTO `insurance_product` VALUES (2, '富德生命鑫福来年金保险', '富德生命鑫福来年金保险', 'http://pb9sg55i7.bkt.clouddn.com/FlZfNYa_yZ63AUyinW8V0A0-iwNt', './static/img/product2.17cf939.png', '鑫福来，鑫福送福乐开怀', '终身', '十年', '富德生命鑫福来年金保险', '2018-07-07 19:22:10');


INSERT INTO `insurance_product_price` VALUES (1, 1, 20000, '元', '返10万', '2018-07-05 16:44:20', '返10万');
INSERT INTO `insurance_product_price` VALUES (2, 1, 50000, '元', '返10万', '2018-07-07 19:19:48', '返10万');
INSERT INTO `insurance_product_price` VALUES (3, 1, 100000, '元', '返10万', '2018-07-07 19:20:17', '返10万');
INSERT INTO `insurance_product_price` VALUES (4, 2, 20000, '元', '返10万', '2018-07-07 19:20:57', '返10万');
INSERT INTO `insurance_product_price` VALUES (5, 2, 50000, '元', '返10万', '2018-07-07 19:28:21', '返10万');
INSERT INTO `insurance_product_price` VALUES (6, 2, 100000, '元', '返10万', '2018-07-07 19:28:35', '返10万');

INSERT INTO `role_info` VALUES (1, '2016-02-15 10:18:50', 'SUPER_ROLE', 'SUPER_ROLE', '超级管理员', 1);
INSERT INTO `user_info` VALUES (1, '2016-02-11 14:53:27', NULL, b'1', '2018-07-21 14:13:38', NULL, '123456', NULL, NULL, NULL, b'1', 'admin', 1);
INSERT INTO `user_role` VALUES (1, 1);