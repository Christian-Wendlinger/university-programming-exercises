type Art = String
type NochPlatz = Bool
type Gewicht = Float

data Gleis = Weiche Gleis Gleis
           | Leer Gleis
           | Belegt NochPlatz Art Gewicht Gleis
           | Puffer
    deriving Show


bsp = Leer
        (Weiche
          (Weiche
            (Belegt False "Kesselwagen" 80
              (Belegt True "Flachwagen" 15
                (Belegt False "Kühlwagen" 42.2 Puffer)))
            (Leer
              (Belegt False "Kesselwagen" 48
              (Belegt False "Kesselwagen" 60 Puffer)))
          )
          (Leer
            (Weiche
              (Belegt True "Flachwagen" 13
                (Belegt False "Flachwagen" 85.6
                  (Belegt False "Flachwagen" 65 Puffer)))
              (Weiche
                (Belegt True "Kesselwagen" 24.9
                  (Belegt True "Kühlwagen" 36.6 Puffer))
                (Leer
                  (Belegt False "Kesselwagen" 79.5 Puffer))
              )
            )
          )
        )




countBelegt :: Gleis -> Int
countBelegt (Weiche g1 g2) = countBelegt g1 + countBelegt g2
countBelegt (Leer g) = countBelegt g
countBelegt (Belegt np art gew gl) = 1 + countBelegt gl
countBelegt Puffer = 0



foldGleis :: (a -> a -> a) ->
             (a -> a) ->
             (Bool -> String -> Float -> a -> a) ->
             a ->
             Gleis ->
             a

foldGleis fw fl fb fp = m
    where
      m (Weiche g1 g2) = fw (m g1) (m g2)
      m (Leer g) = fl (m g)
      m (Belegt np art gew g) = fb np art gew (m g)
      m Puffer = fp



sumGewichtNP :: Gleis -> Float
sumGewichtNP = foldGleis (\g1 g2 -> g1 + g2) (\g -> g) (\np _ gew g -> if np then gew + g else g) 0


replaceKF :: Gleis -> Gleis
replaceKF = foldGleis (\g1 g2 -> Weiche g1 g2) (\g -> Leer g) (\np art gew g -> if art == "Kesselwagen" then Belegt True "Flachwagen" 0.0 g else Belegt np art gew g) Puffer


listWaggons :: Gleis -> [(String, Float)]
listWaggons = foldGleis (\g1 g2 -> g1 ++ g2) (\g -> g) (\_ art gew g -> [(art, gew)] ++ g) []


maxGewicht :: Gleis -> Float
maxGewicht g = snd (maxSndComp (listWaggons g))

maxSndComp :: Ord b => [(a,b)] -> (a,b)
maxSndComp xs = if length erg >= 1 then head erg else error "max of empty list"
        where (x,y) = unzip xs
              m = maximum y
              erg = filter (\(x,y) -> y == m) xs
