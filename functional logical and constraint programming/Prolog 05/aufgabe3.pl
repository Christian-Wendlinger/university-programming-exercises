:- use_module(library(chr)).
:- chr_constraint cb/3. 


% b)
cb(N,K,R) \ cb(N,K,R) <=> true.

% c)
cb(N, K1, R1) \ cb(N, K2, _) <=> Nh is N / 2, K2 >= Nh, M = N - K2, K1 =:= M | cb(N, K2, R1).

% a)
cb(N,K,R) <=> N =:= K; K =:= 0 | R is 1.
cb(N,K,R) <=> N1 is N-1, K1 is K- 1, cb(N1, K1, R1), cb(N1, K, R2), R is R1+R2.


% d) über den Regeln von a, da die Regeln von oben nach unten durchgearbeitet werden.
