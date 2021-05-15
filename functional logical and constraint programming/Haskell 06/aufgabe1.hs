evenUneven :: [Int] -> [Either Int Int]
evenUneven = map (\x -> if mod x 2 == 0 then Right x else Left x)


maxSndComp :: Ord b => [(a,b)] -> (a,b)
maxSndComp xs = if length erg >= 1 then head erg else error "max of empty list"
        where (x,y) = unzip xs
              m = maximum y
              erg = filter (\(x,y) -> y == m) xs
