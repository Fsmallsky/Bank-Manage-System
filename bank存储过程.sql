create or replace trigger loan_state
       after insert on payout
       for each row
         declare prenum BINARY_DOUBLE;
         begin
           select remain into prenum from loan where loan.loan_id = :new.loan_id;
           update loan 
           set loan.remain = prenum - :new.pay_num
           where loan.loan_id = :new.loan_id;
           
           select remain into prenum from loan where loan.loan_id = :new.loan_id;
           if prenum = 0
           then
             update loan
             set loan.state = '发放完成'
             where loan.loan_id = :new.loan_id;
            else
              update loan
              set loan.state = '正在发放'
              where loan.loan_id = :new.loan_id;
            end if;
         end;
