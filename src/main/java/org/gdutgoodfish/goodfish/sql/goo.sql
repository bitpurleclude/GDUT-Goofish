-- MySQL dump 10.13  Distrib 8.0.33, for macos13 (arm64)
--
-- Host: 127.0.0.1    Database: goo
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category_name` char(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'手机'),(2,'数码'),(3,'电脑'),(4,'运动'),(5,'潮玩'),(6,'家电'),(7,'游戏'),(8,'其他');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `item_id` bigint NOT NULL,
  `context` char(255) NOT NULL,
  `create_time` datetime NOT NULL,
  `likes` bigint NOT NULL DEFAULT (0) COMMENT '点赞',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评价表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,1,1,'Excellent seller, very responsive.','2024-11-15 13:36:46',0),(2,2,2,'Good item, but shipping took a bit longer than expected.','2024-11-15 13:37:21',0),(8,1,1,'小米手机性价比超高，二手的也很耐用。','2024-11-17 09:00:00',0),(9,2,1,'这个小米手机外观保存得很好，使用体验不错。','2024-11-17 09:10:00',0),(10,1,1,'小米手机系统很流畅，功能也都正常。','2024-11-17 09:20:00',0),(11,2,1,'二手小米手机的拍照效果让我很惊喜。','2024-11-17 09:30:00',0),(12,1,1,'电池续航能力还可以，整体满意。','2024-11-17 09:40:00',0),(13,1,3,'华为手机质量可靠，二手的没什么毛病。','2024-11-17 09:50:00',0),(14,2,3,'这款华为手机信号很强，使用起来感觉很棒。','2024-11-17 10:00:00',0),(15,1,3,'华为手机的配置不错，二手的性价比很高。','2024-11-17 10:10:00',0),(16,2,3,'外观设计很时尚，二手华为手机依然很亮眼。','2024-11-17 10:20:00',0),(17,1,3,'操作很便捷，华为手机的品质值得信赖。','2024-11-17 10:30:00',0),(18,1,5,'iPhone 15性能确实强劲，二手的也很流畅。','2024-11-17 10:40:00',0),(19,2,5,'外观轻微磨损完全不影响，二手的很值。','2024-11-17 10:50:00',0),(20,1,5,'拍照效果超赞，iPhone 15二手的性价比可以。','2024-11-17 11:00:00',0),(21,2,5,'系统很稳定，二手的iPhone 15使用体验好。','2024-11-17 11:10:00',0),(22,1,5,'电池续航还不错，是个不错的二手手机。','2024-11-17 11:20:00',0),(23,1,6,'vivo X100拍照真的出色，二手成色好是优点。','2024-11-17 11:30:00',0),(24,2,6,'运行速度挺快的，二手vivo X100很划算。','2024-11-17 11:40:00',0),(25,1,6,'外观设计精美，二手vivo X100很吸引人。','2024-11-17 11:50:00',0),(26,2,6,'系统操作便捷，这个二手手机买得值。','2024-11-17 12:00:00',0),(27,1,6,'电池续航能满足日常，vivo X100二手不错。','2024-11-17 12:10:00',0),(28,1,7,'佳能EOS R6 Mark II画质输出真高，二手的质量好。','2024-11-17 12:20:00',0),(29,2,7,'使用次数少，二手相机状态很棒，很满意。','2024-11-17 12:30:00',0),(30,1,7,'操作方便，佳能这款二手相机性价比高。','2024-11-17 12:40:00',0),(31,2,7,'拍摄效果超预期，二手相机物有所值。','2024-11-17 12:50:00',0),(32,1,7,'功能齐全，佳能二手相机值得推荐。','2024-11-17 13:00:00',0),(33,1,8,'大疆Air 3飞行性能卓越，二手有划痕也无妨。','2024-11-17 13:10:00',0),(34,2,8,'航拍效果非常好，二手大疆Air 3很实用。','2024-11-17 13:20:00',0),(35,1,8,'操作简单易上手，二手的性价比高。','2024-11-17 13:30:00',0),(36,2,8,'稳定性很好，这个二手大疆无人机不错。','2024-11-17 13:40:00',0),(37,1,8,'功能丰富，二手大疆Air 3值得拥有。','2024-11-17 13:50:00',0),(38,1,9,'联想小新Pro16性能均衡，二手有油光不影响。','2024-11-17 14:00:00',0),(39,2,9,'运行速度可以满足日常，二手笔记本很划算。','2024-11-17 14:10:00',0),(40,1,9,'屏幕显示效果不错，联想二手本质量好。','2024-11-17 14:20:00',0),(41,2,9,'散热性能良好，二手小新Pro16值得买。','2024-11-17 14:30:00',0),(42,1,9,'接口丰富，这个二手联想本很实用。','2024-11-17 14:40:00',0),(43,1,10,'戴尔游匣G16游戏性能强劲，二手屏幕无划痕棒。','2024-11-17 14:50:00',0),(44,2,10,'运行大型游戏很流畅，二手的性价比高。','2024-11-17 15:00:00',0),(45,1,10,'散热系统出色，戴尔二手游戏本不错。','2024-11-17 15:10:00',0),(46,2,10,'外观设计酷炫，二手游匣G16很吸引人。','2024-11-17 15:20:00',0),(47,1,10,'键盘手感舒适，这个二手戴尔本好。','2024-11-17 15:30:00',0),(48,1,11,'耐克Air Max 270穿着舒适透气，二手磨损可接受。','2024-11-17 15:40:00',0),(49,2,11,'外观时尚，二手运动鞋依然很潮。','2024-11-17 15:50:00',0),(50,1,11,'鞋底弹性好，耐克二手鞋质量不错。','2024-11-17 16:00:00',0),(51,2,11,'尺码合适，这双二手耐克鞋很满意。','2024-11-17 16:10:00',0),(52,1,11,'支撑性良好，二手Air Max 270值得买。','2024-11-17 16:20:00',0),(53,1,12,'阿迪达斯Ultraboost 22缓震出色，二手污渍不碍事。','2024-11-17 16:30:00',0),(54,2,12,'跑步很舒服，二手跑鞋性价比高。','2024-11-17 16:40:00',0),(55,1,12,'鞋面材质好，阿迪达斯二手鞋质量好。','2024-11-17 16:50:00',0),(56,2,12,'外观设计简约时尚，二手Ultraboost 22很棒。','2024-11-17 17:00:00',0),(57,1,12,'包裹性强，这双二手阿迪达斯鞋不错。','2024-11-17 17:10:00',0),(58,1,13,'泡泡玛特DIMOO手办造型可爱，二手掉漆不影响。','2024-11-17 17:20:00',0),(59,2,13,'放在桌上很好看，二手的依然有魅力。','2024-11-17 17:30:00',0),(60,1,13,'细节处理到位，泡泡玛特二手手办不错。','2024-11-17 17:40:00',0),(61,2,13,'色彩鲜艳，这个二手DIMOO手办很赞。','2024-11-17 17:50:00',0),(62,1,13,'设计独特，二手泡泡玛特手办值得收藏。','2024-11-17 18:00:00',0),(63,1,14,'Bearbrick积木熊潮流感十足，二手配件齐全赞。','2024-11-17 18:10:00',0),(64,2,14,'外观非常酷炫，二手积木熊很有价值。','2024-11-17 18:20:00',0),(65,1,14,'做工精细，Bearbrick二手熊质量好。','2024-11-17 18:30:00',0),(66,2,14,'放在家里很有档次，二手的也很棒。','2024-11-17 18:40:00',0),(67,1,14,'是个不错的收藏品，二手积木熊值得入手。','2024-11-17 18:50:00',0),(68,1,15,'海尔冰箱保鲜效果好，二手的划痕不影响使用。','2024-11-14 22:36:51',0),(69,2,15,'容量很大，能装好多东西，二手冰箱这个价格超值。','2024-11-15 12:12:02',0),(70,1,15,'制冷效果不错，二手海尔冰箱质量可靠。','2024-11-16 10:30:00',0),(71,2,15,'内部空间设计合理，使用起来很方便，二手的很满意。','2024-11-16 14:45:00',0),(72,1,15,'运行声音很小，这台二手冰箱真不错。','2024-11-16 09:15:00',0),(73,1,16,'美的空调制冷制热都快，二手的使用时长短很划算。','2024-11-16 16:20:00',0),(74,2,16,'节能效果明显，二手空调性价比高。','2024-11-16 11:20:00',0),(75,1,16,'风速调节范围广，能满足不同需求，二手美的空调不错。','2024-11-16 15:30:00',0),(76,2,16,'运行稳定，没出现过问题，这个二手空调值得买。','2024-11-16 10:00:00',0),(77,1,16,'安装方便，二手美的空调使用体验好。','2024-11-16 14:15:00',0),(78,1,17,'PlayStation 5游戏体验超棒，二手的轻微磨损不碍事。','2024-11-16 11:45:00',0),(79,2,17,'游戏运行流畅，二手主机性价比高。','2024-11-16 15:20:00',0),(80,1,17,'手柄握持感舒适，操作方便，二手PS5很赞。','2024-11-16 11:45:00',0),(81,2,17,'画面显示效果好，这个二手游戏主机物有所值。','2024-11-16 15:20:00',0),(82,1,17,'游戏库丰富，二手PlayStation 5值得入手。','2024-11-16 11:45:00',0),(83,1,18,'任天堂Switch OLED便携性强，二手屏幕无划痕真好。','2024-11-16 15:20:00',0),(84,2,18,'游戏玩法多样，二手掌机性价比高。','2024-11-16 15:20:00',0),(85,1,18,'续航能力还可以，满足日常游戏需求，二手的很满意。','2024-11-16 15:20:00',0),(86,2,18,'手柄功能丰富，操作有趣，这个二手任天堂掌机不错。','2024-11-16 15:20:00',0),(87,1,18,'可以玩好多经典游戏，二手Switch OLED值得买。','2024-11-16 15:20:00',0),(88,1,19,'普通笔记本虽然有部分页面使用过，但不影响记录，二手价格实惠。','2024-11-16 09:30:00',0),(89,2,19,'纸质不错，书写很流畅，二手笔记本能满足日常记录需求。','2024-11-16 13:30:00',0),(90,1,19,'装订牢固，本子质量可以，二手的很实用。','2024-11-16 09:30:00',0),(91,2,19,'大小合适，方便携带，这个二手笔记本买得值。','2024-11-16 13:30:00',0),(92,1,19,'封面设计简洁，二手普通笔记本性价比高。','2024-11-16 09:30:00',0),(93,1,20,'雨伞能正常遮雨遮阳，伞骨虽有轻微变形但不影响使用，二手的性价比高。','2024-11-16 13:30:00',0),(94,2,20,'大小合适，携带方便，二手雨伞很实用。','2024-11-16 13:30:00',0),(95,1,20,'伞面材质防水性好，二手雨伞质量不错。','2024-11-16 13:30:00',0),(96,2,20,'打开和收起都很顺畅，这把二手雨伞令人满意。','2024-11-16 13:30:00',0),(97,1,20,'颜色好看，二手雨伞值得购买。','2024-11-16 13:30:00',0);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite`
--

DROP TABLE IF EXISTS `favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorite` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `item_id` bigint NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='收藏';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite`
--

LOCK TABLES `favorite` WRITE;
/*!40000 ALTER TABLE `favorite` DISABLE KEYS */;
INSERT INTO `favorite` VALUES (1,1,1,'2024-11-14 22:37:05'),(2,1,2,'2024-11-14 22:37:31'),(3,1,3,'2024-11-14 22:37:37'),(4,1,4,'2024-11-14 22:37:45'),(6,1,1,'2024-11-17 09:00:00'),(7,2,3,'2024-11-17 10:00:00'),(8,1,5,'2024-11-17 10:40:00'),(9,2,6,'2024-11-17 11:40:00'),(10,1,7,'2024-11-17 12:20:00'),(11,2,8,'2024-11-17 13:20:00'),(12,1,9,'2024-11-17 14:00:00'),(13,2,10,'2024-11-17 15:00:00'),(14,1,11,'2024-11-17 15:40:00'),(15,2,12,'2024-11-17 16:40:00'),(16,1,13,'2024-11-17 17:20:00'),(17,2,14,'2024-11-17 18:20:00'),(18,1,15,'2024-11-14 22:36:51'),(19,2,16,'2024-11-16 16:20:00'),(20,1,17,'2024-11-16 11:45:00'),(21,2,18,'2024-11-16 15:20:00'),(22,1,19,'2024-11-16 09:30:00'),(23,2,20,'2024-11-16 13:30:00');
/*!40000 ALTER TABLE `favorite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `name` char(255) NOT NULL COMMENT '商品名称',
  `price` decimal(20,6) NOT NULL COMMENT '价格',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `image` char(255) NOT NULL COMMENT '图片',
  `description` char(255) NOT NULL COMMENT '详情',
  `category_id` bigint NOT NULL COMMENT '分类id',
  `location` char(255) NOT NULL COMMENT '位置',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'小米',599.990000,3,'https://goo-fish.oss-cn-guangzhou.aliyuncs.com/item/xiaomi.webp.avif','二手机',1,'北京','2024-11-14 22:36:51'),(3,'华为',699.990000,3,'https://goo-fish.oss-cn-guangzhou.aliyuncs.com/item/huawei.webp.avif','二手机',1,'广州','2024-11-15 12:12:02'),(5,'iPhone 15',5500.000000,3,'https://goo-fish.oss-cn-guangzhou.aliyuncs.com/item/iphone15.webp.avif','二手iPhone 15，性能强劲，外观轻微磨损',1,'上海','2024-11-16 10:30:00'),(6,'vivo X100',2500.000000,3,'https://goo-fish.oss-cn-guangzhou.aliyuncs.com/item/vivox100.webp.avif','二手vivo X100，拍照出色，成色较好',1,'深圳','2024-11-16 14:45:00'),(7,'佳能EOS R6 Mark II',10500.000000,3,'https://goo-fish.oss-cn-guangzhou.aliyuncs.com/item/jianeng.webp.avif','二手佳能专业单反相机，高画质输出，使用次数较少',2,'北京','2024-11-16 09:15:00'),(8,'大疆Air 3',4500.000000,3,'https://goo-fish.oss-cn-guangzhou.aliyuncs.com/item/dajiang.webp.avif','二手大疆航拍无人机，飞行性能卓越，外观有少许划痕',2,'成都','2024-11-16 16:20:00'),(9,'联想小新Pro16',3500.000000,4,'https://goo-fish.oss-cn-guangzhou.aliyuncs.com/item/liangxiang.avif','二手联想轻薄笔记本，性能均衡，键盘有轻微油光',3,'武汉','2020-11-16 11:20:00'),(10,'戴尔游匣G16',5000.000000,4,'https://goo-fish.oss-cn-guangzhou.aliyuncs.com/item/daier.webp.avif','二手戴尔游戏笔记本，游戏性能强劲，屏幕无划痕',3,'杭州','2024-11-16 15:30:00'),(11,'耐克Air Max 270',600.000000,4,'https://goo-fish.oss-cn-guangzhou.aliyuncs.com/item/naike.webp.avif','二手耐克运动鞋，舒适透气，鞋底有一定磨损',4,'广州','2024-11-16 08:45:00'),(12,'阿迪达斯Ultraboost 22',700.000000,4,'https://goo-fish.oss-cn-guangzhou.aliyuncs.com/item/adidasi.webp.avif','二手阿迪达斯跑鞋，缓震出色，鞋面有小污渍',4,'上海','2024-11-16 13:10:00'),(13,'泡泡玛特DIMOO经典系列',30.000000,4,'https://goo-fish.oss-cn-guangzhou.aliyuncs.com/item/paopaomate.webp.avif','二手泡泡玛特DIMOO手办，造型可爱，有轻微掉漆',5,'北京','2024-11-16 12:00:00'),(14,'Bearbrick积木熊400%',200.000000,4,'https://goo-fish.oss-cn-guangzhou.aliyuncs.com/item/bearbrick.webp.avif','二手Bearbrick积木熊，潮流玩具，配件齐全',5,'深圳','2024-11-16 16:45:00'),(15,'海尔冰箱BCD-596WGHSS17B8U1',2500.000000,3,'https://goo-fish.oss-cn-guangzhou.aliyuncs.com/item/haier.webp.avif','二手海尔大容量冰箱，保鲜效果好，外观有划痕',6,'武汉','2024-11-16 10:00:00'),(16,'美的空调KFR-35GW/N8XHC1',1500.000000,3,'https://goo-fish.oss-cn-guangzhou.aliyuncs.com/item/meidi.webp.avif','二手美的节能空调，制冷制热快，使用时长较短',6,'成都','2024-11-16 14:15:00'),(17,'PlayStation 5',2800.000000,3,'https://goo-fish.oss-cn-guangzhou.aliyuncs.com/item/playstation.webp.avif','二手索尼次世代游戏主机，游戏体验出色，外壳有轻微磨损',7,'杭州','2024-11-16 11:45:00'),(18,'任天堂Switch OLED',1800.000000,3,'https://goo-fish.oss-cn-guangzhou.aliyuncs.com/item/switch.webp.avif','二手任天堂掌机，便携性强，屏幕无划痕',7,'深圳','2024-11-16 15:20:00'),(19,'普通笔记本',10.000000,4,'https://goo-fish.oss-cn-guangzhou.aliyuncs.com/item/putongbijiben.webp.avif','二手普通纸质笔记本，用于日常记录，有部分页面已使用',8,'北京','2024-11-16 09:30:00'),(20,'雨伞',8.000000,4,'https://goo-fish.oss-cn-guangzhou.aliyuncs.com/item/yusan.webp.avif','二手普通雨伞，遮雨遮阳，伞骨有轻微变形',8,'上海','2024-11-16 13:30:00');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `send_id` bigint NOT NULL,
  `receive_id` bigint NOT NULL,
  `context` char(255) NOT NULL,
  `create_time` datetime NOT NULL,
  `read` int NOT NULL COMMENT '是否读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='消息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (12,1,3,'你好呀，我看到你在卖小米手机，我挺感兴趣的呢。','2024-11-17 10:00:00',0),(13,3,1,'哈哈，是啊，这小米手机很不错的哦，性价比挺高。','2024-11-17 10:01:00',0),(14,1,3,'嗯，我想了解下它的外观情况呀，有明显划痕或者磕碰吗？','2024-11-17 10:02:00',0),(15,3,1,'外观整体还不错啦，就只有一点点轻微划痕，不仔细看都不太明显的。','2024-11-17 10:03:00',0),(16,1,3,'哦哦，那手机的屏幕显示效果怎么样呀？有没有坏点之类的？','2024-11-17 10:04:00',0),(17,3,1,'屏幕显示效果很好哦，我平时用着都没发现有坏点啥的，很清晰呢。','2024-11-17 10:05:00',0),(18,1,3,'好的呀，那手机的电池续航能力还行不？能撑多久呀？','2024-11-17 10:06:00',0),(19,3,1,'电池续航还可以的，正常使用一天一充没啥问题哦。','2024-11-17 10:07:00',0),(20,1,3,'嗯呢，那手机的系统运行流畅吗？会不会卡顿呀？','2024-11-17 10:08:00',0),(21,3,1,'系统运行挺流畅的呀，我用着都没怎么卡顿过，小米的系统优化得还不错呢。','2024-11-17 10:09:00',0),(22,1,3,'哦哦，这样啊，那手机的拍照效果怎么样呢？清晰吗？','2024-11-17 10:10:00',0),(23,3,1,'拍照效果也还不错哦，日常拍照完全够用啦，像素挺高的呢。','2024-11-17 10:11:00',0),(24,1,3,'好的呀，我还想问下，手机的配件都齐全不？比如充电器、耳机啥的。','2024-11-17 10:12:00',0),(25,3,1,'配件都齐全的哦，原装的充电器和耳机都在呢，你放心。','2024-11-17 10:13:00',0),(26,1,3,'嗯呢，那这个价格还有没有商量的余地呀？','2024-11-17 10:14:00',0),(27,3,1,'哎呀，这个价格已经很实惠啦，真的是性价比超高的，不太好再降了呢。','2024-11-17 10:15:00',0),(28,1,3,'好吧，那我再考虑考虑吧，谢谢你啦。','2024-11-17 10:16:00',0),(29,3,1,'好的呀，你好好考虑下哦，有啥问题随时问我呢。','2024-11-17 10:17:00',0),(30,1,3,'嗯呢，我会的，先这样啦，拜拜。','2024-11-17 10:18:00',0),(31,3,1,'好的呀，拜拜，希望你能早日决定哦。','2024-11-17 10:19:00',0),(32,2,4,'哈喽，我看到你在卖联想小新Pro16笔记本呢，我有点兴趣哦。','2024-11-17 12:00:00',0),(33,4,2,'哈哈，是啊，这笔记本挺不错的呀，性能很均衡呢。','2024-11-17 12:01:00',0),(34,2,4,'嗯，我想先了解下它的外观情况呀，键盘的油光严重吗？','2024-11-17 12:02:00',0),(35,4,2,'键盘的油光其实还好啦，就只是有一点点轻微的，不影响使用哦。','2024-11-17 12:03:00',0),(36,2,4,'哦哦，那电脑的屏幕显示效果怎么样呀？色彩还原度高吗？','2024-11-17 12:04:00',0),(37,4,2,'屏幕显示效果很不错哦，色彩还原度挺高的，看视频啥的都很舒服呢。','2024-11-17 12:05:00',0),(38,2,4,'好的呀，那电脑的电池续航能力还行不？能撑多久呀？','2024-11-17 12:06:00',0),(39,4,2,'电池续航还可以的，正常办公使用的话，大概能撑个四五个小时吧。','2024-11-17 12:07:00',0),(40,2,4,'嗯呢，那电脑的系统运行流畅吗？会不会卡顿呀？','2024-11-17 12:08:00',0),(41,4,2,'系统运行挺流畅的呀，我用着都没怎么卡顿过，联想的系统优化得还不错呢。','2024-11-17 12:09:00',0),(42,2,4,'哦哦，这样啊，那电脑的散热情况怎么样呢？','2024-11-17 12:10:00',0),(43,4,2,'散热情况还不错哦，我平时用着也没觉得很热，它的散热设计还是挺好的呢。','2024-11-17 12:11:00',0),(44,2,4,'好的呀，我还想问下，电脑的配件都齐全不？比如充电器、鼠标啥的。','2024-11-17 12:12:00',0),(45,4,2,'配件都齐全的哦，原装的充电器和鼠标都在呢，你放心。','2024-11-17 12:13:00',0),(46,2,4,'嗯呢，那这个价格还有没有商量的余地呀？','2024-11-17 12:14:00',0),(47,4,2,'哎呀，这个价格已经很实惠啦，真的是性价比超高的，不太好再降了呢。','2024-11-17 12:15:00',0),(48,2,4,'好吧，那我再考虑考虑吧，谢谢你啦。','2024-11-17 12:16:00',0),(49,4,2,'好的呀，你好好考虑下哦，有啥问题随时问我呢。','2024-11-17 12:17:00',0),(50,2,4,'嗯呢，我会的，先这样啦，拜拜。','2024-11-17 12:18:00',0),(51,4,2,'好的呀，拜拜，希望你能早日决定哦。','2024-11-17 12:19:00',0);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `item_id` bigint NOT NULL,
  `number` char(255) NOT NULL COMMENT '订单号',
  `user_id` bigint NOT NULL,
  `status` int NOT NULL DEFAULT (0) COMMENT '0已下单待支付1已支付2已接收',
  `source` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '发货地',
  `target` char(255) NOT NULL COMMENT '收货地',
  `creat_time` datetime NOT NULL,
  `price` decimal(20,6) NOT NULL,
  `item_name` char(255) NOT NULL,
  `item_image` char(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_cart`
--

DROP TABLE IF EXISTS `shop_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop_cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `item_id` bigint NOT NULL COMMENT '商品id',
  `item_number` int NOT NULL COMMENT '商品数量',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `item_price` decimal(20,6) NOT NULL DEFAULT (0) COMMENT '商品价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='购物车';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_cart`
--

LOCK TABLES `shop_cart` WRITE;
/*!40000 ALTER TABLE `shop_cart` DISABLE KEYS */;
INSERT INTO `shop_cart` VALUES (1,1,1,'2024-11-17 14:00:00',1,599.990000),(2,3,1,'2024-11-17 14:02:00',1,699.990000),(3,5,1,'2024-11-17 14:04:00',1,5500.000000),(4,7,1,'2024-11-17 14:06:00',1,10500.000000),(5,11,1,'2024-11-17 14:08:00',1,600.000000),(6,9,1,'2024-11-17 14:10:00',2,3500.000000),(7,6,1,'2024-11-17 14:12:00',2,2500.000000),(8,8,1,'2024-11-17 14:14:00',2,4500.000000),(9,12,1,'2024-11-17 14:16:00',2,700.000000),(10,14,1,'2024-11-17 14:18:00',2,200.000000);
/*!40000 ALTER TABLE `shop_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `email` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '未设置' COMMENT '邮箱',
  `sex` int NOT NULL DEFAULT (0) COMMENT '0未定义1男2女',
  `phone_number` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '未设置' COMMENT '电话号码',
  `avatar` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '未设置' COMMENT '头像',
  `creat_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'jack','123','未设置',1,'未设置','https://goo-fish.oss-cn-guangzhou.aliyuncs.com/avatar/user1.jpg','2024-11-14 16:41:39'),(2,'张三','123456','未设置',1,'未设置','https://goo-fish.oss-cn-guangzhou.aliyuncs.com/avatar/user2.jpeg','2024-11-14 22:35:08'),(3,'李四','123456','未设置',0,'未设置','https://goo-fish.oss-cn-guangzhou.aliyuncs.com/avatar/user3.jpeg','2024-11-14 22:35:26'),(4,'王五','123','未设置',0,'未设置','https://goo-fish.oss-cn-guangzhou.aliyuncs.com/avatar/user4.jpg','2024-11-14 23:11:47'),(5,'npc','202cb962ac59075b964b07152d234b70','未设置',0,'未设置','https://goo-fish.oss-cn-guangzhou.aliyuncs.com/avatar/user5.jpg','2024-11-14 23:38:59'),(6,'abc','250cf8b51c773f3f8dc8b4be867a9a02','未设置',0,'未设置','https://goo-fish.oss-cn-guangzhou.aliyuncs.com/avatar/user6.jpg','2024-11-14 23:40:43');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-17 14:16:25
