package com.company.packer.spliteratior;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.junit.Test;

import com.company.packer.model.Package;
import com.company.packer.spliterator.PackageSpliterator;

public class PackageSpliteratorTest   {

    PackageSpliterator packageSpliterator;

    @Test
    public void invalidPackageWeight() {
	Spliterator<String> line = Stream.of(":(1,1,€1)").spliterator();
	packageSpliterator = new PackageSpliterator(line);
	StreamSupport.stream(packageSpliterator, false).collect(Collectors.toList());
	assertEquals(2, packageSpliterator.getValidations().size());
    }

    @Test
    public void invalidSizeOfItems() {

	Spliterator<String> line = Stream.of("1 : (1,1,€1) (1,1,€1) (1,1,€1) (1,1,€1) (1,1,€1) "
		+ "(1,1,€1) (1,1,€1) (1,1,€1) (1,1,€1) (1,1,€1) (1,1,€1) (1,1,€1) "
		+ "(1,1,€1) (1,1,€1) (1,1,€1) (1,1,€1)").spliterator();
	packageSpliterator = new PackageSpliterator(line);
	StreamSupport.stream(packageSpliterator, false).collect(Collectors.toList());
	assertEquals(2, packageSpliterator.getValidations().size());
    }

    @Test
    public void invalidCostSymbolValue() {
	Spliterator<String> line = Stream.of("1:(1,1,1)").spliterator();
	packageSpliterator = new PackageSpliterator(line);
	StreamSupport.stream(packageSpliterator, false).collect(Collectors.toList());
	assertEquals(2, packageSpliterator.getValidations().size());
    }

    @Test
    public void missingIdValue() {
	Spliterator<String> line = Stream.of("1:(1,€1)").spliterator();
	packageSpliterator = new PackageSpliterator(line);
	StreamSupport.stream(packageSpliterator, false).collect(Collectors.toList());
	assertEquals(2, packageSpliterator.getValidations().size());
    }

    @Test
    public void exceedWeightConstraintValue() {
	Spliterator<String> line = Stream.of("105:(1,1,€1)").spliterator();
	packageSpliterator = new PackageSpliterator(line);
	StreamSupport.stream(packageSpliterator, false).collect(Collectors.toList());
	assertEquals(1, packageSpliterator.getValidations().size());
    }

    @Test
    public void exceedItemWeightOverConstraintValue() {
	Spliterator<String> line = Stream.of("100:(1,100.1,€1)").spliterator();
	packageSpliterator = new PackageSpliterator(line);
	StreamSupport.stream(packageSpliterator, false).collect(Collectors.toList());
	assertEquals(1, packageSpliterator.getValidations().size());
    }

    @Test
    public void itemCostOverConstraintValue() {
	Spliterator<String> line = Stream.of("100:(1,100,€105.2)").spliterator();
	packageSpliterator = new PackageSpliterator(line);
	StreamSupport.stream(packageSpliterator, false).collect(Collectors.toList());
	assertEquals(1, packageSpliterator.getValidations().size());
    }
    
    @Test
    public void uniqueItemsIdValidation() {
	Spliterator<String> line = Stream.of("100:(1,96.32,€10.2)(1,1.2,€20.2)").spliterator();
	packageSpliterator = new PackageSpliterator(line);
	StreamSupport.stream(packageSpliterator, false).collect(Collectors.toList());
	assertEquals(1, packageSpliterator.getValidations().size());
    }
    
    @Test
    public void shouldAcceptLineValues() {
	Spliterator<String> line = Stream.of("100:(1,96.32,€10.2)").spliterator();
	packageSpliterator = new PackageSpliterator(line);
	List<Package> collect = StreamSupport.stream(packageSpliterator, false).collect(Collectors.toList());
	assertEquals(0, packageSpliterator.getValidations().size());
	assertEquals(100d, collect.get(0).getWeight(),0d);
    }
    
    @Test
    public void shouldLoadItems() {
	Spliterator<String> line = Stream.of("100:(1,96.32,€10.2)(2,1.2,€20.2)").spliterator();
	packageSpliterator = new PackageSpliterator(line);
	List<Package> collect = StreamSupport.stream(packageSpliterator, false).collect(Collectors.toList());
	assertEquals(100d, collect.get(0).getWeight(),0d);
	assertEquals(0, packageSpliterator.getValidations().size());
	assertEquals(2, collect.get(0).getItems().size());
    }
}
