
DROP PROCEDURE IF EXISTS task1_4;
DELIMITER $
CREATE PROCEDURE task1_4()
BEGIN    
	/*count sum of integer numbers*/
    SELECT sum(int_number) as summa from strings;    
    
    /*count mediana of double numbers*/
	SELECT avg(ss.float_number) as mediana
	FROM (
		/*selects float variables from table and their row number*/
		SELECT s.float_number, @rownum:=@rownum+1 as 'row_number'
		  FROM strings s, 
				(SELECT @rownum:=0) r /*initializes variable to count rows*/
		  ORDER BY float_number /*sorts rows ascending by numbers*/
		) as ss
	/*selects number in the middle if total rows count is odd,*/
    /*otherwise two side by side numbers from the middle are selected and their average is counted*/
	WHERE ss.row_number IN ( FLOOR((@rownum+1)/2), FLOOR((@rownum+2)/2) ); 
END $
DELIMITER ;

/*procedure call*/
call task1_4(); 
