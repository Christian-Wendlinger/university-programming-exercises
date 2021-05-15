% a)
runner(tim).
runner(arthur).
runner(horst).
runner(lukas).

% b)
vor(tim,lukas).
vor(arthur,A) :- A \= tim.
vor(lukas,A) :- A \= tim.

% c)
allDifferent(A,B,C,D) :- A \= B, A \= C, A \= D, B \= C, B \= D, C \= D.

% d)
einlauf(P1,P2,P3,P4) :-
    runner(P1),
    runner(P2),
    runner(P3),
    runner(P4),
    allDifferent(P1,P2,P3,P4).

loesung(N1,N2,N3,N4) :- 
    einlauf(N1,N2,N3,N4),
    vor(N1,N2),
    vor(N2,N3),
    vor(N3,N4).