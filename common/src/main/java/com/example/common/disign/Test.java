package com.example.common.disign;

import com.example.common.disign.create.BuilderTest;

public class Test {

    public static void main(String[] args) {
        BuilderTest test = new BuilderTest.Builder()
                .setName("name")
                .setAge(12)
                .build();
        test.getAge();
    }
}
