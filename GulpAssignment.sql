
Create table Restaurant( restaurant_id Number(10) Primary Key,
Restaurant_Name varchar2(255),
address Varchar2(255),
description varchar2(255)
);

Create table Reviewer( Reviewer_id Number(10) Primary Key,
Reviewer_Name varchar2(255),
email Varchar2(255),
zipcode varchar2(255)
);

create table ratings( rating_id Number(10) Primary key,
restaurant_id Number(10) REFERENCES Restaurant(Restaurant_id),
user_id Number(10) References Reviewer(Reviewer_id),
stars Number(10),
description Varchar2(255));

CREATE SEQUENCE seq_Restaurant
 MINVALUE 1
 START WITH 1
 INCREMENT BY 1
 CACHE 10;
 
 
CREATE SEQUENCE seq_Reviewer
 MINVALUE 1
 START WITH 1
 INCREMENT BY 1
 CACHE 10;
 
 
CREATE SEQUENCE seq_Ratings
 MINVALUE 1
 START WITH 1
 INCREMENT BY 1
 CACHE 10;
 
CREATE OR REPLACE TRIGGER Restaurant_Trigger
 BEFORE INSERT ON Restaurant
  FOR EACH ROW
BEGIN
  :new.restaurant_id := seq_Restaurant.nextval;
END;

Insert into restaurant(RESTAURANT_NAME,ADDRESS,Description) values('Costal Flats', 'Washingtonin Blvd', 'sea food restaurant');

Insert into restaurant(RESTAURANT_NAME,ADDRESS,Description) values('Fortina Grill', 'Kings Farm, Gaithersburg, MD', 'Italian restaurant');

Insert into restaurant(RESTAURANT_NAME,ADDRESS,Description) values('Panida', 'College Road', 'Thai food restaurant');

CREATE OR REPLACE TRIGGER Reviewer_Trigger
 BEFORE INSERT ON Reviewer
  FOR EACH ROW
BEGIN
  :new.Reviewer_id := seq_Reviewer.nextval;
END;

CREATE OR REPLACE TRIGGER Ratings_Trigger
 BEFORE INSERT ON Ratings
  FOR EACH ROW
BEGIN
  :new.Rating_id := seq_Ratings.nextval;
END;

Insert into reviewer(REVIEWER_NAme,EMAIL,ZIPCODE) values('Ken', 'ken@infosys.com', '020877');
Insert into reviewer(REVIEWER_NAme,EMAIL,ZIPCODE) values('Viki', 'viki@infosys.com', '020877');

alter table ratings add REVIEWDATE DATE;
Insert into ratings(RESTAURANT_ID,USER_ID,Stars,description,REVIEWDATE) values(1,1,4,'great food',TO_DATE('08/02/2015','MM/DD/YYYY'));
Insert into ratings(RESTAURANT_ID,USER_ID,Stars,description,REVIEWDATE) values(2,1,5,'great service',TO_DATE('08/02/2015','MM/DD/YYYY'));

Select * from reviewer where email = 'ken@infosys.com';

--SELECT rs.RESTAURANT_ID,
--  rs.RESTAURANT_NAME,
--  rs.ADDRESS,
--  rs.Description,
--  NVL(avg(stars),0)
--FROM restaurant rs
--JOIN ratings ra
--ON rs.RESTAURANT_ID=ra.RESTAURANT_ID where ra.USER_ID = 2 group by rs.RESTAURANT_ID,
--  rs.RESTAURANT_NAME,
--  rs.ADDRESS,
--  rs.Description;

SELECT rs.RESTAURANT_ID,
  rs.RESTAURANT_NAME,
  rs.ADDRESS,
  rs.Description,
  avg(stars) AS AVERAGE,
  NVL((SELECT STARS FROM RATINGS WHERE USER_ID = 2 and RESTAURANT_ID = rs.RESTAURANT_ID),0) AS USER_RATING
FROM restaurant rs
left outer JOIN ratings ra
ON rs.RESTAURANT_ID=ra.RESTAURANT_ID group by rs.RESTAURANT_ID,
  rs.RESTAURANT_NAME,
  rs.ADDRESS,
  rs.Description;
