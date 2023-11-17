package com.example.springpractice.persistence.repositories.specifications;

@FunctionalInterface
public interface TriFunction <T,U,L,R>{
    R apply(T t, U u, L l);
}
