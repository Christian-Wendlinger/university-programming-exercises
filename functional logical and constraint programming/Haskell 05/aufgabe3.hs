type Title = String
type Shortcut = String
type Checked = Bool

data Menu = MenuItem Title Shortcut [Menu]
          | Checkbox Title Checked
          | Separator
     deriving (Eq, Show)


bsp = MenuItem "Main" "" [
        MenuItem "File" "" [
          MenuItem "Load" "Ctrl+L" [],
          MenuItem "Save" "Ctrl+S" [],
          Separator,
          MenuItem "Encoding" "" [
            MenuItem "UTF-8" "" [],
            MenuItem "ISO 8859-15" "" []
          ]
        ],
        MenuItem "Edit" "" [
          MenuItem "Copy" "Ctrl+C" [],
          MenuItem "Cut" "Ctrl+X" [],
          MenuItem "Paste" "Ctrl+V" [],
          Separator,
          Checkbox "Line Wrap" True,
          Checkbox "Auto Indent" False
        ]
      ]


allMenuItems :: Menu -> [String]
allMenuItems Separator = []
allMenuItems (Checkbox title _) = [title]
allMenuItems (MenuItem title _ items) = [title] ++ [x | xs <- map allMenuItems items, x <- xs]


foldMenu :: (Title -> Shortcut -> [Menu] -> a) -> (Title -> Checked -> a) -> a -> Menu -> a
foldMenu fm fc fs = m
  where
    m (MenuItem title shortcut items) = fm title shortcut items
    m (Checkbox title checked) = fc title checked
    m Separator = fs


countSep :: Menu -> Int
countSep = foldMenu (\_ _ items -> sum (map countSep items)) (\_ _ -> 0) 1


menuDepth :: Menu -> Int
menuDepth = foldMenu (\_ _ items -> if length items > 0 then 1 + maximum (map menuDepth items) else 1) (\_ _ -> 0) 0
