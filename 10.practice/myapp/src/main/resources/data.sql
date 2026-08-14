INSERT INTO users (name, email, version) 
VALUES ('홍길동', 'hong@sk.com', 0),
       ('김철수', 'kim@sk.com', 0),
       ('이영희', 'lee@sk.com', 0),
       ('박승은', 'park@sk.com', 0),
       ('정유라', 'jung@sk.com', 0);

INSERT INTO products
(product_name, price, stock_quantity, status, description, user_id, version)
VALUES
('노트북', 1500000, 10, 'ON_SALE', '고성능 개발용 노트북입니다.', 1, 0),
('무선 마우스', 35000, 50, 'ON_SALE', '2.4GHz 무선 마우스입니다.', 1, 0),
('기계식 키보드', 120000, 0, 'SOLD_OUT', '청축 기계식 키보드입니다.', 2, 0),
('27인치 모니터', 350000, 5, 'ON_SALE', 'QHD 해상도 모니터입니다.', 2, 0),
('USB 허브', 25000, 0, 'DISCONTINUED', '단종된 4포트 USB 허브입니다.', 3, 0);
