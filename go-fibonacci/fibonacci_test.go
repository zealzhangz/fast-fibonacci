package fibonacci

import (
	"time"
	"log"
	"fmt"
	"testing"
	"math/big"
)
func TmpVariableCalcFib(n int) *big.Int {
	if n < 3{
		return big.NewInt(1)
	}
	result,first,second := big.NewInt(0),big.NewInt(1),big.NewInt(1)
	for j := 2; j < n;j++{
		result = first.Add(first,second)
		first = second
		second = result
	}
	return result
}

func TestTmpVariableCalcFib(t *testing.T) {
	n := 8000000
	start := time.Now()
	fmt.Println("The", n, "th Fibonacci is", TmpVariableCalcFib(n))
	elapsed := time.Since(start)
	log.Printf("Time(ms) %f", elapsed.Seconds() * 1000)
}
