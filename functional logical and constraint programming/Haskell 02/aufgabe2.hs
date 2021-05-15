head' :: [Int] -> Int
head' [] = error "empty list"
head' (x:xs) = x

tail' :: [Int] -> [Int]
tail' [] = error "empty list"
tail' (x:xs) = xs

zip' :: [Int] -> [Int] -> [(Int, Int)]
zip' _ [] = []
zip' [] _ = []
zip' (x:[]) (y:[]) = [(x,y)]
zip' (x:xs) (y:ys) = (x,y) : zip' xs ys

unzip' :: [(Int, Int)] -> ([Int], [Int])
unzip' [] = ([], [])
unzip' ((x,y):xys) = (x : xs, y : ys)
  where (xs,ys) = unzip' xys

reverse' :: [Int] -> [Int]
reverse' [] = []
reverse' (x:xs) = reverse' xs ++ [x]

length' :: [Int] -> Int
length' [] = 0
length' (x:xs) = 1 + length' xs

split' :: Int -> [Int] -> ([Int], [Int])
split' _ [] = ([], [])
split' n (x:xs) | n <= 0 = ([], x : xs)
                | otherwise = (x : rx, ry)
                    where (rx, ry) = split' (n-1) xs
