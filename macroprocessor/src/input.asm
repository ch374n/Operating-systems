		START	101

        MACRO
            FIRST  &VAR1 , &VAR2 , &AREG
            MOVEM   &AREG ,   &VAR1
            MULT    &VAR1 ,  &VAR2
            ADD     &AREG ,  &VAR1
            MOVER    &AREG ,   &VAR2
        MEND
        MACRO
            SECOND   &VAR1 , &VAR2 , &CREG
            MOVEM   &CREG ,   &VAR2
            MULT    &VAR1 ,  &VAR2
            ADD     &CREG ,  &VAR1
            MOVER    &CREG ,   &VAR1
        MEND

		READ	N
		MOVER	BREG ,	ONE
		MOVEM	BREG ,	TERM
AGAIN	MULT	BREG ,	TERM
		MOVER	CREG ,	TERM
		ADD	CREG ,	ONE
		MOVEM	CREG ,	TERM
		COMP	CREG ,	N
		BC	LE ,	AGAIN
		SECOND  23 ,    32 ,  CREG
		MOVEM	BREG ,	RESULT

		FIRST	34 ,	12 ,	BREG
		PRINT	RESULT
		STOP
		N	DS	1
		RESULT	DS	1
		ONE	DC	'1'
		TERM	DS	1
		END
