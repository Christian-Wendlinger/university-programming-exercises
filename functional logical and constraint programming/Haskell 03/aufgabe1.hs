split' :: Int -> [a] -> ([a], [a])
split' _ [] = ([],[])
split' n (x:xs) | n <= 0 = ([],(x:xs))
split' n (x:xs) = (x:ys, zs)
  where
    (ys, zs) = split' (n-1) xs

merge :: Ord a => [a] -> [a] -> [a]
merge [] [] = []
merge x [] = x
merge [] y = y
merge [x] [y] | x < y = [x, y]
              | otherwise = [y, x]
merge (x:xs) (y:ys) | x < y = (x : merge xs (y:ys))
                    | otherwise = (y : merge ys (x:xs))


mergesort :: Ord a => [a] -> [a]
mergesort [] = []
mergesort [x] =  [x]
mergesort x = merge (mergesort a) (mergesort b)
                  where (a, b) = split' (div (length x) 2) x
