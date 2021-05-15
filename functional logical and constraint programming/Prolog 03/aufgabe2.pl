% generate and test answers
answer(L, R) :- findall([P1, P2], (subseq0(L, P1), subseq0(L, P2), gerecht(L, P1, P2)), R).

% conditions
gerecht(L,P1,P2) :- aufteilung(L,P1,P2), sum(P1, SP1), sum(P2, SP2), SP1 = SP2.
aufteilung(L,P1,P2) :- quick_sort(L, LS), append(P1, P2, R), quick_sort(R, RS), LS = RS.

%sort a list descending via quicksort
quick_sort(List,Sorted):-q_sort(List,[],Sorted).
q_sort([],Acc,Acc).
q_sort([H|T],Acc,Sorted):-
	pivoting(H,T,L1,L2),
    q_sort(L1,Acc,Sorted1),q_sort(L2,[H|Sorted1],Sorted).
    
pivoting(H,[],[],[]).
pivoting(H,[X|T],[X|L],G):-X=<H,pivoting(H,T,L,G).
pivoting(H,[X|T],L,[X|G]):-X>H,pivoting(H,T,L,G).

% get sum of a list
sum([], 0).
sum([H|T], N):-
    sum(T, X),
    N is X + H.

% generate subsets of a List
subseq0(List, List).
subseq0(List, Rest) :-
subseq1(List, Rest).
subseq1([_|Tail], Rest) :-
subseq0(Tail, Rest).
subseq1([Head|Tail], [Head|Rest]) :-
subseq1(Tail, Rest).