data SSP = Schere
         | Stein
         | Papier
 deriving(Eq, Show)


winner :: SSP -> SSP -> (Int, Int)
winner Schere Papier = (1,0)
winner Papier Stein = (1,0)
winner Stein Schere = (1,0)
winner x y | x == y = (0,0)
           | otherwise = (0,1)
