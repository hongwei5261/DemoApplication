package com.example.common.disign.create;

public class BuilderTest {
    private String name;
    private int age;

    private BuilderTest(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public static class Builder {
        private String name;
        private int age;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public BuilderTest build() {
            return new BuilderTest(this);
        }
    }
}
