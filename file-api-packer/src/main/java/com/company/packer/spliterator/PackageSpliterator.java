package com.company.packer.spliterator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.company.packer.model.Item;
import com.company.packer.model.Package;

public class PackageSpliterator implements Spliterator<Package> {

    private final Spliterator<String> lineSpliterator;

    private final Pattern fileFormat = Pattern
	    .compile("^(\\d+(\\.\\d+)*)\\s*\\:((\\s*\\(\\d+,(\\d+(\\.\\d+)*),\\€(\\d+(\\.\\d+)*)\\)){1,15})\\s*$");

    private final Pattern itemFormat = Pattern.compile("\\((\\d+),(\\d+(\\.\\d+)*),\\€(\\d+(\\.\\d+)*)\\)");

    private Double packageWeight;
    private List<Item> items;

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    private Set<ConstraintViolation<Package>> validations = new HashSet<>();

    public PackageSpliterator(Spliterator<String> lineSpliterator) {
	this.lineSpliterator = lineSpliterator;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Package> action) {

	boolean advance = this.lineSpliterator.tryAdvance(line -> {
	    // validating each line with a regular expression
	    Matcher matcher = fileFormat.matcher(line);
	    if (matcher.find()) {
		packageWeight = Double.parseDouble(matcher.group(1));
		items = loadItems(matcher.group(3));
	    } 
	});

	if (advance) {
	    // creating package object from file line
	    Package pack = new Package(packageWeight, items);
	    //validating all data constrains 
	    validations.addAll(validator.validate(pack));
	    action.accept(pack);
	    return true;
	} else {
	    return false;
	}
    }

    @Override
    public Spliterator<Package> trySplit() {
	return null;
    }

    @Override
    public long estimateSize() {
	return lineSpliterator.estimateSize();
    }

    @Override
    public int characteristics() {
	return lineSpliterator.characteristics();
    }

    protected List<Item> loadItems(String line) {

	List<Item> itemList = new ArrayList<>();
	//Creating groups of individual items using regular expression
	Matcher matcher = itemFormat.matcher(line);
	while (matcher.find()) {
	    //Creating item from matching groups
	    Item item = new Item(Long.parseLong(matcher.group(1)), Double.parseDouble(matcher.group(2)),
		    Double.parseDouble(matcher.group(4)));
	    itemList.add(item);
	}
	return itemList;
    }

    public Set<ConstraintViolation<Package>> getValidations() {
	return validations;
    }

}
