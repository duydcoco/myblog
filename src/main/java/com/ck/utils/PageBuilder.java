package com.ck.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageBuilder {
	
	private static <S, T> PageEntity<T> copy(PageEntity<S> pageEntity) {
		PageEntity<T> result = new PageEntity<>();
		result.setPageNumber(pageEntity.getPageNumber());
		result.setPageSize(pageEntity.getPageSize());
		result.setTotal(pageEntity.getTotal());
		return result;
	}
	
	public static <S, T> PageEntity<T> copyAndConvert(PageEntity<S> pageEntity, List<T> list) {
		PageEntity<T> result = copy(pageEntity);
		result.setList(list);
		return result;
	}
	
	public static <S, T> PageEntity<T> copyAndConvert(PageEntity<S> pageEntity, Function<S, T> function) {
		PageEntity<T> result = copy(pageEntity);
		result.setList(pageEntity.getList().stream().map(v -> function.apply(v)).collect(Collectors.toList()));
		return result;
	}

	public static <T> PageEntity<T> empty(Integer getPageSize, Integer getPageNumber) {
		PageEntity<T> result = new PageEntity<>();
		result.setPageNumber(getPageNumber);
		result.setPageSize(getPageSize);
		result.setTotal(0L);
		result.setList(new ArrayList<>());
		return result;
	}
	
}
