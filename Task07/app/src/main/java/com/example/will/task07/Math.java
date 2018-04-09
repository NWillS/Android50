package com.example.will.task07;

@SuppressWarnings({"FieldCanBeLocal", "UnnecessaryThis", "StandardVariableNames"})
class Math {
    private final Integer a = 2;
    private final Integer b = 4;

    private final Result result;

    Math(Result result) {
        this.result = result;
    }

    void calculate(){
        Integer res = this.a + this.b;
        this.result.result(res);
    }
}
