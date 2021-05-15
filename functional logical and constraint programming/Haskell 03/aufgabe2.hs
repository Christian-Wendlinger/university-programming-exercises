harshad :: [Integer]
harshad = [x | x <- [1..], isHarshad x]


isHarshad :: Integer -> Bool
isHarshad a = if a == 0
  then True
  else a `mod` quersumme a == 0

quersumme :: Integer -> Integer
quersumme a | a < 10 = a
            | otherwise = a `mod` 10 + quersumme (a `div` 10)


leapyear :: [Integer]
leapyear = [x | x <- [2000..], mod x 400 == 0 || (mod x 4 == 0 && mod x 100 /= 0)]

mapFilter :: (Num a, Ord a) => (a -> a) -> (a -> Bool) -> [a] -> [a]
mapFilter a b c = map a (filter b c)
