package com.company.packer.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.company.exception.APIException;
import com.company.packer.model.Item;
import com.company.packer.model.Package;
import com.company.packer.spliterator.PackageSpliterator;

public class PackageService {

    public List<Package> loadPackageFile(String filePath) throws APIException {

	try (Stream<String> lines = Files.lines(Paths.get(filePath))) {

	    // Filter empty lines in the file
	    Spliterator<String> lineSpliterator = lines.filter(line -> !line.isEmpty()).spliterator();
 
	    // Calling custom Spliterator to process the file
	    PackageSpliterator packageSpliterator = new PackageSpliterator(lineSpliterator);
	    List<Package> packages = StreamSupport.stream(packageSpliterator, false).collect(Collectors.toList());

	    // Returning APIException if the file has any invalid data
	    if (packageSpliterator.getValidations().size() > 0) {
		throw new APIException(packageSpliterator.getValidations());
	    } else {
		return packages;
	    }
	} catch (IOException e) {
	    // Throwing an exception if an error with the file
	    throw new APIException("An error occour loading the file: " + e.getMessage());
	}
    }

    public String findBestItemCombination(Package pack) {

	// creating all packing valid options
	List<Package> packages = findPackageCombinations(pack.getItems(), pack.getWeight());

	// returning expensive combination
	return getMaxCostMinWeight(packages);
    }

    public List<Package> findPackageCombinations(List<Item> itemList, double maxWeight) {
	List<Package> packageList = new ArrayList<>();

	// Making combinations
	itemList.forEach(item -> {
	    // Iterating current package combinations
	    int currentSize = packageList.size();
	    for (int i = 0; i < currentSize; i++) {
		// Excluding combination that exceed package weight
		Double weight = packageList.get(i).getWeight() + item.getWeight();
		if (weight > maxWeight)
		    continue;
		// Creating a valid combination
		List<Item> itemCombination = new ArrayList<>();
		itemCombination.addAll(packageList.get(i).getItems());
		itemCombination.add(item);
		packageList.add(new Package(weight, itemCombination));
	    }
	    // Including single item combination
	    if (item.getWeight() <= maxWeight)
		packageList.add(new Package(item.getWeight(), Arrays.asList(item)));
	});
	return packageList;
    }

    protected String getMaxCostMinWeight(List<Package> packages) {
	// Grouping packages by cost
	TreeMap<Double, List<Package>> packagesCost = packages.stream()
		.collect(Collectors.groupingBy(pack -> pack.getItems().stream().mapToDouble(Item::getCost).sum(),
			TreeMap::new, Collectors.mapping(Function.identity(), Collectors.toList())));

	if (packagesCost.isEmpty())
	    return "-";

	// returning most expensive package with the lower weight
	return packagesCost.lastEntry().getValue().stream().min(Comparator.comparing(Package::getWeight)).get()
		.getItems().stream().map(Item::getId).map(String::valueOf).collect(Collectors.joining(","));
    }

}
