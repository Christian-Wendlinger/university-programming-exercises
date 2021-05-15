% a)
move((Sx,Sy), (Sx,Sy), []).
move((Sx,Sy), (Ex,Ey), [(Mx, My)|R]) :- Dx is Sx + Mx, Dy is Sy + My, move((Dx, Dy), (Ex, Ey), R). 

% b)
g([]).
g([X|XS]) :- (X mod 2 =:= 0 -> g(XS); f(XS)).

f([]) :- fail.
f([Y|YS]) :- (Y mod 2 =:= 0 -> f(YS); g(YS)).

% c)
menge([]).
menge([_]).
menge([X|R]) :- member(X, R) -> fail; menge(R).
 