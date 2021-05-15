flipTuples :: [(a,b)] -> [(b,a)]
flipTuples = map (\(a,b) -> (b,a))


max' :: Ord a => [a] -> a
max' xs = foldl1 max xs


removeBy :: Ord a => (a -> Bool) -> [a] -> [a]
removeBy f = filter (not.f)


countOcc :: Eq a => a -> [a] -> Int
countOcc a x = length (filter (== a) x)


zipWith' :: (a -> b -> c) -> [a] -> [b] -> [c]
zipWith' f x y = map (\(x,y) -> f x y) (zip x y)


reverseR :: [a] -> [a]
reverseR = foldr (\x xs -> xs ++ [x]) []


reverseL :: [a] -> [a]
reverseL = foldl (\xs x -> x:xs) []

-- foldl ist für reverse besser geeignet, da foldl quasi die List in umgekehrter Reigenfolge abarbeitet
-- und genau das ist für diesen Fall die optimale Vorgehensweise
