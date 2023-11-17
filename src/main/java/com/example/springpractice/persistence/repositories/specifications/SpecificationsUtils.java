package com.example.springpractice.persistence.repositories.specifications;

import jakarta.persistence.criteria.*;
import com.example.springpractice.persistence.entities.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.function.BiFunction;

public class SpecificationsUtils {
    public static <T> Specification<T> has(String attributeName, Object attributeValue){
        return applyIgnoreNull(attributeValue,
                (root, query, cb) -> cb.equal(root.get(attributeName), attributeValue));
    }

    public static <T> Specification<T> equalsIgnoreCase(String attributeName, String attributeValue){
        return applyIgnoreNull(attributeValue,
            (root, query, cb) -> cb.equal(
                cb.lower(root.get(attributeName)),
                attributeValue.trim().toLowerCase())
        );
    }
    public static <T> Specification<T> join(String field, String targetField, Object value) {
        return joinIgnoreNull(value, (root, query, cb) -> {
            Join<Object, Object> join = root.join(field, JoinType.INNER);
            return cb.equal(join.get(targetField), value);
        });
    }

    public static <T> Specification<T> hasPrefix(String attributeName, String attributeValue){
        return applyIgnoreNull(attributeValue,
                (root, query, cb) -> cb.like(root.get(attributeName), attributeValue + "%"));
    }

    public static <T> Specification<T> containsIgnoreCase(String attributeName, String attributeValue){
        return applyIgnoreNull(attributeValue,
                (root, query, cb) -> cb.like(root.get(attributeName), "%" + attributeValue + "%"));
    }

    private static <T> Specification<T> applyIgnoreNull(Object value, TriFunction<Root<?>, CriteriaQuery<?>, CriteriaBuilder, Predicate> function) {
        return (root, query, cb) -> {
            if(value == null){
                return cb.conjunction();
            }
            return function.apply(root, query, cb);
        };
    }
    private static <T> Specification<T> joinIgnoreNull(Object value, TriFunction<Root<?>, CriteriaQuery<?>, CriteriaBuilder, Predicate> function){
        return (root, query, cb) -> {
            if(value == null){
                return cb.conjunction();
            }
            return function.apply(root, query, cb);
        };
    }

}
