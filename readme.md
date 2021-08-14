
    ## 변수

    1. idx  

        ⇒ DB 담는 순서

    2. 종목명 = name

        ⇒ 종목명

    3. ownStckws

        ⇒ 보유 종목수

    4. pPrice

        구매 (perchase price) 구매당시 주식 가격

    5. cPrice

        현자금액(current price) 현재의 주식가격

    6. totalpPrice(total perchase price)

        매입금액

    7. totalcPrice(total current price)

        평가금액

    8. profitRatio

        수익률

- DAO

    VO 와 window 연결 ⇒ DB 연결

- WInDOW

    ## GUI 객체

    ### *️⃣North

    1. columnNames ⇒ 테이블 명들
    2. table⇒ DB 에서 현황을 불러와주는 table

        model ⇒ DefaultTableModel(columnNames,0)

        JScrollPane ⇒ JScrollPane(table)

    ### *️⃣Center

    1. inputPanel ⇒ 전체 패널
        1. labelPanel ⇒ label 붙여줄 패널
        2. textPanel ⇒ 텍스트 필드 붙여줄 패널
            1. [] textFields ⇒ [ 0 => 종목명 , 1 => 주식 수, 2 => 구매가, 3 => 현재가]
            2. labelPanel ⇒ label 들 붙여줄 패널(이름은 columnNames (index 1 ~4 활용)

    ### *️⃣South

    1. buttonPanel ⇒ 버튼들 붙여줄 패널
        1. buttons =0 입력 1 보기 2 수정 3 삭제
        2. buttonNames ⇒ ["입력","보기","수정","삭제"]
- VIewMain
