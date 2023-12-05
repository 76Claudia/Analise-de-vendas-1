package util;

import java.util.List;

import entities.Sale;

public class SaleService {
	public double filteredSum(List<Sale> list, java.util.function.Predicate<Sale> criteriun) {
		double sum = 0.0;
		for (Sale s : list) {
			if (criteriun.test(s)) {
				sum += s.getTotal();

			}
		}
		return sum;
	}
}