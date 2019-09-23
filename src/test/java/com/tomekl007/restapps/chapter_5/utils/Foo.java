package com.tomekl007.restapps.chapter_5.utils;

import org.springframework.data.annotation.Id;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;


public class Foo {


    public Foo() {
    }

    public Foo(Integer id, String formattedName, Integer quantity) {
        this.id = id;
        this.formattedName = formattedName;
        this.quantity = quantity;
    }

    @Id
    private Integer id;
    private String formattedName;
    private Integer quantity;

    public Foo(FooDto dto) {
        this.id = (ThreadLocalRandom.current()
            .nextInt(0, 100) == 0) ? null : dto.getId();
        this.formattedName = dto.getName();
        this.quantity = ThreadLocalRandom.current()
            .nextInt(0, 10);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormattedName() {
        return formattedName;
    }

    public void setFormattedName(String formattedName) {
        this.formattedName = formattedName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Foo foo = (Foo) o;
        return Objects.equals(id, foo.id) &&
            Objects.equals(formattedName, foo.formattedName) &&
            Objects.equals(quantity, foo.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, formattedName, quantity);
    }
}
