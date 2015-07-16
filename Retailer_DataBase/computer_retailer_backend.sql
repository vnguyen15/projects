
/*	TCSS 445
	Viet Nguyen
	Intermediate Project
	SQL Script
*/

-- Tables: Customers, Products, PruchaseDetails, Pruchases, Stock, Shop, Vendors

CREATE DATABASE Computer_Retailer;
go 


use Computer_Retailer;
go

CREATE TABLE Customers (
    customer_ID int  NOT NULL,
    firstName varchar(30)  NOT NULL,
    lastName varchar(30)  NOT NULL,
    addres varchar(100)  NOT NULL,
    phoneNumber varchar(10)  NOT NULL,
    CONSTRAINT Customers_pk PRIMARY KEY (customer_ID)
);


CREATE TABLE Products (
    barCode int  NOT NULL,
	name varchar(100)  NOT NULL,
    price decimal(10,2)  NOT NULL,
    manfr varchar(100)  NOT NULL,
    date date  NOT NULL,
    itemDescript varchar(500)  NOT NULL,
    CONSTRAINT Products_pk PRIMARY KEY (barCode)
);

CREATE TABLE SaleAssistances (
    ID int  NOT NULL,
    firstName varchar(30)  NOT NULL,
	lastName varchar(30) NOT NULL,
    address varchar(150)  NOT NULL,
    phone decimal(10)  NOT NULL,
    CONSTRAINT Vendors_pk PRIMARY KEY (ID)
);

CREATE TABLE Shops (
    shopID int  NOT NULL,
    name varchar(30)  NOT NULL,
    addres_s varchar(150)  NOT NULL,
	phone decimal(10)  NOT NULL,
    CONSTRAINT Shop_pk PRIMARY KEY (shopID)
);


CREATE TABLE Stock (
    productNumber int  NOT NULL,
    shopNumber int  NOT NULL,
	quantity int  NOT NULL,

    CONSTRAINT Stock_pk PRIMARY KEY (productNumber, shopNumber),
	CONSTRAINT Product_Relationship FOREIGN KEY(productNumber)
			REFERENCES	Products (barCode),
	CONSTRAINT Shop_Relationship FOREIGN KEY(shopNumber)
			REFERENCES	Shops(shopID)
);

CREATE TABLE Trackings (
    trackingNumber int  NOT NULL,
    last_location varchar(50)  NOT NULL,
    destination varchar(150)  NOT NULL,
	status varchar(30)  NOT NULL,

    CONSTRAINT trackings_pk PRIMARY KEY (trackingNumber)
);


CREATE TABLE Purchases (
    transtractionID int  NOT NULL,
    customerID int  NOT NULL,
    assistanceID int  NOT NULL,
    shopNumber int  NOT NULL,
	trackNumber int  NOT NULL,
    date date  NOT NULL,
    Amount decimal(12,2)  NOT NULL,

    CONSTRAINT		Purchases_pk PRIMARY KEY (transtractionID),
	CONSTRAINT		Customers_Relationship FOREIGN KEY(customerID)
			REFERENCES		Customers (customer_ID),
	CONSTRAINT		Vendor_Relationship FOREIGN KEY(assistanceID)
			REFERENCES		SaleAssistances (ID),
	CONSTRAINT		Shops_Relationship FOREIGN KEY(shopNumber)
			REFERENCES		Shops (shopID),
	CONSTRAINT		Trackings_Relationship FOREIGN KEY(trackNumber)
			REFERENCES		Trackings (trackingNumber)
);

CREATE TABLE PurchaseDetails (
	purchaseID int  NOT NULL,
    bar_Code int  NOT NULL,  
    quantity int  NOT NULL,
    CONSTRAINT		PurchaseDetails_pk PRIMARY KEY (purchaseID, bar_Code),
	CONSTRAINT		Purchases_Relationship FOREIGN KEY(purchaseID)
			REFERENCES		Purchases (transtractionID),
	CONSTRAINT		Products_Relationship FOREIGN KEY(bar_Code)
			REFERENCES		Products (barCode)

);

INSERT INTO Customers VALUES (
	1, 'John', 'Smith', '1234 S  ST, Kent WA  98033', 2534457789);
INSERT INTO Customers VALUES (
	2, 'Mary', 'Berreras', '445 S ave , Renton WA  98055', 4252287761);
INSERT INTO Customers VALUES (
	3, 'David', 'Lam', '5678 SE 35 ST, Tacoma WA  98401', 4251114357);
INSERT INTO Customers VALUES (
	4, 'David', 'Lam', '5678 SE 35 ST, Tacoma WA  98401', 4251114357);
INSERT INTO Customers VALUES (
	5, 'Mark', 'Taylor', '721 SE Henderson w, Seattle WA 98107', 2062347654);



INSERT INTO Products VALUES (
	1, 'USB drive', 15.00, 'Cosair','2014/05/19', '8GB  up to 50mps');
INSERT INTO Products VALUES (
	2, 'Hard drive', 49.00, 'Toshiba','2015/01/21', '500 GB');
INSERT INTO Products VALUES (
	3, 'Webcam', 12.00, 'GearHead','2015/01/11', '1.3 Mega Pixel USB 2.0');
INSERT INTO Products VALUES (
	4, 'Dektop PC', 344.00, 'Lenovo','2014/12/06', 'Dual Core AMD A6 6GB DDR3');
INSERT INTO Products VALUES (
	5, 'Speaker', 99.00, 'Infnity','2015/01/12', 'Three way 200 Watts');


INSERT INTO SaleAssistances VALUES (
	1, 'Jenifer', 'Bang','1234 Th 23rd str Seattle  WA  98112',2065543211);
INSERT INTO SaleAssistances VALUES (
	2, 'Anna H.', 'Harris', '4563 SE 55th AVE, LakeWood WA 98496',2534436577);


INSERT INTO Shops VALUES (
	1, 'Best Select', '988 south Seattle way, Seattle WA 98015',2067798855);

INSERT INTO Shops VALUES (
	2, 'Comp World', '7754 North East 255th ST, Renton WA 98057',4252182339);


INSERT INTO Stock VALUES (
	1, 1, 59);
INSERT INTO Stock VALUES (
	1, 2, 35);
INSERT INTO Stock VALUES (
	2, 1, 37);
INSERT INTO Stock VALUES (
	2, 2, 16);
INSERT INTO Stock VALUES (
	3, 1, 43);
INSERT INTO Stock VALUES (
	3, 2, 63);
INSERT INTO Stock VALUES (
	4, 1, 7);
INSERT INTO Stock VALUES (
	4, 2, 0);
INSERT INTO Stock VALUES (
	5, 1, 11);
INSERT INTO Stock VALUES (
	5, 2, 3);

INSERT INTO Trackings VALUES (
	9001, 'Renton, WA', '445 S ave , Renton WA  98055', 'delivered');
INSERT INTO Trackings VALUES (
	9002, 'Tacoma, WA', '445 S ave , Renton WA  98055', 'pending');
INSERT INTO Trackings VALUES (
	9003, 'Kent, WA', '1234 S  ST, Kent WA  98033', 'on carrier');
INSERT INTO Trackings VALUES (
	9004, 'Tacoma, WA', '5678 SE 35 ST, Tacoma WA  98401', 'out for delivery');

INSERT INTO Purchases VALUES (
	1, 2, 2, 2, 9001, '2015/03/02', 30);
INSERT INTO Purchases VALUES (
	2, 2, 2, 1, 9002, '2015/12/12', 12);
INSERT INTO Purchases VALUES (
	3, 1, 2, 1, 9003, '2014/09/17', 344);
INSERT INTO Purchases VALUES (
	4, 3, 1, 2, 9004, '2015/01/18', 24);


INSERT INTO PurchaseDetails VALUES (
	1, 1, 2);
INSERT INTO PurchaseDetails VALUES (
	2, 3, 1);
INSERT INTO PurchaseDetails VALUES (
	3, 4, 1);
INSERT INTO PurchaseDetails VALUES (
	4, 3, 2);



select* from Customers;
select* from Products;
select* from Purchases;
select* from  PurchaseDetails;
select* from trackings;
select* from SaleAssistances;
select* from shops;
select* from stock;

