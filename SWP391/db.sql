CREATE DATABASE IF NOT EXISTS
test_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE test_db;

DROP TABLE IF EXISTS `Categories`;--danh muc
CREATE TABLE `Categories` (
  `categoryID` varchar(10) NOT NULL,
  `categoryName` varchar(100) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
USE test_db;
INSERT INTO `Categories` (`categoryID`, `categoryName`, `description`) VALUES
('C0101', 'Life insurance', 'Life insurance is a solution to protect participants against risks related to health, body and life, thereby helping to reduce financial pressure and live a stable life.'),
('C0102', 'Non-life insurance', 'Non-life insurance is a type of insurance that pays and compensates for loss when the participant encounters material damage such as fire, explosion, natural disaster (flood, storm, earthquake), theft or travel accident.'),
('C0103', 'Health insurance', 'Health insurance is a type of insurance that supports the payment of medical expenses, in case a participant takes care of health at a hospital or encounters a risk related to an accident, hospital stay, critical illness, death or total permanent disability.'),
('C0104', 'Deposit insurance', 'Deposit insurance is a guarantee to return money to participants within a specified limit, when the deposit receiving organization (bank, financial institution) falls into insolvency or bankruptcy.'),
('C0105', 'Health Insurance', 'Health insurance is a type of community health care insurance, helping participants to be supported with part or all of the treatment fee in case they encounter risks of illness or disease.'),
('C0106', 'Social insurance', 'Social insurance is a mode of financial compensation for employees in case their income is reduced due to illness, disease, maternity, labor accident, retirement or death. Social insurance includes two types: compulsory social insurance and voluntary social insurance.')
;
DROP TABLE IF EXISTS `Products`;
USE test_db;
CREATE TABLE `Products` (
  `productID` varchar(10) NOT NULL,
  `categoryID` varchar(10) DEFAULT NULL,
  `productName` varchar(50) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

USE test_db;
INSERT INTO `Products` (`productID`, `categoryID`, `productName`, `price`, `description`) VALUES
('P0101', 'C0101', 'Chai', 18, 'Good product'),
('P01010', 'C0104', 'Ikura', 31, 'Good'),
('P01011', 'C0105', 'Queso Cabrales', 21, 'Good'),
('P01012', 'C0105', 'Queso Manchego La Pastora', 38, 'Good'),
('P0102', 'C0101', 'Chang', 19, 'best sell product'),
('P0103', 'C0101', 'Aniseed Syrup', 10, 'new produced'),
('P0104', 'C0102', 'Chef Anton\'s Cajun Seasoning', 22, 'popular product'),
('P0105', 'C0102', 'Chef Anton\'s Gumbo Mix', 21, 'Good For life'),
('P0106', 'C0103', 'Grandma\'s Boysenberry Spread', 25, 'Good'),
('P0107', 'C0103', 'Uncle Bob\'s Organic Dried Pears', 30, 'Good'),
('P0108', 'C0103', 'Northwoods Cranberry Sauce', 40, 'Good'),
('P0109', 'C0104', 'Mishi Kobe Niku', 97, 'Good');

ALTER TABLE `Categories` ADD PRIMARY KEY (`categoryID`);

ALTER TABLE `Products` ADD PRIMARY KEY (`productID`);
COMMIT;