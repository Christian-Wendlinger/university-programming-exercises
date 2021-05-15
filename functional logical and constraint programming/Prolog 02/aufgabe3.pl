% a)
head([X | _], X).

% b)
palindrom(X) :- X = Y, reverse(Y, X).

% c)
isSorted([]).
isSorted([X | Y]) :- Y = [];head(Y, Z), isSorted(Y), X =< Z.

% d)
%compress([], R).
%compress([_], R).
%compress([X, Y | XS], R) :- X = Y, compress([X | XS], F), R is [X, F]; X \= Y, compress([Y | XS], S), R is [X, Y | S].

% e)
drop(_,[],[]).
drop(N, [X|XS], R) :- drop(N,[X|XS],R,0).
drop(N,[X|XS],R,ACC) :- (ACC =:= N -> R = [X|XS]; ACC1 is ACC+1, drop(N,XS,R,ACC1)).

% e2)
dropappend(_,[],[]).
dropappend(N, [X|XS], R) :- N = 0, append([X],XS,R); N > 0,N1 is N - 1, dropappend(N1, XS, R).

% f)
minMax([], _) :- ! , fail.
minMax(XS,(R1,R2)) :- min_list(XS,R1), max_list(XS,R2).