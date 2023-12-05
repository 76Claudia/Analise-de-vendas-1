package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import entities.Sale;
import util.SaleService;

public class Program {

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);

		Scanner sc = new Scanner(System.in);

		System.out.print("Entre o caminho do arquivo: ");
		String path = sc.nextLine();
		System.out.println();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Sale> list = new ArrayList<>();
			String line = br.readLine();
			while (line != null) {

				String[] fields = line.split(",");

				Integer month = Integer.parseInt(fields[0]);
				Integer year = Integer.parseInt(fields[1]);
				String seller = fields[2];
				Integer items = Integer.parseInt(fields[3]);
				Double total = Double.parseDouble(fields[4]);

				list.add(new Sale(month, year, seller, items, total));

				line = br.readLine();

			}

			double avg = list.stream().map(s -> s.getTotal()).reduce(0.0, (x, y) -> x / y);

			System.out.println("Cinco primeiras vendas de 2016 de maior preco medio: ");

			List<String> sales = list.stream().filter(s -> s.averagePrice() > avg).map(s -> s.toString()).limit(5)

					.collect(Collectors.toList());

			sales.forEach(System.out::println);

			System.out.println();

			SaleService ss = new SaleService();

			double sum = ss.filteredSum(list, s -> s.getSeller().charAt(0) == 'L');

			System.out
					.println("Valor total vendido pelo vendedor Logan nos meses 1 e 7 = " + String.format("%.2f", sum));

		}

		catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}

		sc.close();

	}
}
