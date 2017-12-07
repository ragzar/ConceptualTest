package com.company.packer.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.company.exception.APIException;
import com.company.packer.model.Item;
import com.company.packer.model.Package;
import com.company.packer.service.PackageService;

public class PackageServiceTest {

    PackageService service = new PackageService();

    @Test
    public void shouldLoadValidFile() throws APIException {
	String file = this.getClass().getResource("/package.txt").getPath();
	List<Package> packages = service.loadPackageFile(file);
	assertEquals(4, packages.size());
    }

    @Test(expected = APIException.class)
    public void shouldTrhowExceptionInvalidData() throws APIException {
	String file = this.getClass().getResource("/invalidPackage.txt").getPath();
	service.loadPackageFile(file);
    }

    @Test(expected = APIException.class)
    public void shouldTrhowExceptionInvalidFilePath() throws APIException {
	service.loadPackageFile("invalid.txt");
    }

    @Test
    public void shouldCreateAllPosibleCombinations() throws APIException {
	List<Item> items = Arrays.asList(new Item(1l, 3d, 5d), new Item(2l, 5d, 3d), new Item(3l, 7d, 3d));
	List<Package> packages = service.findPackageCombinations(items, 30);
	assertEquals(7, packages.size());
    }

    @Test
    public void shouldRemoveHevierCombinations() throws APIException {
	List<Item> items = Arrays.asList(new Item(1l, 23d, 5d), new Item(2l, 29d, 3d), new Item(3l, 30d, 3d));
	List<Package> packages = service.findPackageCombinations(items, 30);
	assertEquals(3, packages.size());
    }

    @Test
    public void shouldRemoveAllHeavierItems() throws APIException {
	List<Item> items = Arrays.asList(new Item(1l, 43d, 5d), new Item(2l, 60d, 3d), new Item(3l, 31d, 3d));
	List<Package> packages = service.findPackageCombinations(items, 30);
	assertEquals(0, packages.size());
    }

    @Test
    public void shouldChooseHigerCostLighterWeigthCombination() throws APIException {
	List<Item> items = Arrays.asList(new Item(1l, 3d, 20d), new Item(2l, 23d, 20d));
	List<Item> items2 = Arrays.asList(new Item(3l, 13d, 20d), new Item(4l, 3d, 20d));
	List<Package> packages = Arrays.asList(new Package(26d, items), new Package(16d, items2));
	assertEquals("3,4", service.getMaxCostMinWeight(packages));
    }

    @Test
    public void shouldChooseHigerCostCombination() throws APIException {
	List<Item> items = Arrays.asList(new Item(1l, 3d, 30d), new Item(2l, 23d, 20d));
	List<Item> items2 = Arrays.asList(new Item(3l, 13d, 20d), new Item(4l, 3d, 20d));
	List<Package> packages = Arrays.asList(new Package(26d, items), new Package(16d, items2));
	assertEquals("1,2", service.getMaxCostMinWeight(packages));
    }

    @Test
    public void shouldReturnDash() throws APIException {
	assertEquals("-", service.getMaxCostMinWeight(Collections.emptyList()));
    }
}
