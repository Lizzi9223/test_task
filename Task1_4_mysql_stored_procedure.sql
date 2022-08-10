
DROP PROCEDURE IF EXISTS task1_4;
DELIMITER $
CREATE PROCEDURE task1_4()
BEGIN    
    SELECT sum(int_number) as summa from strings;    
    
	SELECT avg(ss.float_number) as mediana
	FROM (
		SELECT s.float_number, @rownum:=@rownum+1 as 'row_number'
		  FROM strings s, (SELECT @rownum:=0) r
		  WHERE float_number is NOT NULL
		  ORDER BY float_number
		) as ss
	WHERE ss.row_number IN ( FLOOR((@rownum+1)/2), FLOOR((@rownum+2)/2) );
END $
DELIMITER ;

call task1_4();
