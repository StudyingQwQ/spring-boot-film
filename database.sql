drop database film;
create database film;
USE film;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for db_account
-- ----------------------------
DROP TABLE IF EXISTS `db_account`;
CREATE TABLE `db_account`  (
                               `id` int(0) NOT NULL AUTO_INCREMENT,
                               `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                               `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                               `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                               `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                               `points` int(0) NULL DEFAULT NULL,
                               `register_time` datetime(0) NULL DEFAULT NULL,
                               PRIMARY KEY (`id`) USING BTREE,
                               UNIQUE INDEX `unique_email`(`email`) USING BTREE,
                               UNIQUE INDEX `unique_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for db_film
-- ----------------------------
DROP TABLE IF EXISTS `db_film`;
CREATE TABLE `db_film`  (
                            `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '电影ID',
                            `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电影名称',
                            `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电影简介',
                            `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电影类型',
                            `actor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电影主演',
                            `region` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电影地区',
                            `good` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电影好评',
                            `wheat` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电影周热度',
                            `mheat` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电影月热度',
                            `time` date NULL DEFAULT NULL COMMENT '电影上映时间',
                            `picurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片链接',
                            `filmurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频链接',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;


-- Inserting sample data into db_film table
INSERT INTO db_film (name, intro, type, actor, region, good, wheat, mheat, time, picurl, filmurl) VALUES
                                                                                                      ('Mobile Suit Gundam: NT - Narrative',
                                                                                                       'U.C.0097──。“拉普拉斯的盒子”被打开一年后。 即使已经揭露了提及新人类的存在和应有权利的“宇宙世纪宪章”，世界格局还是没有发生太大的变化。随着新吉翁残党“带袖的”的瓦解，被称为“拉普拉斯事变”的纷争也随之终结。 在那场最后的战斗里，两部全精神感应框架式样机动战士，展现出超越人类智能的力量。 白色独角兽与黑色狮子，这两部机动战士的威胁，本应已随着受到封印而逐渐从人的记忆中远去，甚至被遗忘了……',
                                                                                                       '动作', 'Erika Ishii, Patrick Seitz',
                                                                                                       '日本', 6.1, 1000, 5000, '2018-01-01',
                                                                                                       'http://localhost:9000/test/cover/1.jpg',
                                                                                                       'http://localhost:9000/test/video/test.mp4'),
                                                                                                      ('The Bricklayer',
                                                                                                       '有人正在勒索中央情报局——通过暗杀外国记者，并栽赃CIA。 当世界开始联合起来反对美国时，中央情报局必须出动他们最聪明且叛逆的特工重返江湖，迫使他面对自己曲折的过去，同时揭开一个国际阴谋。',
                                                                                                       '动作', 'Nina Dobrev, Aaron Eckhart',
                                                                                                       '美国', 5.0, 800, 4000, '2023-02-15',
                                                                                                       'http://localhost:9000/test/cover/2.jpg',
                                                                                                       'http://localhost:9000/test/video/test.mp4'),
                                                                                                      ('Napoleon',
                                                                                                       '影片将以原创角色视角看待拿破仑与挚爱约瑟芬之间波折起伏的关系，展现拿破仑一生经历过的著名战役，作为一个能力非凡、有远见的军事家的野心和战略头脑，以及他如何迅速发迹并最终成为法兰西第一帝国皇帝。',
                                                                                                       '历史', 'Vanessa Kirby, Joaquin Phoenix',
                                                                                                       '美国', 6.5, 800, 4000, '2023-02-15',
                                                                                                       'http://localhost:9000/test/cover/3.jpg',
                                                                                                       'http://localhost:9000/test/video/test.mp4'),
                                                                                                      ('Tiger 3',
                                                                                                       '《猛虎3》由玛尼什·莎玛执导，萨尔曼·汗、卡特莉娜·卡芙和埃朗·哈斯米主演，是《猛虎还活着》的续集，同时也是YRF间谍宇宙的第五部。',
                                                                                                       '动作', 'Salman Khan, Simran',
                                                                                                       '美国', 6.7, 800, 4000, '2023-02-15',
                                                                                                       'http://localhost:9000/test/cover/4.jpg',
                                                                                                       'http://localhost:9000/test/video/test.mp4'),
                                                                                                      ('Last Night of Amore',
                                                                                                       '米兰在35年后退休的前一天晚上，Amore中尉看到他的世界被颠覆了。但也许是他周围的世界发生了变化。一部惊悚片在法律和犯罪之间危险地徘徊。',
                                                                                                       '剧情', 'Pierfrancesco Favino',
                                                                                                       '美国', 7.2, 800, 4000, '2023-02-15',
                                                                                                       'http://localhost:9000/test/cover/5.jpg',
                                                                                                       'http://localhost:9000/test/video/test.mp4'),
                                                                                                      ('Justice League: Crisis on Infinite Earths - Part One',
                                                                                                       '死亡即将来临。比死亡更糟糕的是遗忘。这不仅仅关乎我们的地球，而是关乎每一处、每一个人，在每一个宇宙!为了抵御这种终极破坏，神秘的观察者已经聚集了史上最伟大的超级英雄队伍。但集合超人、神奇女侠、蝙蝠侠、闪电侠、绿灯侠以及来自多个地球的数百名超级英雄的力量，他们又能做些什么来拯救所有现实免于无法阻挡的反物质末日呢?',
                                                                                                       '冒险 ', 'Alexandra Daddario, Jensen Ackles',
                                                                                                       '美国', 6.5, 800, 4000, '2023-02-15',
                                                                                                       'http://localhost:9000/test/cover/6.jpg',
                                                                                                       'http://localhost:9000/test/video/test.mp4'),
                                                                                                      ('Spider-Man: Far from Home',
                                                                                                       '在复仇者联盟众英雄的努力下，于灭霸无限手套事件中化作为灰烬的人们，重新回到了人世间，曾经消失的蜘蛛侠 彼得帕克 也回归到了普通的生活之中，数月后，蜘蛛侠彼得帕克所在的学校举行了欧洲旅游，帕克也在其中， 在欧州威尼斯旅行时，一个巨大无比的水怪袭击了威尼斯，不敌敌人的蜘蛛侠幸得一位自称神秘客的男子搭救才击退敌人，之后 神盾局局长找上正在旅游的彼得帕克并要求其加入神盾局，并安排神秘客协助帕克',
                                                                                                       '动作', 'Jake Gyllenhaal, Tom Holland',
                                                                                                       '美国', 7.4, 800, 4000, '2019-02-15',
                                                                                                       'http://localhost:9000/test/cover/7.jpg',
                                                                                                       'http://localhost:9000/test/video/test.mp4'),
                                                                                                      ('Deadpool',
                                                                                                       '油嘴滑舌的雇佣兵韦德·威尔森（瑞恩·雷诺兹 Ryan Reynolds 饰）与俏皮可爱的瓦内莎（莫蕾娜·巴卡林 Morena Baccarin 饰）邂逅，但在爱情最美满的时刻，他被诊断患有晚期癌症，留在世上的时间所剩无几。',
                                                                                                       '动作', 'Ryan Reynolds, Morena Baccarin',
                                                                                                       '美国', 8.0, 800, 4000, '2016-02-15',
                                                                                                       'http://localhost:9000/test/cover/8.jpg',
                                                                                                       'http://localhost:9000/test/video/test.mp4'),
                                                                                                      ('Puss in Boots',
                                                                                                       '他是一只穿靴子的猫（Antonio Banderas 配音），多年来行侠仗义，行走江湖，剑术高明，胆大卖萌，虽是官兵通缉捉拿的要犯，但从来都无所畏惧，泰然自若。在某个极不友好的小酒馆里，靴猫听说杰克（Billy Bob Thornton 配音）和吉尔 （Amy Sedaris 配音）这对雌雄恶棍拿到了传说中的魔豆，魔豆长出的豆荚直通巨人的宫殿，而那里住着令人垂涎可以下金蛋的鹅。',
                                                                                                       '动画', 'Salma Hayek, Jossara Jinaro',
                                                                                                       '美国', 6.6, 800, 4000, '2011-02-15',
                                                                                                       'http://localhost:9000/test/cover/9.jpg',
                                                                                                       'http://localhost:9000/test/video/test.mp4'),
                                                                                                      ('The Wolverine2',
                                                                                                       '位于加拿大的深山老林，背负着永生之苦的“金刚狼”罗根隐居于此。在一次与猎人的冲突中，他得到日本女子雪绪的帮助。雪绪是罗根二战期间的旧相识矢志田派来的下属，似乎不久于人世的老友渴望死去之间再见罗根一面。',
                                                                                                       '动作', 'Famke Janssen, Hugh Jackman',
                                                                                                       '美国', 6.7, 800, 4000, '2013-02-15',
                                                                                                       'http://localhost:9000/test/cover/10.jpg',
                                                                                                       'http://localhost:9000/test/video/test.mp4'),
                                                                                                      ('Bumblebee',
                                                                                                       '意图征服一切的霸天虎全员出击，塞伯坦即将陨落，B-127临危受命前往地球，等待重新建立基地的时机。在1987年的加州，B-127不仅遭到特工伯恩斯的追捕，更被闪电殴至重伤，语言模块和记忆模块严重受损。',
                                                                                                       '动作', 'Hailee Steinfeld, John Cena',
                                                                                                       '美国', 6.7, 800, 4000, '2018-02-15',
                                                                                                       'http://localhost:9000/test/cover/11.jpg',
                                                                                                       'http://localhost:9000/test/video/test.mp4'),
                                                                                                      ('Django Unchained',
                                                                                                       '1858年，美国南北战争前两年。德国赏金猎人金·舒尔茨（克里斯托弗·瓦尔兹 Christoph Waltz饰）从贩奴商人手中买下黑奴姜戈（杰米·福克斯 Jamie Foxx饰），让其重获自由。舒尔茨实则是要把姜戈培养成无法无天的赏金猎人，为其缉拿各种通缉犯以换取报酬。',
                                                                                                       '动作', 'Leonardo DiCaprio, Quentin Tarantino',
                                                                                                       '美国', 8.5, 800, 4000, '2012-02-15',
                                                                                                       'http://localhost:9000/test/cover/12.jpg',
                                                                                                       'http://localhost:9000/test/video/test.mp4');