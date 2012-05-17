z = 1

p = 2

--module myModule (factorial) where

add x y = x + y

doubleAdd x y = 2*x + 2*y 
к любым типам


lambdaMinus  = \x y -> x - y 


-- we can call this function with x difined only!
first = \x y -> x 

--factorial on Haskell
factorial x = if(x > 1)
    then x * factorial (x-1)
    else 1
-- tail ended optimization for factorial
tail_ended_helper= \accumulator n -> if(n > 1) then tail_ended_helper (accumulator * n) (n - 1) else accumulator   

tailEndedFactorial n = tail_ended_helper 1 n 

k = \x y -> x

mySignum :: Int -> Int
mySignum n | n > 0 = 1
           | n < 0 = (-1)
           | n == 0 = 0  

superAdd = add (3.5::Double) (2.9::Double) 

