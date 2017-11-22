package com.ck.utils;


import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PageEntity<T> implements Serializable {

	private List<T> list = new ArrayList<>();

	private Long total;

	private Integer pageNumber;

	private Integer pageSize;

}
